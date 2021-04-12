package com.toleey.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.toleey.smbms.entity.User;
import com.toleey.smbms.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger = Logger.getLogger(UserController.class);

    @Resource(name = "userService")
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //进入登录页面
    @RequestMapping(value = "/login.html",method = RequestMethod.GET)
    public String login(){
        logger.info("===》展示登陆界面");
        return "login";
    }

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String doLogin(
            @RequestParam("userCode") String userCode,
            @RequestParam("userPassword")String password,
            Model model, HttpSession session, HttpServletRequest request
    ){
        User user = userService.findUserByUserCode(userCode);
        if (user!=null){
            if (user.getUserPassword().equals(password)) {
                session.setAttribute("userSession", user);
            }else {
                model.addAttribute("error", "密码输入有误");
                request.setAttribute("error", "密码输入错误");
                return "login"; //与视图解析器的前缀后缀匹配 自动添加.jsp   去webinf/jsp/里找  这是映射，默认转发请求
                //通过视图映器映射哪个视图(jsp)
                //return "redirect:/user/login.html";只重定向不转发数据
                //return "forward:/user/login.html";
            }

        }else{ //user=null 没有值
            //model.addAttribute("error","用户名不存在"); 当到model浪费
            //还可以用异常来做
            //modelandview mapper model request
            request.setAttribute("error", "用户名不存在");
            return "login";
            //return "forward:/user/login.html";
        }
        //return "frame"; 必须转发请求
        return "redirect:/user/main.html";
    }
    
    @RequestMapping(value = "/logout.do",method = RequestMethod.GET)
    public String doLoginOut(HttpSession session){


            return "login";
    }

    //专门进入主页的方法
    @RequestMapping("/main.html")
    public String main(HttpSession session){
        User user = (User) session.getAttribute("userSession");
        if (user!=null){
            return "frame";
        }
        return "login";
    }



}
