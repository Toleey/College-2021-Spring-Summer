package com.toleey.appinfo.controller.developer;

import com.toleey.appinfo.service.developer.user.DevUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/devloper")
public class DeveloperController {
    @Resource(name = "devUserService")
    private DevUserService devUserService;

    public void setDevUserService(DevUserService devUserService) {
        this.devUserService = devUserService;
    }

}
