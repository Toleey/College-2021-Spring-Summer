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


}
