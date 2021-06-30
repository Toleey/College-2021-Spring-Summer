package com.toleey.smbmsspringboot.controller;


import com.toleey.smbmsspringboot.pojo.User;
import com.toleey.smbmsspringboot.service.user.UserService;
import com.toleey.smbmsspringboot.tools.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class LoginController {  
	
	@Resource(name = "userService")
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//进入登录页面
    @RequestMapping(value = "/login.html")
    public String login(){
        return "login";
    }
    
    @RequestMapping(value = "/doLogin.do")
    public String doLogin(
    		@RequestParam("userCode") String userCode,
    		@RequestParam("userPassword") String userPassword,
    		HttpServletRequest request,
    		HttpSession session,
			Model model
    		) {
    	User user = userService.findUserByUserCode(userCode);
    	System.out.println(user);
    	if (user!=null) {
				if (user.getUserPassword().equals(userPassword)) {
					session.setAttribute(Constants.USER_SESSION, user);
				}else {
					request.setAttribute("error", "密码输入有误");
					model.addAttribute("userCode",userCode);
					return "login";
				}
			}else {
				request.setAttribute("error", "用户名输入有误");
				return "forward:/login.html";
			}
		return "redirect:/sys/user/main.html";
    }
    
}
