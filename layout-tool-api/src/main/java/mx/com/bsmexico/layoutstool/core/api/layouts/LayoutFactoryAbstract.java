package mx.com.bsmexico.layoutstool.core.api.layouts;

import java.io.File;

import mx.com.bsmexico.layoutstool.core.api.layouts.model.Layout;

/**
 * @author jchr
 *
 */
public abstract class LayoutFactoryAbstract {

	protected File layout;

	protected LayoutFactoryAbstract(final File layout) throws IllegalArgumentException {
		if (layout == null || !layout.exists() || !layout.canRead() && !layout.isFile()) {
			throw new IllegalArgumentException("The file does not exist or can not be accessed");
		}
		this.layout = layout;
	}

	/**
	 * Get Layout instance
	 * 
	 * @return
	 */
	public abstract Layout getLayoutInstance() throws Exception;
}