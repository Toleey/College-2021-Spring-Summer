package cn.bw.springbootjunitjsp.test;

import cn.bw.springbootjunitjsp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testUserService {
    @Autowired
    private UserService userService;

    @Test
    public void testAdd(){
        userService.add();
    }
}
