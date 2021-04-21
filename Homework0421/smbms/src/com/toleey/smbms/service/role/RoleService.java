package com.toleey.smbms.service.role;

import com.toleey.smbms.entity.Role;

import java.util.List;

public interface RoleService {
    //获得所有角色信息
    public List<Role> findRoleList();
}
