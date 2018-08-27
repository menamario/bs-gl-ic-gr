package mx.com.bsmexico.layoutstool.core.api.layouts;

import java.util.List;

/**
 * @author jchr
 *
 */
public interface Exporter<T> {

	void export(final List<T> records) throws Exception;
}
