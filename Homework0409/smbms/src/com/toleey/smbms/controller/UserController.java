package com.toleey.smbms.controller;

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

    //进入登录页面
    @RequestMapping(value = "/login.html",method = RequestMethod.GET)
    public String login(){
        logger.info("===》展示登陆界面");
        return "login";
    }

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String doLogin(
            @RequestParam("userName") String uerName,
            String password,
            Model Model
    ){
            return "frame";
    }



}
