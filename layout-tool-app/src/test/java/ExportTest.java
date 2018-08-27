import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mx.com.bsmexico.layoutstool.app.Beneficiario;
import mx.com.bsmexico.layoutstool.app.BeneficiariosExport;

public class ExportTest {

	@Test
	public void exportCsvTest() {
		BeneficiariosExport exporter = new BeneficiariosExport();
		List<Beneficiario> beneficiarios = new ArrayList<>();
		for(int idx =0 ; idx< 50; idx++) {
			beneficiarios.add(getBeneficiario());
		}
		try {
			exporter.export(beneficiarios);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private Beneficiario getBeneficiario() {
		final Beneficiario ben = new Beneficiario();
		ben.setApellidoMaterno("AAAAA");
		ben.setApellidoPaterno("DDDD");
		ben.setBancoParticipante("CCCCC");
		ben.setCuenta("BBBBB");
		ben.setImporteMaximo("00.00");
		ben.setMoneda("MXN");
		ben.setNombre("JJJJJJJ");
		ben.setNumLinea("0000000000");
		ben.setRazonSocial("AAAAA");
		ben.setTipoCuenta("00");
		ben.setTipoPersona("01");
		return ben;
	}
}

