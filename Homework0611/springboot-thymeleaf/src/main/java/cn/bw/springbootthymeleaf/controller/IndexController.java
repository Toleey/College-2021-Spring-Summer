package cn.bw.springbootthymeleaf.controller;

import cn.bw.springbootthymeleaf.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    //@RequestMapping("index")
    @GetMapping("/index")
    public String index(Model model){
        //model中添加一个变量值
        model.addAttribute("name","Toby");
        //往model中加一个User对象
        User user1 = new User(1,"哈","好");
        model.addAttribute("user1",user1);
        User user2 = new User(2,"里斯","hh");
        User user3 = new User(3,"所得","hdf");
        List<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user3);
        model.addAttribute("userList",userList);

        return "index";
    }
}
