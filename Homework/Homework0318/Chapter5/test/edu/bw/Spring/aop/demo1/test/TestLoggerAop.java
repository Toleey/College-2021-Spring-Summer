package edu.bw.Spring.aop.demo1.test;

import edu.bw.Spring.aop.demo1.entity.User;
import edu.bw.Spring.aop.demo1.service.UserService;
import edu.bw.Spring.aop.demo2.entity.Phone;
import edu.bw.Spring.aop.demo2.service.PhoneService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestLoggerAop {

    @Test
    public void testLogger(){
        ApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
       //获得实体User
       User user =  (User)cxt.getBean("User");
       //获得实体UserService对象
       UserService userService = (UserService) cxt.getBean("UserService");

       userService.addNewUser(user);
    }

    @Test
    public void testLogger2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Phone phone = (Phone) ac.getBean("phone");
        PhoneService ps = (PhoneService) ac.getBean("PhoneService");
        ps.addNewPhone(phone);

    }
}
