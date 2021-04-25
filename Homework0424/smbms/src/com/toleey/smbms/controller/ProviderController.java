package com.toleey.smbms.controller;

import com.alibaba.fastjson.JSON;
import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.entity.Role;
import com.toleey.smbms.entity.User;
import com.toleey.smbms.service.provider.ProviderService;
import com.toleey.smbms.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
            @RequestParam(value = "pageIndex",required = false) String currentPageNum,
            Model model
    ){

        //当前页
        int currentpage = 1;
        if (currentPageNum!=null && !"".equals(currentPageNum)){
            currentpage = Integer.parseInt(currentPageNum);
        }
        model.addAttribute("pageCount",currentpage);

        //总条数
        int rowCount = providerService.findProviderCountByProCodeAndProName(proCode,proName);
        model.addAttribute("totalCount",rowCount);

        //每页显示条数
        int rowPerPage = 5;

        //总页数
        Pager pager = new Pager(rowCount,rowPerPage,currentpage);
        int pageCount = pager.getPageCount();

        model.addAttribute("totalPageCount",pageCount);
        model.addAttribute("queryProName",proName);
        model.addAttribute("currentPageNo",currentpage);
        //获得分页记录
        int fromLineNum=(currentpage-1)*rowPerPage;

        List<Provider> providerList = providerService.findProviderListByProCodeAndProName(proCode,proName,fromLineNum,rowPerPage);
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
        return "redirect:/provider/provider.do";
    }
    //进入修改供应商页面
    @RequestMapping(value = "/modifyProvider.html",method = RequestMethod.GET)
    public String modifyProvider(
            @ModelAttribute("provider") Provider provider,@RequestParam("proid") String proid,Model model
    ){
        Integer id = null;
        if (proid!=null){
            id = Integer.parseInt(proid);
        }
        Provider provider2 = providerService.findProviderById(id);
        model.addAttribute("provider",provider2);
        return "/provider/providermodify";
    }

    //提交供应商修改信息
    @RequestMapping(value = "/modifyProviderSave.do",method = RequestMethod.POST)
    public String modifyProviderSave(@Valid Provider provider,BindingResult bindingResult,HttpSession session){
        if (bindingResult.hasErrors()) {
            return "/provider/providermodify";
        }
        User loginUser = (User) session.getAttribute("userSession");
        provider.setModifyBy(loginUser.getId());
        provider.setModifyDate(new Timestamp(new Date().getTime()));
        System.out.println(provider);
        boolean boo = providerService.modifyProvider(provider);
        if (!boo){
            return "/provider/providermodify";
        }
        return "redirect:/provider/provider.do";
    }

    //查看供应商信息
    @RequestMapping(value = "/viewProvider.do",method = RequestMethod.GET)
    public String viewProvider(@RequestParam("proid") Integer proid,Model model){
        Provider provider = providerService.findViewProviderById(proid);
        model.addAttribute("provider",provider);
        return "providerview";
    }

    //删除供应商信息
    @RequestMapping(value = "/deleteProvider.do",method = RequestMethod.GET)
    @ResponseBody
    public Object deleteProvider(@RequestParam("proid") Integer proid){
        boolean result = providerService.deleteProviderById(proid);
        String data = null;
        if (result){
            data = "true";
        }else {
            data = "false";
        }
        String dataStr = JSON.toJSONString(data);
        return dataStr;
    }

}
