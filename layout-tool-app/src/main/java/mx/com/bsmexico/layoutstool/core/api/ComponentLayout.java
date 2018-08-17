package mx.com.bsmexico.layoutstool.core.api;

import mx.com.bsmexico.layoutstool.core.api.nav.NavRoute;

/**
 * All class that implements this interface can be a visual and functional part
 * in the Application.
 * 
 * @author jchr
 *
 */
public interface ComponentLayout {

	/**
	 * Get the layout of this component to show it within of the application
	 * 
	 * @return the region (Axis, Chart, Control, Pane) to show in the Application
	 */
	Layout getLayout();

	/**
	 * Get the route information to anchor the component to some Navigator
	 * {@link mx.com.bsmexico.layoutstool.core.api.nav.AppNavigator}
	 */
	NavRoute getRoute();
}
