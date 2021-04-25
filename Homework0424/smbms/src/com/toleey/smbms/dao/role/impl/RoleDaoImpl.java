package com.toleey.smbms.dao.role.impl;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.role.RoleDao;
import com.toleey.smbms.entity.Role;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

    @Override
    public List<Role> getRoleList(Connection conn) {
        String sql = "SELECT id,roleName FROM smbms_role";
        ResultSet rst =  BaseDao.executeQuery(conn,sql);
        List<Role> roleList = new ArrayList<Role>();
        try {
            while (rst.next()){
            	Role role = new Role();
                role.setId(rst.getInt("id"));
                role.setRoleName(rst.getString("roleName"));
                roleList.add(role);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return roleList;
    }

}
