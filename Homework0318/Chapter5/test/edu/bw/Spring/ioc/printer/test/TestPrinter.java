package edu.bw.Spring.ioc.printer.test;

import edu.bw.Spring.ioc.printer.Printer;
import edu.bw.Spring.ioc.printer.ink.ColorInk;
import edu.bw.Spring.ioc.printer.paper.A4Paper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPrinter {
    @Test
    public void testPrinter(){
        //测试打印机
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Printer printer = (Printer) ac.getBean("printer2");
        printer.print();
    }
}

//        Printer printer = (Printer) ac.getBean("a4Colorprinter");
