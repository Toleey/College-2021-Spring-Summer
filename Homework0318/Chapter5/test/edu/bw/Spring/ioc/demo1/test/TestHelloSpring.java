package edu.bw.Spring.ioc.demo1.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.bw.Spring.ioc.demo1.HelloSpring;

public class TestHelloSpring {
	
	@Test
	public void TestHelloSPring() {
		//1.没有Spring的时候
		//(1).实例化一个对象
		HelloSpring hs = new HelloSpring();
		//(2).给对象属性赋值
		hs.setWord("Hello,World!");
		//(3).调用say方法
		hs.say();
		
		//2.使用Spring的时候，配置文件
		//(1)先得到applicationContext.xml配置文件
		ApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		//(2)获得HelloSpring对象 因为可以获得任何Bean，所以需要
		HelloSpring hs2 = (HelloSpring)cxt.getBean("hello");
		//(3)调用say方法
		hs2.say();
		
		
	}

}
