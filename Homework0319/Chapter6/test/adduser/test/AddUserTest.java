package adduser.test;

import org.apache.log4j.Logger;
import org.bw.aopextend.demo1.dao.UserDao;
import org.bw.aopextend.demo1.entity.User;
import org.bw.aopextend.demo1.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AddUserTest {
    private static Logger logger = Logger.getLogger("AddUserTest.class");

    @Test
    public void testAddUser(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService us = (UserService) ac.getBean("userService");
        User user3 = (User)ac.getBean("user3");
        us.addAUser(user3);
    }
}
