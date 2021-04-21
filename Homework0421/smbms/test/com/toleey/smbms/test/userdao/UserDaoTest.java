package com.toleey.smbms.test.userdao;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.user.UserDao;
import com.toleey.smbms.dao.user.impl.UserDaoImpl;
import com.toleey.smbms.entity.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class UserDaoTest {

//    @Test
//    public void testGetUserListByNameAndRole(){
//        Connection conn = BaseDao.getConnection();
//        UserDao userDao = new UserDaoImpl();
//        List<User> userList =  userDao.getUserListByNameAndRole(conn,"T",2,0,10);
//        for (User user : userList) {
//			System.out.println(user);
//		}
//    }
//    
//    @Test
//    public void testGetUserCountByNameAndRole() {
//    	 Connection conn = BaseDao.getConnection();
//    	 UserDao userDao = new UserDaoImpl();
//    	 int line = userDao.getUserCountByNameAndRole(conn, "T", 2);
//    	 System.out.println(line);
//    }

    @Test
    public void testUpdateUser(){
        Connection conn = BaseDao.getConnection();
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setId(34);
        user.setUserName("haha");
        user.setGender(1);
        user.setBirthday(new Timestamp(new Date().getTime()));
        user.setPhone("13012341234");
        user.setAddress("上海");
        user.setUserRole(2);
        user.setModifyBy(2);
        user.setModifyDate(new Timestamp(new Date().getTime()));
        int line = userDao.updateUserById(conn,user);
        System.out.println(line);
    }
}
