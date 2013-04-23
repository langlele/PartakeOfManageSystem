package net.lampbrother.java.share.web.filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.lampbrother.java.share.exception.DaoException;
import net.lampbrother.java.share.util.ConnectionContext;
import net.lampbrother.java.share.util.JDBCUtils;

/**
 * Servlet Filter implementation class ConnectionFilter
 */
public class TransactioinFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TransactioinFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * 
	 * 实现的是一个浏览器端发过来的请求用一个Connection对象
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//生成一个Connection
		Connection conn = null;
		try {
			conn = ConnectionContext.getConnection(); //可能是缓存的，也可能是从连接池中获取的
			System.out.println("ConnectionFilter conn="+conn);
			
			//开启事务
			JDBCUtils.beginTransaction(conn);
			System.out.println("开启事务");
			chain.doFilter(request, response);
			
			//提交事务
			JDBCUtils.commitTransaction(conn);
			System.out.println("提交事务");
			
		} catch (DaoException e) {
			e.printStackTrace();
			//回滚事务
			JDBCUtils.rollbackTransaction(conn);
			System.out.println("回滚事务");
			//转向到一个错误页面去显示错误信息
			request.setAttribute("message", "数据库操作出错了！");
			request.getRequestDispatcher("/WEB-INF/pages/result.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			//转向到一个错误页面去显示错误信息
			request.setAttribute("message", "操作出错了！");
			request.getRequestDispatcher("/WEB-INF/pages/result.jsp").forward(request, response);
		} finally {
			//在请求返回时
			System.out.println("释放连接！");
			ConnectionContext.removeConn();
			JDBCUtils.releaseConn(conn);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
