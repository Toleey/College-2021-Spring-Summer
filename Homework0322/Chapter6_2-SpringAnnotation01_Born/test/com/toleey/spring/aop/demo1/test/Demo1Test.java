package com.toleey.spring.aop.demo1.test;

import com.toleey.spring.aop.demo1.entity.User;
import com.toleey.spring.aop.demo1.service.UserService;
import com.toleey.spring.aop.demo1.service.impl.UserServiceImpl;
import org.junit.Test;

public class Demo1Test {
	
	@Test
	public void testDemo1() {
		User user = new User(1,"哈哈");
		UserService us = new   UserServiceImpl();
		us.addUser(user);
	}

}
