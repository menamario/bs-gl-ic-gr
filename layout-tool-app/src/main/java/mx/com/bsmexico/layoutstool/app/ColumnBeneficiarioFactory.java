package mx.com.bsmexico.layoutstool.app;

import mx.com.bsmexico.layoutstool.core.api.layouts.ColumnTableFactoryAbstract;

/**
 * @author jchr
 *
 */
public class ColumnBeneficiarioFactory extends ColumnTableFactoryAbstract<Beneficiario> {

	public ColumnBeneficiarioFactory() throws IllegalArgumentException {
		super(Beneficiario.class);
	}
}
