package cn.smbms.service.role.impl;

import cn.smbms.dao.role.RoleDao;
import cn.smbms.pojo.Role;
import cn.smbms.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }
}
