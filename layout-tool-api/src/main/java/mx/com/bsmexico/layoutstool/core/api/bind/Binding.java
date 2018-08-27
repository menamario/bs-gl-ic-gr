package mx.com.bsmexico.layoutstool.core.api.bind;

import java.io.File;
import java.io.OutputStream;

/**
 * @author jchr
 *
 * @param <T>
 */
public interface Binding<T> {
	/**
	 * @param file that will be converted to Java object
	 * @return
	 * @throws Exception
	 */
	T unmarshall(final File file) throws Exception;

	/**
	 * @param obj    Object that will be converted to data format suitable for
	 *               storage or transmission.
	 * @param output XML will be added to this stream
	 * @throws Exception
	 */
	void marshall(T obj, OutputStream output) throws Exception;
}
