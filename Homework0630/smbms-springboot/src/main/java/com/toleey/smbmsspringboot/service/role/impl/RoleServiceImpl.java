package com.toleey.smbmsspringboot.service.role.impl;


import com.toleey.smbmsspringboot.dao.role.RoleMapper;
import com.toleey.smbmsspringboot.pojo.Role;
import com.toleey.smbmsspringboot.service.role.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource(name = "roleMapper")
    public RoleMapper roleMapper;

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }


    @Override
    public List<Role> findRoleList() {
           List<Role> roleList = roleMapper.getRoleList();
        return roleList;
    }


}
