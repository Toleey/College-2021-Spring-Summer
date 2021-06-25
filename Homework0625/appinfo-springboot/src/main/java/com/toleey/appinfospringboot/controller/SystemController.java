package com.toleey.appinfospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chooseSystem")
public class SystemController {

    @RequestMapping("/backendlogin")
    public String backendlogin(){

        return "backendlogin";
    }
    @RequestMapping("/devlogin")
    public String devlogin(){

        return "devlogin";
    }
}
