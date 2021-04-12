package com.toleey.smbms.service;

import com.toleey.smbms.entity.User;

/**
 * 处理用户数据的业务
 */
public interface UserService {

    public User findUserByUserCode(String userCode);

}
