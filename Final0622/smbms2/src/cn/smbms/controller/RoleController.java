package cn.smbms.controller;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sys/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/role.do",method = RequestMethod.GET)
    public String roleList(Model model){
        List<Role> roleList = roleService.findAllRoleList();
        model.addAttribute("roleList",roleList);
        return "rolelist";
    }
    @RequestMapping(value = "/roleadd.html",method = RequestMethod.GET)
    public String addRole(@ModelAttribute("role") Role role){
        return "roleadd";
    }
    @RequestMapping(value = "/addRoleSave.do",method = RequestMethod.POST)
    public String addRoleSave(Role role, HttpSession session){
        User loginUser = (User) session.getAttribute("userSession");
        role.setCreatedBy(loginUser.getId());
        role.setCreationDate(new Timestamp(new Date().getTime()));
        roleService.addRole(role);
        return "redirect:/sys/role/role.do";
    }
    @RequestMapping(value = "/modifyrole.html",method = RequestMethod.GET)
    public String modifyRole(@ModelAttribute("role") Role role,@RequestParam("roleid") Integer roleid, Model model){
        Role role2 = roleService.findARoleById(roleid);
        model.addAttribute("role",role2);
        return "rolemodify";
    }
    @RequestMapping(value = "/doModifyRole.do",method = RequestMethod.POST)
    public String doModifyRole(Role role,HttpSession session){
        User loginUser = (User) session.getAttribute("userSession");
        role.setModifyBy(loginUser.getId());
        role.setModifyDate(new Timestamp(new Date().getTime()));
        roleService.updateRoleById(role);
        return "redirect:/sys/role/role.do";
    }
    @RequestMapping(value = "/deleteRole.do",method = RequestMethod.GET)
    @ResponseBody
    public Object deleteRole(@RequestParam("id") Integer id){
        boolean result = roleService.deleteRoleById(id);
        String data = null;
        if (result){
            data = "true";
        }else {
            data = "false";
        }
        String dataStr = JSON.toJSONString(data);
        return dataStr;
    }
    @RequestMapping(value = "/checkRoleCodeExists.do",method = RequestMethod.GET)
    @ResponseBody//不认为是返回的是视图的名字
    public Object getAllRoleCode(@RequestParam("roleCode") String roleCode){
        Role role = roleService.findRoleByRoleCode(roleCode);
        String data = null;
        if (role == null){  //没有。数据库查不到
            data = "noexist";
        }else {
            data = "exist";
        }
        String dataStr = JSON.toJSONString(data);
        return dataStr;

    }


    @RequestMapping(value = "/getRoleListWithJson",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody//不认为是返回的是视图的名字
    public Object getRoleListWithJson(){
        List<Role> roleList = roleService.findRoleList();
        String roleString = JSON.toJSONString(roleList);
        return roleString;

    }

}
