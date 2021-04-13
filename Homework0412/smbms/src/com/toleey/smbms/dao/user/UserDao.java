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

    public List<User> getAllUsers(Connection conn);
}
