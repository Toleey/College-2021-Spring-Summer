package com.toleey.smbmsspringboot.service.user;


import com.toleey.smbmsspringboot.pojo.User;

import java.util.List;

/**
 * 处理用户数据的业务
 */
public interface UserService {
    //根据编码查询用户信息
    public User findUserByUserCode(String userCode);
    //查询用户
    public List<User> findUserListByNameAndRole(String userName, Integer roleId, Integer fromLineNum, Integer toLineNum);
    //查询用户总数
    public Integer findUserCountByNameAndRole(String userName,Integer roleId);


}
