package edu.bw.Spring.aop.demo1.dao.impl;

import edu.bw.Spring.aop.demo1.dao.UserDao;
import edu.bw.Spring.aop.demo1.entity.User;

public class UserDaoImpl implements UserDao {

    @Override
    public void add(User user) {
        System.out.println("用户"+user.getName()+"添加成功");
    }
}
