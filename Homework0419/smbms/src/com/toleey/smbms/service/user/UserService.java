package com.toleey.smbms.service.user;

import com.toleey.smbms.entity.User;

import java.util.List;

/**
 * 处理用户数据的业务
 */
public interface UserService {

    public User findUserByUserCode(String userCode);

    public List<User> findUserListByNameAndRole(String userName,Integer roleId,Integer fromLineNum,Integer toLineNum);
    public Integer findUserCountByNameAndRole(String userName,Integer roleId);
    //新增用户
    public boolean addUser(User user);

}
