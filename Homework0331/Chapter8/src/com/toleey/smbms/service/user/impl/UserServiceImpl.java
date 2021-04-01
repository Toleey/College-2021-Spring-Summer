package com.toleey.smbms.service.user.impl;

import com.toleey.smbms.dao.user.UserDao;
import com.toleey.smbms.dao.user.UserMapper;
import com.toleey.smbms.entity.User;
import com.toleey.smbms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Scope(value = "prototype")
@Service(value = "userService")
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

    @Transactional(propagation =  Propagation.SUPPORTS)
    @Override
    public Integer addAUser(User user) {
        Integer t = 0; //0 失败 ｜ 1 通过

        try {
            userMapper.addAUser(user);
            t = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }

        return t;
    }

    @Transactional(propagation =  Propagation.SUPPORTS)
    @Override
    public Integer updateAUser(User user) {
        Integer t = 0; //0 失败 ｜ 1 通过

        try {
            userMapper.updateAUser(user);
            t = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }

        return t;
    }

    @Transactional(propagation =  Propagation.SUPPORTS)
    @Override
    public Integer deleteAUser(Integer id) {
        Integer t = 0; //0 失败 ｜ 1 通过

        try {
            userMapper.deleteAUser(id);
            t = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }

        return t;
    }
}
