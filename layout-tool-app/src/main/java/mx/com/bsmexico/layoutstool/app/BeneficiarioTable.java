package mx.com.bsmexico.layoutstool.app;

import mx.com.bsmexico.layoutstool.core.api.layouts.ColumnTableFactoryAbstract;
import mx.com.bsmexico.layoutstool.core.api.layouts.LayoutFactoryAbstract;
import mx.com.bsmexico.layoutstool.core.api.layouts.LayoutTable;

public class BeneficiarioTable extends LayoutTable<Beneficiario> {

	private final int INITIAL_CAPACITY = 10;

	public BeneficiarioTable(final LayoutFactoryAbstract layoutFactory,
			final ColumnTableFactoryAbstract<Beneficiario> columnFactory)
			throws IllegalArgumentException, InstantiationError {
		super(layoutFactory, columnFactory);

	}

	protected void polulate() {
		for (int idx = 0; idx <= INITIAL_CAPACITY; idx++) {
			this.data.add(new Beneficiario());
		}
	}

}
