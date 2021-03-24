package com.toleey.spring.aop.demo1.service.impl;


import com.toleey.spring.aop.demo1.dao.UserDao;
import com.toleey.spring.aop.demo1.dao.impl.UserDaoImpl;
import com.toleey.spring.aop.demo1.entity.User;
import com.toleey.spring.aop.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


//声明业务层对象，id为userService
@Service("userService")
public class UserServiceImpl implements UserService {
    //往属性里注入值
    //实现Bean的自动装配，默认按类型匹配
    @Autowired
    //指定Bean的名称
    //@Qualifier("userDao")  //有错，存疑
    //实现Bean的按名称自动装配
    //@Resource(name = "userDao")


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
