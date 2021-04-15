package com.toleey.smbms.service.role.impl;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.role.RoleDao;
import com.toleey.smbms.entity.Role;
import com.toleey.smbms.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> findRoleList() {
        Connection conn = BaseDao.getConnection();
        List<Role> roleList = roleDao.getRoleList(conn);
        BaseDao.close(conn,null,null);
        return roleList;
    }
}
