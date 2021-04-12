package org.bw.smbms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author 甘霖 用单例模式读取数据库配置文件属性值
 */
public class DBConfigure {
	/* private static DBConfigure dbConfigure =new DBConfigure() ; */
	private static Properties properties;

	private DBConfigure() {
		// 读取配置文件
		InputStream inputStream = DBConfigure.class.getClassLoader().getResourceAsStream("database.properties");
		// 流里的数据转化成建值对
		properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获得数据库配置信息的对象-懒汉模式
	/*
	 * public static DBConfigure getInstance() { if (dbConfigure==null) {
	 * dbConfigure=new DBConfigure(); } return dbConfigure;
	 * 
	 * }
	 */
	/*
	 * //把数据库配置信息的属性拿到-饿汉模式 public static DBConfigure getDBProperty(String key) {
	 * return dbConfigure; }
	 */
	// 单例模式的静态内部类模式
	private static class MyInstance{
		private final static DBConfigure DB_CONFIGURE=
				new DBConfigure();
	}
	public static DBConfigure getInstance() {
		return MyInstance.DB_CONFIGURE;
	}

	// 把数据库配置信息的属性拿到
	public String getDBProperty(String key) {
		return getInstance().properties.getProperty(key);
	}
	
}
