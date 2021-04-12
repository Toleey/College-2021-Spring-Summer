package org.bw.smbms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bw.smbms.util.DBConfigure;

/**
 * jdbc进行数据库连接和操作
 * @author 甘霖
 *
 */
public class BaseDao {
	private static String driver=
			DBConfigure.getInstance().getDBProperty("driver");
	private static String url=
			DBConfigure.getInstance().getDBProperty("url");
	private static String userName=
			DBConfigure.getInstance().getDBProperty("username");
	private static String password=
			DBConfigure.getInstance().getDBProperty("password");

	//1、加载驱动
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//2、建立连接
	public static Connection getConnection() {
		Connection con=null;
		try {
			con=DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	//3、数据库表的操作
	//(1)查询操作
	public static ResultSet executeQuery(
			Connection conn,String sql,Object ... params) {
		PreparedStatement prep;
		ResultSet rst=null;
		try {
			prep=conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				prep.setObject(i+1, params[i]);
			}
			rst=prep.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rst;
		
	}
	//(2)增删改操作
	public static int executeUpdate(
			Connection conn,
			String sql,
			Object ...params
			) {
		PreparedStatement prep=null;
		int line=0;
		try {
			prep=conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				prep.setObject(i+1, params[i]);
			}
			line=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
	//4、关闭jdbc对象
	public static void close(
			Connection conn
   		    ,PreparedStatement prep
   		    ,ResultSet rst
			) {
		if(rst!=null) {
			try {
				rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(prep!=null) {
			try {
				prep.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}







}