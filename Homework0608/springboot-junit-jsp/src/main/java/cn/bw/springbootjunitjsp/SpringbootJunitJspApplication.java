package cn.bw.springbootjunitjsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cn.bw.*.*")
public class SpringbootJunitJspApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJunitJspApplication.class, args);
    }

}
