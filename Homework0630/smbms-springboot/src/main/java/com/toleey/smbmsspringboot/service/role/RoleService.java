package com.toleey.smbmsspringboot.service.role;


import com.toleey.smbmsspringboot.pojo.Role;

import java.util.List;

public interface RoleService {
    //获得角色(Id,Name)信息Ajax
    public List<Role> findRoleList();
}
