package dao.userMapper;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;
import org.junit.Test;

import javax.annotation.Resource;

public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Test
    public void testGetUserByUserCode(){
        User user = userMapper.getUserByUserCode("admin");
        System.out.println(user);
    }
}
