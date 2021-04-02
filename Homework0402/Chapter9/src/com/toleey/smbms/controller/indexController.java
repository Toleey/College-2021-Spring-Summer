package com.toleey.smbms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

        @RequestMapping("index") //url地址
        // 访问xxx/index访问下面的index.jsp
        public String index(){ //String 返回映射的地址
            return "index"; //访问对应的index.jsp
        }

        @RequestMapping("welcome")
        public String welcome(){
            return "welcome";
        }
}