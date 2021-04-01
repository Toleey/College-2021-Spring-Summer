package com.toleey.smbms.dao.user.impl;

import com.toleey.smbms.dao.user.UserDao;
import com.toleey.smbms.entity.User;
import com.toleey.smbms.util.ApplicationContextUtil;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> getUserListByCondition(User user) {
        //获得一个SqlSession
        SqlSessionTemplate sqlSession = (SqlSessionTemplate) ApplicationContextUtil.getApplicationContext().getBean("sqlSessionTemplate");

        List<User> userList = sqlSession.selectList("com.toleey.smbms.dao.user.UserDao.getUserListByCondition",user);

        return userList;
    }
}
