package net.lampbrother.java.share.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 得到method参数值
		String methodName = req.getParameter("method");
			Method method;
			try {
				method = this.getClass().getMethod(methodName,
						HttpServletRequest.class, HttpServletResponse.class);
				method.invoke(this, req, resp);
			} catch (Exception e) {//InvocationTargetException
				e.printStackTrace();
			}
			
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
