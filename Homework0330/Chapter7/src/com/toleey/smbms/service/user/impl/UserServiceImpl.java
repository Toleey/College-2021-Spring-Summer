package com.toleey.smbms.service.user.impl;

import com.toleey.smbms.dao.user.UserDao;
import com.toleey.smbms.dao.user.UserMapper;
import com.toleey.smbms.entity.User;
import com.toleey.smbms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("userService")
public class UserServiceImpl  implements UserService {
    //private UserDao userDao; //需要UserDao注入

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }
//ppdfae
//    @Override
//    public List<User> findUserListByCondition(User user) {
//        return userDao.getUserListByCondition(user);
//    }

    @Autowired //类型匹配
    @Qualifier("userMapper") //类型匹配下的匹配 不能单用 两个可以一起用
    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional(propagation =  Propagation.SUPPORTS)
    @Override
    public List<User> findUserListByCondition(User user) {
        List<User> userList = null;
        try {
            userList = userMapper.getUserListByCondition(user);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }finally {

        }
        return userList;
    }
}
