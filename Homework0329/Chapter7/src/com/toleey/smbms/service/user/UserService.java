package com.toleey.smbms.service.user;

import com.toleey.smbms.entity.User;

import java.util.List;

public interface UserService {
    //根据条件(用户名称、角色ID)查询用户列表
    public List<User> findUserListByCondition(User user);
}
