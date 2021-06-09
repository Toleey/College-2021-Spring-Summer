package cn.bw.springbootmybatisjsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(basePackages = "cn.bw.*.*")
public class SpringbootMybatisJspApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisJspApplication.class, args);
    }

}
