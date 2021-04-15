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
    public List<User> getUserListByNameAndRole(Connection conn, String userName, Integer roleId, Integer fromLineNum, Integer toLineNum) {

        String sql = "SELECT userCode,userName,gender,YEAR(NOW())-YEAR(birthday) AS age,phone,roleName " +
                "FROM smbms_user u  INNER JOIN smbms_role r  ON u.userRole = r.id WHERE 1=1";
        if(userName!=null && !userName.equals("")){
            sql+=" and userName like '%" +userName+ "%' ";
        }
        if (roleId!=null && roleId!=0){
            sql+=" and userRole = "+roleId;
        }
        sql+=" limit "+fromLineNum+","+toLineNum;

        
        ResultSet rst = BaseDao.executeQuery(conn,sql);
        List<User> userList = new ArrayList<User>();
            try {
                while(rst.next()) {
                    User user = new User();
                    user.setUserCode(rst.getString("userCode"));
                    user.setUserName(rst.getString("userName"));
                    user.setGender(rst.getInt("gender"));
                    user.setAge(rst.getInt("age"));
                    user.setPhone(rst.getString("phone"));
                    user.setRoleName(rst.getString("roleName"));
                    userList.add(user);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            
        return userList;
    }

    @Override
    public Integer getUserCountByNameAndRole(Connection conn, String userName, Integer roleId) {
        String sql = "SELECT count(*) as linenum from smbms_user where 1=1";
        if(userName!=null && userName.equals("")){
            sql+="and userName like '%"+userName+"%'";
        }
        if (roleId!=null){
            sql+=" and userRole="+roleId;
        }
        ResultSet rst = BaseDao.executeQuery(conn,sql);
        int linenum = 0;
        try {
            if (rst.next()){
                linenum=rst.getInt("linenum");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return linenum;
    }

}
