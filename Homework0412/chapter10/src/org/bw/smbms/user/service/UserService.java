package org.bw.smbms.user.service;

import org.bw.smbms.entity.User;

/**
 * 处理用户数据的业务
 * @author toby
 *
 */

public interface UserService {
	
	public User findUserByUserCode(String userCode);
	

}
