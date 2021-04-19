package com.toleey.smbms.dao.user;

import java.sql.Connection;
import java.util.List;

import com.toleey.smbms.entity.User;

public interface UserDao {
    //查找某个编码的用户
    /**
     * 查找某个编码的用户
     * @param conn 数据库连接
     * @param userCode 用户编码
     * @return 查找到的某个编码的用户
     */
    public User getUserByUserCode(Connection conn, String userCode);

    /**
     * 根据多条件(用户名模糊查询，角色)查询用户列表，并且分页
     * @param conn 连接
     * @param userName 用户名
     * @param roleId 角色编号
     * @param fromLineNum 分页从数据库表第几行开始
     * @param toLineNum 分页显示到数据库表的第几行
     * @return 分页后的结果集转化成的列表
     */
    public List<User> getUserListByNameAndRole(
            Connection conn,String userName,Integer roleId,Integer fromLineNum,Integer toLineNum
    );
    //查找用户的总记录数
    public Integer getUserCountByNameAndRole(
            Connection conn,
            String userName,
            Integer roleId
    );
    //新增用户
    public Integer insertUser(Connection conn,User user);
}
