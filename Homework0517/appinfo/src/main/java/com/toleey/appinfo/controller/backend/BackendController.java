package com.toleey.appinfo.controller.backend;

import com.toleey.appinfo.pojo.BackendUser;
import com.toleey.appinfo.service.backend.user.BackUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/backend")
public class BackendController {
    @Resource(name = "backUserService")
    private BackUserService backUserService;

    public void setBackUserService(BackUserService backUserService) {
        this.backUserService = backUserService;
    }

    @RequestMapping("/logout.do")
    public String doLogout(HttpSession session){

        session.invalidate();
        //session.getAttribute("userSession"); 怎么单独销毁这个session？不然一退出开发者也没了

        return "redirect:/index.jsp";
    }


}
