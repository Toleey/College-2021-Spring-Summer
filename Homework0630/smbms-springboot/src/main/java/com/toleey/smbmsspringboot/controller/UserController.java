package com.toleey.smbmsspringboot.controller;


import com.alibaba.fastjson.JSON;
import com.toleey.smbmsspringboot.pojo.Role;
import com.toleey.smbmsspringboot.pojo.User;
import com.toleey.smbmsspringboot.service.role.RoleService;
import com.toleey.smbmsspringboot.service.user.UserService;
import com.toleey.smbmsspringboot.tools.Pager;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sys/user")
public class UserController {

	@Resource(name = "userService")
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Resource(name = "roleService")
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
    @RequestMapping("/main.html")
    public String main(HttpSession session){
        User user = (User) session.getAttribute("userSession");
        if (user!=null){
            return "frame";
        }
        return "login";
    }

	//退出账号
	@RequestMapping(value = "/logout.do",method = RequestMethod.GET)
	public String doLoginOut(HttpSession session){
		 session.invalidate();
		return "redirect:/login.html";
	}

	//打开并显示用户列表
	@RequestMapping(value = "/user.do",method = RequestMethod.GET)
	public String userList(
			@RequestParam(value = "queryname",required = false) String userName,
			@RequestParam(value = "queryUserRole",required = false) String userRole,
			@RequestParam(value = "pageIndex",required = false) String currentPageNum,
			Model model
	){
		model.addAttribute("queryname",userName);
		model.addAttribute("queryUserRole",userRole);
		//获得角色
		List<Role> roleList = roleService.findRoleList();
		model.addAttribute("roleList",roleList);
		//当前页
		int currentpage = 1;
		if (currentPageNum!=null && !"".equals(currentPageNum)){
			currentpage = Integer.parseInt(currentPageNum);
		}
		model.addAttribute("pageCount",currentpage);
		//总条数
		Integer roleId = null;
		if (userRole!=null && !"".equals(userRole)) {
			roleId = Integer.parseInt(userRole);
		}
		int rowCount = userService.findUserCountByNameAndRole(userName,roleId);
		model.addAttribute("totalCount",rowCount);
		//每页显示条数
		int rowPerPage = 5;
		//总页数
		Pager pager = new Pager(rowCount,rowPerPage,currentpage);
		int pageCount = pager.getPageCount();
		model.addAttribute("totalPageCount",pageCount);
		model.addAttribute("queryUserName",userName);
		model.addAttribute("currentPageNo",currentpage);
		//获得分页记录
		int fromLineNum=(currentpage-1)*rowPerPage;

		List<User> userList = userService.findUserListByNameAndRole(userName,roleId,fromLineNum,rowPerPage);
		model.addAttribute("userList",userList);
		return "userlist";
	}

}
