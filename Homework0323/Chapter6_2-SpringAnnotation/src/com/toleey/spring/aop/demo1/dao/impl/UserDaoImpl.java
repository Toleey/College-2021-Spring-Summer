package com.toleey.spring.aop.demo1.dao.impl;

import com.toleey.spring.aop.demo1.dao.UserDao;
import com.toleey.spring.aop.demo1.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//声明一个IOC的Dao对象，id为UserDao
@Repository("userDao")
//@Component("userDao")
public class UserDaoImpl implements UserDao {

    @Override
    public void insertUser(User user) {
        System.out.println("添加用户：编号："+user.getId()+" 姓名："+user.getName()+" 成功！");
        //throw new RuntimeException("异常抛出增强");
    }
}
