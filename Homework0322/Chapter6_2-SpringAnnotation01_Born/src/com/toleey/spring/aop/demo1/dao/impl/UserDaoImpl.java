package com.toleey.spring.aop.demo1.dao.impl;

import com.toleey.spring.aop.demo1.dao.UserDao;
import com.toleey.spring.aop.demo1.entity.User;

public class UserDaoImpl implements UserDao {

    @Override
    public void insertUser(User user) {
        System.out.println("添加用户：编号："+user.getId()+" 姓名："+user.getName()+" 成功！");
    }
}
