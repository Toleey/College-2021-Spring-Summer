package cn.smbms.dao.role;

import cn.smbms.pojo.Role;

import java.sql.Connection;
import java.util.List;

//对smbms数据表中角色表进行操作
public interface RoleDao {
    //查询所有角色数据
    public List<Role> getRoleList();

}
