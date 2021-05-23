package com.toleey.appinfo.controller.developer;

import com.alibaba.fastjson.JSON;
import com.toleey.appinfo.pojo.AppCategory;
import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.DataDictionary;
import com.toleey.appinfo.pojo.DevUser;
import com.toleey.appinfo.service.developer.app.DevAppService;
import com.toleey.appinfo.service.developer.user.DevUserService;
import com.toleey.appinfo.tools.PageSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/dev")
public class DeveloperController {
    @Resource(name = "devUserService")
    private DevUserService devUserService;

    public void setDevUserService(DevUserService devUserService) {
        this.devUserService = devUserService;
    }

    @Resource(name = "devAppService")
    private DevAppService devAppService;

    public void setDevAppService(DevAppService devAppService) {
        this.devAppService = devAppService;
    }

    //账号退出
    @RequestMapping("/logout.do")
    public String doLogout(HttpSession session){
        session.invalidate();
        //session.getAttribute("userSession"); 怎么单独销毁这个session？不然一退出开发者也没了
        return "redirect:/index.jsp";
    }

    //返回主页
    @RequestMapping("/goMain.html")
    public String goMain(){
        return "developer/main";
    }

    //进入appinfoList页面
    @RequestMapping("goAppInfoList.html")
    public String goAppInfoList(
            @RequestParam(value = "querySoftwareName",required = false) String querySoftwareName,
            @RequestParam(value = "queryStatus",required = false) Integer queryStatus,
            @RequestParam(value = "queryFlatformId",required = false) Integer queryFlatformId,
            @RequestParam(value = "queryCategoryLevel1",required = false) Integer queryCategoryLevel1,
            @RequestParam(value = "queryCategoryLevel2",required = false) Integer queryCategoryLevel2,
            @RequestParam(value = "queryCategoryLevel3",required = false) Integer queryCategoryLevel3,
            @RequestParam(value = "pageIndex",required = false) Integer pageIndex,
            Model model, HttpSession session
    ){
        DevUser devUser = (DevUser) session.getAttribute("devUserSession");

        //状态
        List<DataDictionary> statusList = devAppService.findAppStatus();
        model.addAttribute("statusList",statusList);
        //平台
        List<DataDictionary> flatFormList = devAppService.findFlatForm();
        model.addAttribute("flatFormList",flatFormList);
        //目录1
        List<AppCategory> categoryLevel1List = devAppService.findCategoryLevel1();
        model.addAttribute("categoryLevel1List",categoryLevel1List);

        //定义每页显示数量
        Integer pageSize = 5 ;
        //进行分页设置
        if (pageIndex == null){
            pageIndex = 1;
        }
        PageSupport pageSupport = new PageSupport(
                pageIndex,//currentPageNo 当前页码
                devAppService.findCountAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3
                        ((int) devUser.getId(),querySoftwareName,queryStatus,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3),
                pageSize
        );
        pageSupport.setTotalPageCountByRs();//计算总页数
        model.addAttribute("pages",pageSupport);

         List<AppInfo> appInfoList = devAppService.
                 findAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3
                         ((int) devUser.getId(),querySoftwareName,queryStatus,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3,(pageSupport.getCurrentPageNo()-1)*pageSize,pageSupport.getPageSize());
         model.addAttribute("appInfoList",appInfoList);

        model.addAttribute("querySoftwareName",querySoftwareName);
        model.addAttribute("queryFlatformId",queryFlatformId);
        model.addAttribute("queryStatus",queryStatus);
        model.addAttribute("queryCategoryLevel1",queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2",queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3",queryCategoryLevel3);
        return "developer/appinfolist";
    }
    //categoryLevel2List AJAX
    @RequestMapping(value = "getCategoryLevel2List.json")
    @ResponseBody
    public Object getCategoryLevel2List(@RequestParam("pid") Integer pid){
        return JSON.toJSONString(devAppService.findCategoryLevel2ByCategoryLevel1(pid));
    }
    //categoryLevel3List AJAX
    @RequestMapping(value = "getCategoryLevel3List.json")
    @ResponseBody
    public Object getCategoryLevel3List(@RequestParam("pid") Integer pid){
        return JSON.toJSONString(devAppService.findCategoryLevel3ByCategoryLevel2(pid));
    }
    //categoryLevel1List AJAX
    @RequestMapping(value = "getCategoryLevel1List.json")
    @ResponseBody
    public Object getCategoryLevel1List(@RequestParam("pid") Integer pid){
        return JSON.toJSONString(devAppService.findCategoryLevel1());
    }
    //flatFormList AJAX
    @RequestMapping(value = "getFlatFormList.json")
    @ResponseBody
    public Object getFlatFormListList(@RequestParam("pid") Integer pid){
        return JSON.toJSONString(devAppService.findFlatForm());
    }
    //APKName AJAX
    @RequestMapping(value = "checkAPKName.json")
    @ResponseBody
    public Object getFlatFormListList(@RequestParam("APKName") String APKName){
        AppInfo appInfo = devAppService.findAppInfoByAPKName(APKName);
        String data = "";
        if (appInfo == null){
            data = "noexist";
        }else {
            data = "exist";
        }
        return JSON.toJSONString(data);
    }
    //新增App基础信息
    @RequestMapping("goAppInfoAdd.html")
    public String goAppInfoAdd(Model model){
        return "developer/appinfoadd";
    }
    //新增App基础信息 表单提交地址
    @RequestMapping("doAppInfoAdd.do")
    public String doAppInfoAdd(

            Model model
    ){
        return "developer/appinfoadd";
    }


}
