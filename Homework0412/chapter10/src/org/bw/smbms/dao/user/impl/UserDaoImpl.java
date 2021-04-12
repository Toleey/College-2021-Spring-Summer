package org.bw.smbms.dao.user.impl;

import java.sql.SQLException;

import org.bw.smbms.dao.BaseDao;
import org.bw.smbms.dao.user.UserDao;
import org.bw.smbms.entity.User;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Override
	public User getUserByUserCode(Connection conn, String userCode) {
		String sql = "select * from smbms_user where userCode = ?";
		ResultSet rst =  (ResultSet) BaseDao.executeQuery(conn, sql, userCode);
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
				user.setModifyBy(rst.getInt("modify"));
				user.setModifyDate(rst.getTimestamp("modifyDate"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
