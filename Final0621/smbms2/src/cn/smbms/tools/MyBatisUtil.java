package cn.smbms.tools;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {

    //声明一个唯一生命周期是应用级的变量SqlSessionFactory
    private static SqlSessionFactory sessionFactory;

    static {
        //mybatis读取主配置文件
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建SqlSession，该sqlsession事务管理使用的是非自动提交事务
    //业务层调用
    public static SqlSession createSession() {
        return sessionFactory.openSession();
    }

    //在业务层里方法里用完Sqlsession，别忘了关闭  加static才能调用关闭
    public static void closeSqlSession(SqlSession session) {
        if(session!=null) {
            session.close();
        }
    }
}
