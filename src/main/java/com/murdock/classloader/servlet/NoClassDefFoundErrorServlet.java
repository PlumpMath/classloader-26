/**
 * 
 */
package com.murdock.classloader.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;

/**
 * @author weipeng2k 2015年3月27日 下午5:15:15
 */
@WebServlet(name = "NoClassDefFoundErrorServlet", urlPatterns = "/noClassDefFoundError.do")
public class NoClassDefFoundErrorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 61585757018374721L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(TestCase.class.toString());
	}

}
