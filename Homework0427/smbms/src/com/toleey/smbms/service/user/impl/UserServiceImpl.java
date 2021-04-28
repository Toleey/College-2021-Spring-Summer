package com.toleey.smbms.service.user.impl;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.user.UserDao;
import com.toleey.smbms.entity.User;
import com.toleey.smbms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired()
    @Qualifier("userDao")
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserByUserCode(String userCode) {
        Connection conn = BaseDao.getConnection();
        User user = userDao.getUserByUserCode(conn,userCode);
        BaseDao.close(conn,null,null);
        return user;
    }

    @Override
    public List<User> findUserListByNameAndRole(String userName, Integer roleId, Integer fromLineNum, Integer toLineNum) {
        Connection conn = BaseDao.getConnection();
        List<User> userList = userDao.getUserListByNameAndRole(conn,userName,roleId,fromLineNum,toLineNum);
        BaseDao.close(conn,null,null);
        return userList;
    }

    @Override
    public Integer findUserCountByNameAndRole(String userName, Integer roleId) {
        Connection conn = BaseDao.getConnection();
        Integer lines = userDao.getUserCountByNameAndRole(conn,userName,roleId);
        BaseDao.close(conn,null,null);
        return lines;
    }

    @Override
    public boolean addUser(User user) {
        Connection conn = BaseDao.getConnection();
        Integer line = userDao.insertUser(conn,user);
        BaseDao.close(conn,null,null);
        return line>=1;
    }

    @Override
    public User findUserById(Integer id) {
        Connection conn = BaseDao.getConnection();
        User user = userDao.getUserById(conn,id);
        BaseDao.close(conn,null,null);
        return user;
    }

    @Override
    public boolean modifyUser(User user) {
        Connection conn = BaseDao.getConnection();
        Integer line = userDao.updateUserById(conn,user);
        BaseDao.close(conn,null,null);
        return line>=1;
    }

    @Override
    public boolean deleteUser(Integer id) {
        Connection conn = BaseDao.getConnection();
        Integer line = userDao.deleteUserById(conn,id);
        BaseDao.close(conn,null,null);
        return line>=1;
    }

    @Override
    public User findViewUserById(Integer id) {
        Connection conn = BaseDao.getConnection();
        User user = userDao.getVewUserById(conn,id);
        BaseDao.close(conn,null,null);
        return user;
    }

    @Override
    public String findUserPasswordById(Integer id) {
        Connection conn = BaseDao.getConnection();
        String userPassword = userDao.getUserPasswordById(conn,id);
        BaseDao.close(conn,null,null);
        return userPassword;
    }

    @Override
    public boolean modifyUserPasswordById(Integer id, String password) {
        Connection conn = BaseDao.getConnection();
        Integer line = userDao.updateUserPasswordById(conn,id,password);
        BaseDao.close(conn,null,null);
        return line>=1;
    }


}
