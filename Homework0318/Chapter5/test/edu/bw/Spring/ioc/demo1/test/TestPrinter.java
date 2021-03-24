package edu.bw.Spring.ioc.demo1.test;

import edu.bw.Spring.ioc.demo1.Printer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPrinter {

    @Test
    public void testPrinter(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        Printer printer = (Printer) ac.getBean("printer");
        printer.print();
    }
}
