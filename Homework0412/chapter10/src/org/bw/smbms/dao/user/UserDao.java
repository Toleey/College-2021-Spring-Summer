package org.bw.smbms.dao.user;

import org.bw.smbms.entity.User;

import com.mysql.jdbc.Connection;

public interface UserDao {
	
	//查找某个编码的用户
	/**
	 * 查找某个编码的用户
	 * @param conn 数据库连接
	 * @param suerCode 用户编码
	 * @return 查找到的某个编码的用户
	 */
	public User getUserByUserCode(Connection conn,String userCode);

}
