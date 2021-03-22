package com.toleey.spring.aop.demo1.service.impl;


import com.toleey.spring.aop.demo1.dao.UserDao;
import com.toleey.spring.aop.demo1.dao.impl.UserDaoImpl;
import com.toleey.spring.aop.demo1.entity.User;
import com.toleey.spring.aop.demo1.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.insertUser(user);
    }
}
