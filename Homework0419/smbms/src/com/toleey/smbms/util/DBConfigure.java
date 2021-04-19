package com.toleey.smbms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author toby
 * 用单例模式读取数据库配置文件属性值
 *	读配置文件（IO操作）的时候用单例模式
 */
public class DBConfigure {
	//private static DBConfigure dbConfigure; 懒汉模式
	//private static DBConfigure dbConfigure = new DBConfigure(); //饿汉模式 类一加载就产生
 	private static Properties properties;
	
	private DBConfigure() {
		//读取配置文件
		InputStream inputStream = DBConfigure.class.getClassLoader().getResourceAsStream("database.properties");
		//流里的数据转化成健值对
		properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//获得数据库配置信息的对象-懒汉模式 延迟加载 但影响效率
//	public synchronized static DBConfigure getInstance(){ //synchronized 不能同时一起执行
//		if (dbConfigure==null){
//			dbConfigure=new DBConfigure();
//		}
//		return dbConfigure;

	//获得数据库配置信息的对象-饿汉模式 天生线程安全，类一加载就初始化了
//	public static DBConfigure getInstance(){
//		return dbConfigure;
//	}

	//单例模式的静态内部类模式 延迟加载+线程安全 两个都有
	private static class MyInstance{
		private final static DBConfigure DB_CONFIGURE=new DBConfigure();
	}
	public static DBConfigure getInstance(){
		return MyInstance.DB_CONFIGURE;
	}
	//把数据库配置信息的属性拿到
	public String getDBProperities(String key){
		return getInstance().properties.getProperty(key);
	}

}
