package com.toleey.smbms.controller;

import com.alibaba.fastjson.JSON;
import com.toleey.smbms.entity.Role;
import com.toleey.smbms.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    //ajax json形式的获取角色数据表里角色id和角色名称
    @RequestMapping(value = "/getRoleListWithJson",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody//不认为是返回的是视图的名字
    public Object getRoleListWithJson(){
        List<Role> roleList = roleService.findRoleList();
        String roleString = JSON.toJSONString(roleList);
        return roleString;

    }
}
