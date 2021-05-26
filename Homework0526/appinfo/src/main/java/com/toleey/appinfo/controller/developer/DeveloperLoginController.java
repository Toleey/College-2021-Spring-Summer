package com.toleey.appinfo.controller.developer;

import com.toleey.appinfo.pojo.BackendUser;
import com.toleey.appinfo.pojo.DevUser;
import com.toleey.appinfo.service.developer.user.DevUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/loginDev")
public class DeveloperLoginController {

    @Resource(name = "devUserService")
    private DevUserService devUserService;

    public void setDevUserService(DevUserService devUserService) {
        this.devUserService = devUserService;
    }

    @RequestMapping("/login.do")
    public String doLogin(
            @RequestParam("devCode") String devCode,
            @RequestParam("devPassword") String devPassword,
            Model model, HttpSession session
    ){
        if(devCode == null && devCode.equals("")){
            model.addAttribute("error","未填写登陆账户");
            return "devlogin";
        }else {
            if (devPassword == null && devPassword.equals("")){
                model.addAttribute("error","未输入密码");
                return "devlogin";
            }else { //服务器验证通过 都有
                DevUser devUser = devUserService.findDevUserByDevCode(devCode);
                if (devUser != null){ //能查到账户
                    if (devUser.getDevPassword() == devPassword || devUser.getDevPassword().equals(devPassword)){

                        session.setAttribute("devUserSession",devUser);

                        return "developer/main";
                    }else {
                        model.addAttribute("error","密码错误");
                        return "devlogin";
                    }
                }else { //查不到账户
                    model.addAttribute("error","没有此账户");
                    return "devlogin";
                }
            }
        }

    }



}
