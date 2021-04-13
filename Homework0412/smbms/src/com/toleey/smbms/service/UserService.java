package com.toleey.smbms.service;

import com.toleey.smbms.entity.User;

import java.util.List;

/**
 * 处理用户数据的业务
 */
public interface UserService {

    public User findUserByUserCode(String userCode);

    public List<User> findAllUsers();

}
