package org.bw.aopextend.demo1.service.impl;

import org.bw.aopextend.demo1.dao.UserDao;
import org.bw.aopextend.demo1.dao.impl.UserDaoImpl;
import org.bw.aopextend.demo1.entity.User;
import org.bw.aopextend.demo1.service.UserService;

public class UserServiceImpl implements UserService {
   private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addAUser(User user) {

            userDao.addUser(user);


    }
}
