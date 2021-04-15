package com.toleey.smbms.controller;

import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.service.provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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

}
