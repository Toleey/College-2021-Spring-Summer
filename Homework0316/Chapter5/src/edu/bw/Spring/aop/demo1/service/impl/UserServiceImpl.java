package edu.bw.Spring.aop.demo1.service.impl;

import edu.bw.Spring.aop.demo1.dao.UserDao;
import edu.bw.Spring.aop.demo1.entity.User;
import edu.bw.Spring.aop.demo1.service.UserService;


public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addNewUser(User user) {
        userDao.add(user);
    }
}
