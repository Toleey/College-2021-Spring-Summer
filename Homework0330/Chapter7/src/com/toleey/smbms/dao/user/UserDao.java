package com.toleey.smbms.dao.user;

import com.toleey.smbms.entity.User;

import java.util.List;

public interface UserDao {
    //根据条件（用户名称、角色ID）查询用户列表
    //public List<User> getUserListByCondition(Map<String,Object> arg);
    //public List<User> getUserListByCondition(
    //      @Param("userName") String userName,
    //      @Param("roleId") Integer roleId
    //);
    public List<User> getUserListByCondition(User user);

}
