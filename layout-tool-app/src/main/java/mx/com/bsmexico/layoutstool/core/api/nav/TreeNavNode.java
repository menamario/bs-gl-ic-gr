package mx.com.bsmexico.layoutstool.core.api.nav;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import javafx.scene.Node;

/**
 * @author jchr
 *
 */
public class TreeNavNode {

	private TreeNavNode parent;
	private List<TreeNavNode> children;
	private Node graphic;
	private Integer position;
	private String id;
	private Node componentLayout;

	/**
	 * @param id
	 * @param parent
	 * @param children
	 * @param graphic
	 * @param position
	 */
	public TreeNavNode(final String id, final TreeNavNode parent, final List<TreeNavNode> children, final Node graphic,
			final Integer position) {
		this.parent = parent;
		this.children = (children == null) ? new ArrayList<>() : children;
		this.graphic = graphic;
		this.id = (StringUtils.isBlank(id)) ? UUID.randomUUID().toString() : id;
		this.position = (position == null) ? 0 : position;
	}

	/**
	 * @param id
	 * @param parent
	 * @param graphic
	 * @param position
	 */
	public TreeNavNode(final String id, TreeNavNode parent, Node graphic, final Integer position) {
		this(id, parent, null, graphic, position);
	}

	/**
	 * @param id
	 * @param position
	 */
	public TreeNavNode(final String id, final Integer position) {
		this(id, null, null, null, position);
	}

	/**
	 * @param child
	 */
	public void addChildren(final TreeNavNode child) {
		children.add(child);
	}

	/**
	 * @param node
	 */
	public void removeChildren(final TreeNavNode node) {
		children.remove(node);
	}

	/**
	 * @return immutable list of children
	 */
	public List<TreeNavNode> getChilden() {
		return Collections.unmodifiableList(children);
	}

	/**
	 * @return immutable list of siblings
	 */
	public List<TreeNavNode> getSiblings() {
		return (parent == null || parent.children.isEmpty()) ? Collections.emptyList() : parent.getChilden();
	}

	/**
	 * Get Parent
	 * 
	 * @return the parent
	 */
	public TreeNavNode getParent() {
		return parent;
	}

	/**
	 * Change the parent
	 * 
	 * @param parent
	 * @return old Parent
	 */
	public TreeNavNode changeParent(TreeNavNode parent) {
		TreeNavNode oldParent = this.parent;
		this.parent = parent;
		return oldParent;
	}

	/**
	 * @return the graphic
	 */
	public Node getGraphic() {
		return graphic;
	}

	/**
	 * @param graphic the graphic to set
	 */
	public void setGraphic(Node graphic) {
		this.graphic = graphic;
	}

	/**
	 * @return the position
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the componentLayout
	 */
	public Node getComponentLayout() {
		return componentLayout;
	}

	/**
	 * @param componentLayout the componentLayout to set
	 */
	public void setComponentLayout(Node componentLayout) {
		this.componentLayout = componentLayout;
	}

}
