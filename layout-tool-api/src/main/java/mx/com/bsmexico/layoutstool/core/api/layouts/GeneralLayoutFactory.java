package mx.com.bsmexico.layoutstool.core.api.layouts;

import java.io.File;

import javax.xml.bind.JAXBException;

import mx.com.bsmexico.layoutstool.core.api.layouts.bind.GeneralLayoutBinding;
import mx.com.bsmexico.layoutstool.core.api.layouts.model.Layout;

/**
 * 
 * @author jchr
 *
 */
public class GeneralLayoutFactory extends LayoutFactoryAbstract{
	private GeneralLayoutBinding binding;

	/**
	 * Constructor that creates Layout instances from a file xml (instance of
	 * generalLayout.xsd)
	 * 
	 * @param layout
	 * @param schmValidation
	 * @throws InstantiationError
	 * @throws IllegalArgumentException
	 */
	public GeneralLayoutFactory(final File layout, final boolean schmValidation)
			throws InstantiationError, IllegalArgumentException {
		super(layout);
		try {
			binding = new GeneralLayoutBinding();
			if (schmValidation) {
				binding.setValidating(true);
			}
		} catch (JAXBException exception) {
			throw new InstantiationError(exception.getMessage());
		}
	}

	/**
	 * Get Layout instance
	 * 
	 * @return
	 */
	@Override
	public synchronized Layout getLayoutInstance() throws Exception {
		Layout result = null;
		try {
			result = binding.unmarshall(layout);
		} catch (JAXBException exception) {
			throw new Exception(exception);
		}
		return result;
	}

}
