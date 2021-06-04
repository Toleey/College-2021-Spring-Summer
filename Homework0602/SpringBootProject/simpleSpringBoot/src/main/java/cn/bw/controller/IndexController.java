package cn.bw.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller和@ResponseBody 的混合体 返回值不要返回页面，直接出现在浏览器页面里
public class IndexController {
    @RequestMapping(value = "/index")
    public String index(){
        return "Hello,SpringBoot";
    }
}
