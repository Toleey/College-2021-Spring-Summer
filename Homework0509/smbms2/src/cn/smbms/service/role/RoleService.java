package cn.smbms.service.role;


import cn.smbms.pojo.Role;

import java.util.List;

public interface RoleService {
    //获得所有角色信息
    public List<Role> findRoleList();
}
