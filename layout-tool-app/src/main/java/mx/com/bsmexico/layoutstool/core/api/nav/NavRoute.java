package mx.com.bsmexico.layoutstool.core.api.nav;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

//forward 
/**
 * @author jchr
 *
 */
public class NavRoute {

	private String section;
	private List<NavNode> path;
	private int index = 0;

	private NavRoute(final BuilderNavRoute builder) {
		this.section = builder.section;
		path.addAll(builder.path);
	}

	public static class BuilderNavRoute {
		private String section;
		private List<NavNode> path;

		public BuilderNavRoute(final String section) {
			path = new ArrayList<NavNode>();
			this.section = section;
		}

		public BuilderNavRoute addNode(final NavNode node) {
			path.add(node);
			return this;
		}

		public BuilderNavRoute addNode(final String title) {
			this.addNode(title, -1, false, null);
			return this;
		}

		public BuilderNavRoute addNode(final String title, final Integer position, final boolean displace,
				final FileInputStream img) {
			this.addNode(new NavNode(title, img, position, displace));
			return this;
		}

		public NavRoute build() throws IllegalStateException {
			if (path.isEmpty()) {
				throw new IllegalStateException("Is necesary add at least one node Node to build a NavRoute");
			}
			return new NavRoute(this);
		}
	}

	public static class NavNode {
		private String title;
		private FileInputStream img;
		private Integer position;
		private boolean displace;

		/**
		 * @param title
		 * @param img
		 * @param position
		 * @param displace
		 */
		public NavNode(String title, FileInputStream img, Integer position, boolean displace) {
			super();
			this.title = title;
			this.img = img;
			this.position = position;
			this.displace = displace;
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @return the img
		 */
		public FileInputStream getImg() {
			return img;
		}

		/**
		 * @return the position
		 */
		public Integer getPosition() {
			return position;
		}

		/**
		 * @return the displace
		 */
		public boolean isDisplace() {
			return displace;
		}

	}

	/**
	 * 
	 * @return
	 */
	public NavNode back() {
		if (this.index > 0) {
			this.index--;
		}
		return this.path.get(index);
	}

	/**
	 * @return
	 */
	public NavNode forward() {
		if (this.index < this.path.size() - 1) {
			this.index++;
		}
		return this.path.get(index);
	}

	/**
	 * @return
	 */
	public NavNode goStart() {
		return this.path.get(0);
	}

	/**
	 * @return
	 */
	public NavNode goEnd() {
		return this.path.get(this.path.size() - 1);
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	public boolean hasNext() {
		return (index < path.size() - 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		path.forEach(node -> {
			builder.append(node.title);
			builder.append("/");
		});
		if (builder.length() > 0) {
			builder.insert(0, "/");
		}
		return builder.toString();
	}

	/**
	 * @return
	 */
	public String toStringFromCurrentPosition() {
		StringBuilder builder = new StringBuilder();
		this.path.subList(this.index, this.path.size() - 1).forEach(node -> {
			builder.append(node.title);
			builder.append("/");
		});
		if (builder.length() > 0) {
			builder.insert(0, "/");
		}
		return builder.toString();
	}
}
