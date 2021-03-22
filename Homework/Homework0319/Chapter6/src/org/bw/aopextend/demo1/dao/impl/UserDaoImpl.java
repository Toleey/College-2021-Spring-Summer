package org.bw.aopextend.demo1.dao.impl;

import org.bw.aopextend.demo1.dao.UserDao;
import org.bw.aopextend.demo1.entity.User;

public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(User user) {
        System.out.println("添加用户成功，编号："+user.getId()+" 姓名："+user.getName());
        throw new RuntimeException("为了测试异常增强而产生的异常");
    }

}                                                                                                                                                       
