package com.toleey.appinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/back")
public class backendController {
    @RequestMapping("/login.do")
    public String doLogin(
            @RequestParam("userCode") String userCode,
            @RequestParam("userPassword") String userPassword
    ){
        System.out.println(userCode+userPassword);
        return "";
    }
}
