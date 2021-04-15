package com.toleey.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.toleey.smbms.entity.User;
import com.toleey.smbms.exception.LoginException;
import com.toleey.smbms.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    @RequestMapping(value = "/login.html")
    public String login(){
        logger.info("===》展示登陆界面");
        return "login";
    }

    //登陆处理，用户名密码出错使用传统的方式解决
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
                request.setAttribute("error", "密码输入错误");
                return "forward:/user/login.html";

                //model.addAttribute("error", "密码输入有误");
                //return "login"; //与视图解析器的前缀后缀匹配 自动添加.jsp   去webinf/jsp/里找  这是映射，默认转发请求
                //通过视图映器映射哪个视图(jsp)
                //return "redirect:/user/login.html";只重定向不转发数据
            }

        }else{
            request.setAttribute("error", "用户名不存在");
            return "forward:/user/login.html";

            //user=null 没有值
            //model.addAttribute("error","用户名不存在"); 当到model浪费
            //还可以用异常来做
            //modelandview mapper model request
            //return "login";
        }
        //return "frame"; 必须转发请求
        return "redirect:/user/main.html";
    }
    //登陆功能，密码用户名出错，使用异常来处理
    @RequestMapping(value = "/exlogin.do",method = RequestMethod.POST)
    public String exdoLogin(
            @RequestParam("userCode") String userCode,
            @RequestParam("userPassword")String password,
            Model model, HttpSession session, HttpServletRequest request
    ){
        User user = userService.findUserByUserCode(userCode);
        if (user!=null){
            if (user.getUserPassword().equals(password)) {
                session.setAttribute("userSession", user);
            }else {
                RuntimeException e = new LoginException("密码输入有误");
                request.setAttribute("error",e.getMessage());
                throw e;
               //throw new Exception("密码输入有误"); 这是检查异常
               //throw new RuntimeException("密码输入有误");//运行时异常
            }
        }else{
            RuntimeException e = new LoginException("用户不存在");
            request.setAttribute("error",e.getMessage());
            throw e;
        }
        return "redirect:/user/main.html";
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

//处理本Controller异常 自动处理异常(局部异常处理)-只能处理当前控制器的异常 不如全局异常好，局部只适用于特殊处理，会优先执行局部 异常指的是Spring的异常
//    @ExceptionHandler(value = {RuntimeException.class,})
//    public String handlerException(RuntimeException e,HttpServletRequest req){
//        req.setAttribute("error",e.getMessage());
//        return "login";
//    }

    //退出账号
    @RequestMapping(value = "/logout.do",method = RequestMethod.GET)
    public String doLoginOut(HttpSession session){


        return "login";
    }

    //查询用户列表
    @RequestMapping("/user.do")
    public String queryUser(HttpServletRequest request){
        List<User> userList = userService.findAllUsers();
        request.setAttribute("userList",userList);
        return "userlist";
    }



}
