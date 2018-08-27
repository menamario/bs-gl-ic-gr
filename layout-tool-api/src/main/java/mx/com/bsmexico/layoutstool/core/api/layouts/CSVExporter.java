package mx.com.bsmexico.layoutstool.core.api.layouts;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * @author jchr
 *
 * @param <T>
 */
public abstract class CSVExporter <T> implements Exporter<T>{
	final SimpleDateFormat fd = new SimpleDateFormat("YYYY-MM-dd:HH:mm:ss");
	/* (non-Javadoc)
	 * @see mx.com.bsmexico.layoutstool.core.api.layouts.Exporter#export()
	 */
	@Override
	public void export(final List<T> records) throws Exception{		
		Writer writer = Files.newBufferedWriter(Paths.get("beneficiarios_"+fd.format(new Date())+".csv"));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(getHeader()));        
        if(records != null) {
        	records.forEach(r ->{
        		try {
					csvPrinter.printRecord(getRecord(r));
				} catch (IOException e) {
					//do nothig
				}
        	});
        }
        csvPrinter.flush();
        csvPrinter.close();		
	}

	/**
	 * @param obj
	 * @return
	 */
	protected abstract Object[] getRecord(T obj);
	
	/**
	 * @return
	 */
	protected abstract String[] getHeader();
}
