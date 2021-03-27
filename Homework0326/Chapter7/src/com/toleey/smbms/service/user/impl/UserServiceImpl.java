package com.toleey.smbms.service.user.impl;

import com.toleey.smbms.dao.user.UserDao;
import com.toleey.smbms.entity.User;
import com.toleey.smbms.service.user.UserService;

import java.util.List;

public class UserServiceImpl  implements UserService {
    private UserDao userDao; //需要UserDao注入

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findUserListByCondition(User user) {
        return userDao.getUserListByCondition(user);
    }

}
