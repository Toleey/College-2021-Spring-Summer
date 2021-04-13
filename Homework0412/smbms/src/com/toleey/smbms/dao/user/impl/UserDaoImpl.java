package com.toleey.smbms.dao.user.impl;

import java.sql.Connection;
import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.user.UserDao;
import com.toleey.smbms.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl  implements UserDao {
    @Override
    public User getUserByUserCode(Connection conn, String userCode) {
        String sql = "select * from smbms_user where userCode = ?";
        ResultSet rst = BaseDao.executeQuery(conn,sql,userCode);
        User user = null;
        try {
            if (rst.next()) {
                user = new User();
                user.setId(rst.getInt("id"));
                user.setUserCode(rst.getString("userCode"));
                user.setUserName(rst.getString("userName"));
                user.setUserPassword(rst.getString("userPassword"));
                user.setGender(rst.getInt("gender"));
                user.setBirthday(rst.getDate("birthday"));
                user.setPhone(rst.getString("phone"));
                user.setUserRole(rst.getInt("userRole"));
                user.setCreatedBy(rst.getInt("createdBy"));
                user.setCreationDate(rst.getTimestamp("creationDate"));
                user.setModifyBy(rst.getInt("modifyBy"));
                user.setModifyDate(rst.getTimestamp("modifyDate"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers(Connection conn) {
        String sql = "select * from smbms_user";
        ResultSet rst = BaseDao.executeQuery(conn,sql);
        List<User> userList = new ArrayList<User>();
        try {
            if (rst.next()) {
                User user = new User();
                user.setId(rst.getInt("id"));
                user.setUserCode(rst.getString("userCode"));
                user.setUserName(rst.getString("userName"));
                user.setUserPassword(rst.getString("userPassword"));
                user.setGender(rst.getInt("gender"));
                user.setBirthday(rst.getDate("birthday"));
                user.setPhone(rst.getString("phone"));
                user.setUserRole(rst.getInt("userRole"));
                user.setCreatedBy(rst.getInt("createdBy"));
                user.setCreationDate(rst.getTimestamp("creationDate"));
                user.setModifyBy(rst.getInt("modifyBy"));
                user.setModifyDate(rst.getTimestamp("modifyDate"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }
}
