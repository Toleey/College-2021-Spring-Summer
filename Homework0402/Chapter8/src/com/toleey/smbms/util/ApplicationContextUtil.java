package com.toleey.smbms.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil {

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext(){
        if (context == null){
            context = new ClassPathXmlApplicationContext("applicationContext.xml");
        }
        return context;
    }
}
