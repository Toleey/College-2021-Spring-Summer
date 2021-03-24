package demo1.test;

import org.apache.log4j.Logger;
import org.bw.aopextend.demo1.entity.User;
import org.bw.aopextend.demo1.service.UserService;
import org.bw.aopextend.demo3.TestDifTypeValue;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;


public class TestDemo1 {

    private static Logger logger = Logger.getLogger("TestDemo1.class");
    @Test
    public void testDemo1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        User user= (User)ctx.getBean("user1");
//        logger.info(user);

        User user2= (User)ctx.getBean("user2");
        logger.info(user2);

//        try {
//            User user = (User) Class.forName("org.bw.aopextend.demo1.entity.User").newInstance();
//            logger.info(user);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        long startTime = System.currentTimeMillis(); //获取开始时间

        //测试不同种类的属性ioc赋值
        TestDifTypeValue test = (TestDifTypeValue) ctx.getBean("testDigTypeValue");
        logger.info(test);

        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间

        System.out.println("————————————————————————————");
        //异常增强
        UserService userService = (UserService) ctx.getBean("userService2");
        userService.addAUser(user2);
        
        
    }
    
    
    
    


}
