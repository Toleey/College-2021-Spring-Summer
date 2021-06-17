package cn.smbms.controller;

import java.net.http.HttpRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;

@Controller   //login页面不用拦截
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
					//HttpSession session = request.getSession();
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
		return "redirect:/sys/user/main.html"; //forward带数据过去
    }
    
}
