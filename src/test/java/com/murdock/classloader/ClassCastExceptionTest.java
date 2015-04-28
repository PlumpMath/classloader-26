/**
 * 
 */
package com.murdock.classloader;

import java.io.File;
import java.net.URL;
import java.util.Observer;

import org.apache.mina.core.buffer.IoBuffer;
import org.junit.Test;

/**
 * @author weipeng2k 2015年3月30日 下午2:37:27
 */
public class ClassCastExceptionTest {
	@Test
	public void classCastException() throws Exception {
		CachedClassLoader cl = new CachedClassLoader(new URL[] {
				new File("/Users/weipeng2k/Documents/arena/jarviewer/target/jarviewer-1.0-SNAPSHOT.jar").toURI()
						.toURL(),
				new File("/Users/weipeng2k/.m2/repository/org/apache/mina/mina-core/2.0.7/mina-core-2.0.7.jar").toURI()
						.toURL() });

		Class<?> clazz = cl.loadClass("com.murdock.book.jarviewer.classcast.ClassCastException");
		Observer obj = (Observer) clazz.newInstance();
		IoBuffer buf = IoBuffer.allocate(4);
		obj.update(null, buf);
		cl.close();
	}
}
