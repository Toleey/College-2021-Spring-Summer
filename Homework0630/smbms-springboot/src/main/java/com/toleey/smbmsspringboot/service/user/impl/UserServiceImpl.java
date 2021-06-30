package com.toleey.smbmsspringboot.service.user.impl;


import com.toleey.smbmsspringboot.dao.user.UserMapper;
import com.toleey.smbmsspringboot.pojo.User;
import com.toleey.smbmsspringboot.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUserByUserCode(String userCode) {
        User user = null;
        try {
            user = userMapper.getUserByUserCode(userCode);

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findUserListByNameAndRole(String userName, Integer roleId, Integer fromLineNum, Integer toLineNum) {
        List<User> userList = userMapper.getUserListByNameAndRole(userName,roleId,fromLineNum,toLineNum);
        return userList;
    }

    @Override
    public Integer findUserCountByNameAndRole(String userName, Integer roleId) {
        Integer lines = userMapper.getUserCountByNameAndRole(userName,roleId);
        return lines;
    }


}
