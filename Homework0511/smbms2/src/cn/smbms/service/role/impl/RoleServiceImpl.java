package cn.smbms.service.role.impl;

import cn.smbms.dao.role.RoleMapper;
import cn.smbms.pojo.Role;
import cn.smbms.service.role.RoleService;
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
    public List<Role> findAllRoleList() {
        List<Role> roleList = roleMapper.getAllRoleList();
        return roleList;
    }

    @Override
    public List<Role> findRoleList() {
           List<Role> roleList = roleMapper.getRoleList();
        return roleList;
    }

    @Override
    public boolean addRole(Role role) {
        Integer line = roleMapper.insertRole(role);
        return line>=1;
    }

    @Override
    public boolean deleteRoleById(Integer id) {
        Integer line = roleMapper.deleteRoleById(id);
        return line>=1;
    }

    @Override
    public boolean updateRoleById(Role role) {
        Integer line = roleMapper.updateRoleById(role);
        return line>=1;
    }

    @Override
    public Role findARoleById(Integer id) {
        Role role = roleMapper.getARoleById(id);
        return role;
    }

    @Override
    public Role findRoleByRoleCode(String roleCode) {
        Role role = roleMapper.getRoleByRoleCode(roleCode);
        return role;
    }


}
