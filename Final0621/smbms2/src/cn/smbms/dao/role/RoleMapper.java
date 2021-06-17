package cn.smbms.dao.role;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.util.List;

//对smbms数据表中角色表进行操作
public interface RoleMapper {
    //查询所有角色数据
    public List<Role> getAllRoleList();
    //查询所有角色(Id,Name)数据 Ajax用
    public List<Role> getRoleList();
    //添加角色
    public Integer insertRole(Role role);
    //删除角色
    public Integer deleteRoleById(@Param("id") Integer id);
    //修改角色
    public Integer updateRoleById(Role role);
    //查询单个角色
    public Role getARoleById(@Param("id") Integer id);
    //查询角色编码
    public Role getRoleByRoleCode(@Param("roleCode") String roleCode);

}
