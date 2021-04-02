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

    @Test
    public void addAUserTest(){
        User user = new User();
        user.setUserCode("as");
        user.setUserName("是的");
        user.setUserPassword("123456");
        user.setGender(1);
        //user.setBirthday();
        user.setPhone("12312341234");
        user.setAddress("被第三");
        user.setUserRole(2);
        user.setCreatedBy(1);
        //user.setCreationDate();


        UserService userService = (UserService) ApplicationContextUtil.getApplicationContext().getBean("userService");
        Integer result = userService.addAUser(user);
        if (result.equals(0)){
            System.out.println("添加失败");
        }else{
            System.out.println("添加成功:"+user);
        }
    }

    @Test
    public void updateAUserTest(){
        User user = new User();
        user.setId(26);
        user.setUserCode("12s");
        user.setUserName("哈哈");
        user.setUserPassword("12sd56");
        user.setGender(2);
        user.setPhone("123423431234");
        user.setAddress("被阿斯顿三");
        user.setUserRole(1);
        user.setCreatedBy(2);

        UserService userService = (UserService) ApplicationContextUtil.getApplicationContext().getBean("userService");
        Integer result = userService.updateAUser(user);
        if (result.equals(0)){
            System.out.println("添加失败");
        }else{
            System.out.println("添加成功");
        }
    }

    @Test
    public void deleteAUserTest(){

        Integer id = 28;
        UserService userService = (UserService) ApplicationContextUtil.getApplicationContext().getBean("userService");
        Integer result = userService.deleteAUser(id);
        if (result.equals(0)){
            System.out.println("删除失败");
        }else{
            System.out.println("删除成功");
        }
    }
}
