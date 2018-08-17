package mx.com.bsmexico.layoutstool.core.api.nav;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.sun.istack.internal.NotNull;

import javafx.scene.Node;
import mx.com.bsmexico.layoutstool.core.api.ComponentLayout;
import mx.com.bsmexico.layoutstool.core.api.Layout;

/**
 * 
 * 
 * @author jchr
 *
 */
public abstract class AppNavigator implements ComponentLayout {

	private final String ROOT_NAME = "ROOT";
	private final String DEFAULT_SECTION_NAME = "DEFAULT";
	protected TreeNavNode root;

	protected enum NODETYPE {
		SECTION_NODE, NAV_NODE, ROOT_NODE;
	}

	/**
	 * Build a Navigation Tree Structure from the Component Layouts
	 * 
	 * @param components
	 * @return
	 */
	protected void builNavigationTree(final List<ComponentLayout> components) {
		// TODO agregar graphic
		root = new TreeNavNode(ROOT_NAME, null, getGraphicNode(null, NODETYPE.ROOT_NODE), 0);
		if (components != null) {
			components.forEach(cmp -> {
				final Layout layout = cmp.getLayout();
				if (layout != null && layout.getRoute() != null) {
					this.buildTreeBranch(root, layout.getRoute(), layout.getNode(), true);
				}
			});
		}

	}

	/**
	 * Build a branch in the Tree
	 * 
	 * 
	 * @param parent
	 * @param route
	 */
	protected void buildTreeBranch(@NotNull final TreeNavNode parent, @NotNull final NavRoute route,
			Node componentLayout, boolean section) {
		if (parent != null && route != null) {
			if (route.hasNext()) {
				final NavRoute.NavNode node = (section)
						? new NavRoute.NavNode(
								(StringUtils.isBlank(route.getSection())) ? DEFAULT_SECTION_NAME : route.getSection(),
								null, 0, false)
						: route.forward();
				TreeNavNode nextParent = null;
				final Optional<TreeNavNode> optNode = parent.getChilden().stream()
						.filter(child -> child.getId() == node.getTitle()).findFirst();
				if (optNode.isPresent()) {
					nextParent = optNode.get();
				} else {
					nextParent = new TreeNavNode(node.getTitle(), parent,
							getGraphicNode(node, (section) ? NODETYPE.SECTION_NODE : NODETYPE.NAV_NODE),
							node.getPosition());
					parent.addChildren(nextParent);
				}
				buildTreeBranch(nextParent, route, componentLayout, false);
			} else {
				// Parent is tree lef. Bind componentLayout
				parent.setComponentLayout(componentLayout);
			}
		}
	}

	/**
	 * @param navNode
	 * @return
	 */
	abstract Node getGraphicNode(NavRoute.NavNode navNode, NODETYPE type);
}
