package com.toleey.smbmsspringboot.dao.role;


import com.toleey.smbmsspringboot.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//对smbms数据表中角色表进行操作
@Mapper
public interface RoleMapper {
    //查询所有角色(Id,Name)数据
    public List<Role> getRoleList();

}
