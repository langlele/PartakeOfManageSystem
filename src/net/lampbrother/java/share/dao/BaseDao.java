package net.lampbrother.java.share.dao;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.lampbrother.java.share.exception.DaoException;
import net.lampbrother.java.share.util.ConnectionContext;
import net.lampbrother.java.share.util.JDBCUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/*
 * 所有dao类的基类
 * 利用dbutils包封装查询结果数据,整成对象或对象的集合
 * 	QueryRunner
 * 		BeanListHandler
 * 		BeanHandler
 * 
 * 		ArrayHandler 
 * 		ScalarHandler
 * 
 */
public abstract class BaseDao<T> {

	private static QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;//什么时候可以确定它的值
	
	/*
	 * 
	 */
	public BaseDao() {
		Class c = this.getClass();//得到的是实现子类的class对象
		System.out.println(c);//class net.lampbrother.java.bookstore.dao.impl.BookDaoImpl
		ParameterizedType type = (ParameterizedType) c.getGenericSuperclass();
		System.out.println(type);//BaseDao<net.lampbrother.java.bookstore.domain.Book>
		clazz = (Class<T>) type.getActualTypeArguments()[0];
		System.out.println(clazz);//class net.lampbrother.java.bookstore.domain.Book
	}
	
	/*
	 * 向表中插入一条数据，得到自动生成的主键
	 */
	public long insert(String sql, Object...params){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = ConnectionContext.getConnection();
		try {
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for(int i=0;i<params.length;i++) {
				ps.setObject(i+1, params[i]);
			}
			ps.execute();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				return (long) rs.getObject(1);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			JDBCUtils.closeRS(rs,ps);
		}
		return -1;
	}
	
	/*
	 * 表数据的添加，删除和更新
	 */
	public void update(String sql, Object...params ) {
		Connection conn = ConnectionContext.getConnection();
		try {
			queryRunner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/*
	 * 查询得到多条记录，返回List<T>
	 * BeanListHandler  得到一个对象的集合
	 */
	public List<T> queryForList(String sql, Object...params ) {
		List<T> list = new ArrayList<T>();
		Connection conn = ConnectionContext.getConnection();
		System.out.println("BaseDao conn="+conn);
		//从当前线程对象中取出Connection对象
		
		try {
			list = queryRunner.query(conn, sql, new BeanListHandler<T>(clazz), params);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
		return list;
	}
	
	/*
	 * 查询Group，返回List<String>
	 * BeanListHandler  得到一个对象的集合
	 */
	public List<Object[]> queryGroupForList(String sql, Object...params ) {
		List<Object[]> list ;
		Connection conn = ConnectionContext.getConnection();
		System.out.println("BaseDao conn="+conn);
		//从当前线程对象中取出Connection对象
		
		try {
			list = queryRunner.query(conn, sql, new ArrayListHandler(), params);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
		return list;
	}
	
	/*
	 * 查询得到一条记录，返回一个对象T
	 * BeanHandler 
	 */
	public T query(String sql, Object...params ) {
		T t = null;
		Connection conn = ConnectionContext.getConnection();
		try {
			t = queryRunner.query(conn, sql, new BeanHandler<T>(clazz), params);
			
			/*
			 * 	propertys  实体类的所有属性名
			 *  columns : 表的所有字段
			 * for(columns c : columns) {
			 * 		for(String property : propertys) {
			 * 			if(c.equalsIgnorse(property) {
			 * 				Object value = rs.getObject(c);
			 * 				//通过反射将value注入到bean的property中去
			 * 			}
			 * 		//结论：实体类的属性个数可以不与对应表的字段个数一致， 可多可少
			 * }
			 */
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return t;
	}
	
	/*
	 * 查询得到一个单一的数据
	 */
	@SuppressWarnings("unchecked")
	public <K> K getSingleValue(String sql, Object...params) {
		K k = null;
		Connection conn = ConnectionContext.getConnection();
		try {
			 k = (K) queryRunner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return k;
	}
	
	/*
	 * 批量执行
	 */
	
	public void batch(String sql, Object[]... params) {
		
		Connection conn = ConnectionContext.getConnection();
		try {
			queryRunner.batch(conn, sql, params);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}





