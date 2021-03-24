package peoplesay.test;

import org.apache.log4j.Logger;
import org.bw.aopextend.demo1.entity.PeopleSay;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PeopleSayTest {

    private static Logger logger = Logger.getLogger("PeopleSayTest.class");

    @Test
    public void testPeopleSay(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PeopleSay ps = (PeopleSay) ctx.getBean("peopleSay");
        logger.info(ps);
    }

    @Test
    public void testPeopleSay2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PeopleSay ps = (PeopleSay) ctx.getBean("peopleSay2");
        logger.info(ps);
    }
}
