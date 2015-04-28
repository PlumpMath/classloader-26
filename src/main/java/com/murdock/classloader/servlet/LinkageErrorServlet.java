/**
 * 
 */
package com.murdock.classloader.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mina.filter.codec.ProtocolEncoder;

import com.murdock.classloader.CachedClassLoader;

/**
 * @author weipeng2k 2015年4月5日 下午9:50:01
 */
@WebServlet(name = "LinkageErrorServlet", urlPatterns = "/linkageError.do")
public class LinkageErrorServlet extends HttpServlet {
	private static final long	serialVersionUID	= -2951763301762511785L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CachedClassLoader cl = new CachedClassLoader(
				new URL[] { new File(
						"/Users/weipeng2k/.m2/repository/org/apache/mina/mina-core/2.0.7/mina-core-2.0.7.jar").toURI()
						.toURL() }, this.getClass().getClassLoader());

		try {
			Class<?> klass = cl.loadClass("org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory");
			Method method = null;
			for (Method m : klass.getMethods()) {
				if (m.getName().equals("getEncoder")) {
					method = m;
					break;
				}
			}

			ProtocolEncoder pe  = (ProtocolEncoder) method.invoke(klass.newInstance(), new Object[] { null });

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			cl.close();
		}

	}
}
