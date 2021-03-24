package com.toleey.spring.aop.demo1.test;

import com.toleey.spring.aop.demo1.entity.User;
import com.toleey.spring.aop.demo1.service.UserService;
import com.toleey.spring.aop.demo1.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo1Test {
	
	@Test
	public void testDemo1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

		User user = (User) ac.getBean("user");
		user.setId(1);
		user.setName("哈哈");
		UserService us = (UserService) ac.getBean("userService");
		us.addUser(user);
	}

}
