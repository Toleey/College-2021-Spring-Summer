package cn.bw.springbootfreemaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mav = new ModelAndView();
        // View 视图
        // SB会自动在 resources/templates 中去查找 hello.ftl
        // resources/static 是放静态文件的地方
        mav.setViewName("hello");
        // Model数据
        mav.addObject("hello", "Hello Freemarkder!");
        return mav;
    }


}
