package org.bw.smbms.user.service;

import org.bw.smbms.dao.BaseDao;
import org.bw.smbms.dao.user.UserDao;
import org.bw.smbms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;

@Service
public class UserServiceImpl  implements UserService{
	@Autowired
	@Qualifier("userDao")
	private UserDao UserDao;


	public void setUserDao(UserDao userDao) {
		UserDao = userDao;
	}


	@Override
	public User findUserByUserCode(String userCode) {
		Connection conn = (Connection) BaseDao.getConnection();	
		User user = UserDao.getUserByUserCode(conn, userCode);
		return user;
	}

}
