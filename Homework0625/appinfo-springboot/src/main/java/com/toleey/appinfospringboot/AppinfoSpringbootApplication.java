package com.toleey.appinfospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.toleey")
@SpringBootApplication
public class AppinfoSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppinfoSpringbootApplication.class, args);
    }

}
