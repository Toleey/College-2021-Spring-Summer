package cn.bw.springbootjunitjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController 相当于Controller和ReponseBody的双重功能
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){

        return "index";
    }

}
