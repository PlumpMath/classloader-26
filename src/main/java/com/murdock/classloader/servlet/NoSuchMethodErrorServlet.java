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

import org.springframework.beans.factory.BeanFactoryUtils;

/**
 * @author weipeng2k 2015年3月31日 上午9:09:58
 */
@WebServlet(name = "NoSuchMethodErrorServlet", urlPatterns = { "/noSuchMethodError.do" })
public class NoSuchMethodErrorServlet extends HttpServlet {

	private static final long	serialVersionUID	= 1699609060417354821L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BeanFactoryUtils.isGeneratedBeanName("xxx");
		
		resp.getWriter().println("done.");
	}
	
}
