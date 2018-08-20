package mx.com.bsmexico.layoutstool.core.api.nav;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import javafx.scene.Parent;
import javafx.scene.layout.Region;

/**
 * 
 * 
 * @author jchr
 *
 */
public abstract class Navigator extends Region {
	protected final String ROOT_NAME = "ROOT";
	protected final String DEFAULT_SECTION_NAME = "DEFAULT";
	protected TreeNavNode root;

	/**
	 * Create a default Navigator
	 */
	public Navigator() {
		super();
	}

	/**
	 * Create a Navigator based in routes
	 * 
	 * @param routes
	 */
	public Navigator(final List<NavRoute> routes) throws IllegalArgumentException {
		super();
		if (routes == null || routes.isEmpty()) {
			throw new IllegalArgumentException("List routes is empty or null. Use default constructor");
		}
		builLogicNavigationTree(routes);
	}

	/**
	 * Node types in the logic navigation tree
	 * 
	 * @author jchr
	 *
	 */
	protected enum NODETYPE {
		SECTION_NODE, NAV_NODE, ROOT_NODE, LEAF_NODE;
	}

	/**
	 * Build a Navigation Tree Structure from the Component Layouts
	 * 
	 * @param components
	 * @return
	 */
	protected void builLogicNavigationTree(final List<NavRoute> routes) {
		// TODO agregar graphic
		root = new TreeNavNode(ROOT_NAME, NODETYPE.ROOT_NODE, null, 0);
		root.setGraphic(getGraphicNavigatorNode(null, NODETYPE.ROOT_NODE));
		if (routes != null) {
			routes.forEach(r -> this.buildTreeBranch(root, r, true));
		}

	}

	/**
	 * Build a branch in the logic tree
	 * 
	 * 
	 * @param parent
	 * @param route
	 */
	protected void buildTreeBranch(final TreeNavNode parent, final NavRoute route, boolean section) {
		if (parent != null && route != null) {
			if (route.hasNext()) {
				final String name = (StringUtils.isBlank(route.getSection())) ? DEFAULT_SECTION_NAME
						: route.getSection();
				final NavRoute.NavNode node = (section) ? new NavRoute.NavNode(name, name, null, 0, false)
						: route.next();
				TreeNavNode nextParent = null;
				final Optional<TreeNavNode> optNode = parent.getChilden().stream()
						.filter(child -> child.getId() == node.getName()).findFirst();
				if (optNode.isPresent()) {
					nextParent = optNode.get();
				} else {
					NODETYPE type = (route.hasNext()) ? ((section) ? NODETYPE.SECTION_NODE : NODETYPE.NAV_NODE)
							: NODETYPE.LEAF_NODE;
					nextParent = new TreeNavNode(node.getName(), type, parent, node.getPosition());
					nextParent.setGraphic(getGraphicNavigatorNode(node, type));
					parent.addChildren(nextParent);
				}
				buildTreeBranch(nextParent, route, false);
			}
		}
	}

	/**
	 * Find a specific node in the logic tree
	 * 
	 * @param node
	 * @param id
	 * @return the first node with id
	 */
	protected TreeNavNode findTreeNode(final TreeNavNode node, final String id) {
		TreeNavNode result = null;
		if (node != null || !StringUtils.isBlank(id)) {
			if (node.getId().equals(id)) {
				result = node;
			} else {
				List<TreeNavNode> children = node.getChilden();
				for (TreeNavNode child : children) {
					result = findTreeNode(child, id);
					if (result != null) {
						break;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get all branch corresponding to the route
	 * 
	 * @param root
	 * @param route
	 * @return
	 */
	protected List<TreeNavNode> getBranchByRoute(final TreeNavNode root, final NavRoute route) {
		List<TreeNavNode> branch = new ArrayList<>();
		route.goStart();
		TreeNavNode currentNode = root;
		while (route.hasNext() && currentNode != null) {
			NavRoute.NavNode navNode = route.next();
			currentNode = findTreeNode(currentNode, navNode.getName());
			branch.add(currentNode);
		}
		return branch;
	}

	/**
	 * 
	 * Create the graphic part corresponding to a node in the Navigator
	 * 
	 * @param navNode
	 * @return
	 */
	protected abstract Parent getGraphicNavigatorNode(NavRoute.NavNode navNode, NODETYPE type);
}
