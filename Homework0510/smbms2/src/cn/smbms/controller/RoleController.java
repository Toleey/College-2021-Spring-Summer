package cn.smbms.controller;

import cn.smbms.pojo.Role;
import cn.smbms.service.role.RoleService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sys/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/getRoleListWithJson",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody//不认为是返回的是视图的名字
    public Object getRoleListWithJson(){
        List<Role> roleList = roleService.findRoleList();
        String roleString = JSON.toJSONString(roleList);
        return roleString;

    }

}
