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
    //根据用户编号查找用户信息
    public User findUserById(Integer id);
    //修改用户
    public boolean modifyUser(User user);
    //删除用户
    public boolean deleteUser(Integer id);
    //根据用户编号查找用户信息 viewUser
    public User findViewUserById(Integer id);

}
