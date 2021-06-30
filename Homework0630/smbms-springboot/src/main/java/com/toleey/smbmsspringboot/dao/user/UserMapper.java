package com.toleey.smbmsspringboot.dao.user;


import com.toleey.smbmsspringboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    //查找某个编码的用户
    public User getUserByUserCode(@Param("userCode") String userCode);
    // 根据多条件(用户名模糊查询，角色)查询用户列表，并且分页
    public List<User> getUserListByNameAndRole(
            @Param("userName") String userName,@Param("roleId") Integer roleId,
            @Param("fromLineNum") Integer fromLineNum,@Param("toLineNum") Integer toLineNum
    );
    //查找用户的总记录数
    public Integer getUserCountByNameAndRole(@Param("userName") String userName, @Param("roleId") Integer roleId);

}
