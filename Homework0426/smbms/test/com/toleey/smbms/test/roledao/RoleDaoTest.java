package com.toleey.smbms.test.roledao;

import java.net.ConnectException;

import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.role.RoleDao;
import com.toleey.smbms.dao.role.impl.RoleDaoImpl;
import com.toleey.smbms.entity.Role;
import com.toleey.smbms.service.role.RoleService;
import com.toleey.smbms.service.role.impl.RoleServiceImpl;

public class RoleDaoTest {
	
	@Test
	public void testRoleDao() {
		int a = 1;
		int b = 2;
	    long c = (a+b);
		System.out.println(c);
		
		Connection conn = BaseDao.getConnection();
		RoleService roleService = new RoleServiceImpl();
//		List<Role> roleList = roleService.findRoleList();
		RoleDao roleDao = new RoleDaoImpl();
		List<Role> roleList = roleDao.getRoleList(conn);
		
		for (Role role : roleList) {
			System.out.println(role);
		}
		
	}

}
