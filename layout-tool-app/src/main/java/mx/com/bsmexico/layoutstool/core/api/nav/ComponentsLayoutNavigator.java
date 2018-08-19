package mx.com.bsmexico.layoutstool.core.api.nav;

import java.util.ArrayList;
import java.util.List;

import mx.com.bsmexico.layoutstool.core.api.ComponentLayout;

/**
 * 
 * 
 * @author jchr
 *
 */
public abstract class ComponentsLayoutNavigator extends Navigator {

	private List<ComponentLayout> components;

	/**
	 * @param components
	 * @throws IllegalArgumentException
	 */
	public ComponentsLayoutNavigator(final List<ComponentLayout> components) throws IllegalArgumentException {
		if (components == null || components.isEmpty()) {
			throw new IllegalArgumentException("List components can not be empty or null");
		}
		this.components = components;
		final List<NavRoute> routes = new ArrayList<>();
		this.components.forEach(cmp -> {
			if (cmp.getLayout() != null && cmp.getLayout().getRoute() != null) {
				routes.add(cmp.getLayout().getRoute());
			}
		});
		builLogicNavigationTree(routes);
		bindComponents();
	}

	/**
	 * Bind a ComponentLayout to Tree Nodes
	 */
	private void bindComponents() {
		if (this.root != null) {
			components.forEach(cmp -> bindComponent(cmp));
		}
	}

	/**
	 * @param component
	 */
	private void bindComponent(final ComponentLayout component) {
		if (components != null && component.getLayout() != null && component.getLayout().getRoute() != null) {
			final NavRoute route = component.getLayout().getRoute();			
			List<TreeNavNode> branch = this.getBranchByRoute(root, route);
			if(!branch.isEmpty()) {
				//Bind component to leaf of the branch
				branch.get(branch.size() -1).bind(component);
			}
			
		}
	}
}
