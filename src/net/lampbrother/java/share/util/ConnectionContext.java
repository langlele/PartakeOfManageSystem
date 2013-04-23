package net.lampbrother.java.share.util;

import java.sql.Connection;

/*
 * 从连接池中获取Connection
 * 缓存Connection
 * 	缓存是怎么使用的：
 * 		1 众缓存中取出？
 * 			有--》直接返回
 * 			没有--》创建 一个，缓存起来，返回
 */
public class ConnectionContext {

	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	
	public static Connection getConnection() {
		Connection conn = null;
		//从连接池中取出Connection
		conn = local.get();
		if(conn==null) {
			conn = JDBCUtils.getConnection();
			local.set(conn);
		}
		return conn;
	}
	
	public static void removeConn() {
		local.remove();
	}
}
