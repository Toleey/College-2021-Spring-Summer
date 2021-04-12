package com.toleey.smbms.dao;

import com.toleey.smbms.util.DBConfigure;

import java.sql.*;

/**
 * jdbc 进行数据库连接和操作
 */

public class BaseDao {
    private static String driver = DBConfigure.getInstance().getDBProperities("driver");
    private static String url = DBConfigure.getInstance().getDBProperities("url");
    private static String username = DBConfigure.getInstance().getDBProperities("username");
    private static String password = DBConfigure.getInstance().getDBProperities("password");

    //1.加载驱动 加载一次，放到静态块里
    static{
        try {
            Class.forName(driver);//根据名字来加载类
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //2.建立连接
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
    //3.数据库表的操作
    //(1).查询操作
    public static ResultSet executeQuery(Connection conn,String sql,Object ...params){
        PreparedStatement prep = null; //初始化
        ResultSet rst = null;
        try {
            prep = conn.prepareStatement(sql);
            for (int i=0;i<params.length;i++){
                prep.setObject(i+1,params[i]);
            }
            rst=prep.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rst;//不能关，因为要返回结果集
    }
    //(2).增删改操作
    public static int executeUpdate(Connection conn,String sql,Object ...params){
        PreparedStatement prep = null;
        int line = 0;
        try {
            prep=conn.prepareStatement(sql);
            for (int i = 0;i<params.length;i++){
                prep.setObject(i+1,params[i]);
            }
            line=prep.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return line;//可以关，有缺陷，查询的时候，关不了
    }
    //4.数据库关闭操作
    public static void close(Connection conn,PreparedStatement prep,ResultSet rst){
        if (rst!=null){
            try {
                rst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(prep!=null){
            try {
                prep.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
