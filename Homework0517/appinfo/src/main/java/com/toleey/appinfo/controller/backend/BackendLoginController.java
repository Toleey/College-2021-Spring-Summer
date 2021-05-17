package com.toleey.appinfo.controller.backend;

import com.toleey.appinfo.pojo.BackendUser;
import com.toleey.appinfo.service.backend.user.BackUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/backendLogin")
public class BackendLoginController {
    @Resource(name = "backUserService")
    private BackUserService backUserService;

    public void setBackUserService(BackUserService backUserService) {
        this.backUserService = backUserService;
    }

    @RequestMapping("/login.do")
    public String doLogin(
            @RequestParam("userCode") String userCode,
            @RequestParam("userPassword") String userPassword,
            Model model, HttpSession session
    ){
        if(userCode == null && userCode.equals("")){
            model.addAttribute("error","未填写登陆账户");
            return "backendlogin";
        }else {
            if (userPassword == null && userPassword.equals("")){
                model.addAttribute("error","未输入密码");
                return "backendlogin";
            }else { //服务器验证通过 都有
                BackendUser backendUser = backUserService.findBackendUser(userCode);
                if (backendUser != null){ //能查到账户
                    if (backendUser.getUserPassword() == userPassword || backendUser.getUserPassword().equals(userPassword)){

                        //session.setAttribute("userSession",backendUser);

                        return "backend/main";
                    }else {
                        model.addAttribute("error","密码错误");
                        return "backendlogin";
                    }
                }else { //查不到账户
                    model.addAttribute("error","没有此账户");
                    return "backendlogin";
                }
            }
        }

    }
}
