package cn.bw.springbootmybatisjsp.service.impl;

import cn.bw.springbootmybatisjsp.mapper.UserMapper;
import cn.bw.springbootmybatisjsp.pojo.User;
import cn.bw.springbootmybatisjsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int findUserCount() {
        return userMapper.getUserCount();
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.getAllUser();
    }

}
