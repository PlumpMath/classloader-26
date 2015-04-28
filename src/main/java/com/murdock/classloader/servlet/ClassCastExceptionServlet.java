package com.murdock.classloader.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mina.proxy.utils.MD4;

import com.murdock.classloader.CachedClassLoader;

/**
 * @author weipeng2k 2015年4月4日 下午6:00:54
 */
@WebServlet(name = "ClassCastExceptionServlet", urlPatterns = "/classCastException.do")
public class ClassCastExceptionServlet extends HttpServlet {
	private static final long	serialVersionUID	= -8959000121057369987L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String localFirst = req.getParameter("localFirst");
		CachedClassLoader cl = null;
		cl = new CachedClassLoader(
				new URL[] { new File(
						"/Users/weipeng2k/.m2/repository/org/apache/mina/mina-core/2.0.7/mina-core-2.0.7.jar").toURI()
						.toURL() }, this.getClass().getClassLoader());
		if ("false".equals(localFirst)) {
			cl.setLocalFirst(false);
		}
		try {
			Class<?> klass = cl.loadClass("org.apache.mina.proxy.utils.MD4");
			MD4 md4 = (MD4) klass.newInstance();

			resp.getWriter().println(md4);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			cl.close();
		}

	}
}
