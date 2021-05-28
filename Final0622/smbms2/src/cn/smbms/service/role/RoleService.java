package cn.smbms.service.role;


import cn.smbms.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
    //获得所有角色信息
    public List<Role> findAllRoleList();
    //获得角色(Id,Name)信息Ajax
    public List<Role> findRoleList();
    //添加角色
    public boolean addRole(Role role);
    //删除角色
    public boolean deleteRoleById(Integer id);
    //修改角色
    public boolean updateRoleById(Role role);
    //查询单个角色
    public Role findARoleById(Integer id);
    //查询角色编码
    public Role findRoleByRoleCode(String roleCode);
}
