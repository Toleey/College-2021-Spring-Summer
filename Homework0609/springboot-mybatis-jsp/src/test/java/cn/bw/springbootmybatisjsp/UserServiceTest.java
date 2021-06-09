package cn.bw.springbootmybatisjsp;

import cn.bw.springbootmybatisjsp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void testFindUserCount() {
        System.out.println("用户人数"+userService.findUserCount());

    }

}

