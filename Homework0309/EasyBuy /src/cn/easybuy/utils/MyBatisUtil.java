package cn.easybuy.utils;
/**
 * 获得mybatis和数据库交互的SqlSession对象
 * @author lyonb
 *
 */

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	//SQLSessionFactory作为生命周期应用程序级属性
	private static SqlSessionFactory factory;
	//SQLSessionFactory只需要一个
	static {
		//获得主配置文件的对象
		InputStream input;
		try {
			input = Resources.getResourceAsStream("mybatis-config.xml");
			factory=new SqlSessionFactoryBuilder().build(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//创建和数据库交互的SQLSession,默认非自动提交事务
	public static SqlSession createSQLSession() {
		
		return factory.openSession();
	}
	
}
