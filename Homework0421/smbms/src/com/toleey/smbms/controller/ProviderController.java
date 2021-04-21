package com.toleey.smbms.controller;

import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.entity.User;
import com.toleey.smbms.service.provider.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    @Resource(name = "providerService")
    private ProviderService providerService;
    public void setProviderService(ProviderService providerService) {
        this.providerService = providerService;
    }

    @RequestMapping("/provider.do")
    public String providerList(
            @RequestParam(value = "queryProCode",required = false) String proCode,
            @RequestParam(value = "queryProName",required = false) String proName,
            Model model
    ){
        List<Provider> providerList = providerService.findProviderListByProCodeAndProName(proCode,proName);
        model.addAttribute("providerList",providerList);
        return "providerlist";
    }

    //进入新增巩供应商的界面
    @RequestMapping(value = "/provideradd.html",method = RequestMethod.GET)
    public String addProvider(@ModelAttribute("provider") Provider provider){
        return "/provider/provideradd";
    }
    //添加供应商的操作
    @RequestMapping(value = "/addProviderSave.do",method = RequestMethod.POST)
    public String addProviderSave(Provider provider, HttpSession session){
        User loginUser = (User) session.getAttribute("userSession");
        provider.setCreatedBy(loginUser.getId());
        provider.setCreationDate(new Timestamp(new Date().getTime()));
        providerService.addProvider(provider);
        System.out.println(provider);
        return "redirect:/provider/provider.do";
    }

}
