package user.test;

import com.toleey.smbms.dao.user.UserDao;
import com.toleey.smbms.dao.user.impl.UserDaoImpl;
import com.toleey.smbms.entity.User;
import com.toleey.smbms.service.user.UserService;
import com.toleey.smbms.util.ApplicationContextUtil;
import org.junit.Test;

import java.util.List;

public class UserTest {

    @Test
    public void testUserList(){

        User user = new User();
        user.setUserName("孙");
        user.setUserRole(3);

        //UserDao userDao = new UserDaoImpl();
        //List<User> userList = userDao.getUserListByCondition(user);

        UserService userService = (UserService) ApplicationContextUtil.getApplicationContext().getBean("userService");
        List<User> userList = userService.findUserListByCondition(user);
        System.out.println(userList);
    }
}
