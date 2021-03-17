package edu.bw.Spring.ioc.demo1.test;

import edu.bw.Spring.ioc.demo1.PeopleSay;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPeopleSay {
    @Test
    public void testPeopleSay(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        PeopleSay ps = (PeopleSay) ac.getBean("peopleSay");
        ps.say();

        PeopleSay ps2 = (PeopleSay) ac.getBean("peopleSay2");
        ps2.say();

    }

}
