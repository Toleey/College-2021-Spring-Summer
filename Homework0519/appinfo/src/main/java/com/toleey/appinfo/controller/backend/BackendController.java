package com.toleey.appinfo.controller.backend;

import com.alibaba.fastjson.JSON;
import com.toleey.appinfo.pojo.AppCategory;
import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.BackendUser;
import com.toleey.appinfo.pojo.DataDictionary;
import com.toleey.appinfo.service.backend.app.BackendAppService;
import com.toleey.appinfo.service.backend.user.BackendUserService;
import com.toleey.appinfo.tools.PageSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/backend")
public class BackendController {

    @Resource(name = "backendUserService")
    private BackendUserService backendUserService;

    public void setBackendUserService(BackendUserService backendUserService) {
        this.backendUserService = backendUserService;
    }

    @Resource(name = "backendAppService")
    private BackendAppService backendAppService;

    public void setBackendAppService(BackendAppService backendAppService) {
        this.backendAppService = backendAppService;
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
        return "backend/main";
    }

    //进入app审核页面
    @RequestMapping("/goAppList")
    public String goAppList(
            @RequestParam(value = "querySoftwareName",required = false) String querySoftwareName,
            @RequestParam(value = "queryFlatformId",required = false) Integer queryFlatformId,
            @RequestParam(value = "queryCategoryLevel1",required = false) Integer queryCategoryLevel1,
            @RequestParam(value = "queryCategoryLevel2",required = false) Integer queryCategoryLevel2,
            @RequestParam(value = "queryCategoryLevel3",required = false) Integer queryCategoryLevel3,
            @RequestParam(value = "pageIndex",required = false) Integer pageIndex,
            Model model){

        //平台
        List<DataDictionary> flatFormList = backendAppService.findAllFlatForm();
        model.addAttribute("flatFormList",flatFormList);
        //目录1
        List<AppCategory> categoryLevel1List = backendAppService.findCategoryLevel1();
        model.addAttribute("categoryLevel1List",categoryLevel1List);

        //进行分页设置
        if (pageIndex == null){
            pageIndex = 1;
        }
        PageSupport pageSupport = new PageSupport(
                pageIndex,
                backendAppService.findCountAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3
                (querySoftwareName,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3),
                5
        );
        pageSupport.setTotalPageCountByRs();//计算总页数
        model.addAttribute("pages",pageSupport);

        //AppInfo数据
        List<AppInfo> appInfoList = backendAppService.
                findAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3
                        (querySoftwareName,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3,pageSupport.getCurrentPageNo(),pageSupport.getPageSize());

        model.addAttribute("appInfoList",appInfoList);
        return "backend/applist";
    }
    //categoryLevel2List AJAX
    @RequestMapping(value = "/getCategoryLevel2List.json")
    @ResponseBody
    public Object getCategoryLevel2List(@RequestParam("pid") String pid){
        Integer id = null;
        if (pid != null && !pid.equals("")) {
            id = Integer.parseInt(pid);
        }
        List<AppCategory> categoryLevel2List = backendAppService.findCategoryLevel2ByCategoryLevel1(id);
        return JSON.toJSONString(categoryLevel2List);
    }
    //categoryLevel3List AJAX
    @RequestMapping("/getCategoryLevel3List.json")
    @ResponseBody
    public Object getCategoryLevel3List(@RequestParam("pid") Integer pid){
        List<AppCategory> categoryLevel3List = backendAppService.findCategoryLevel3ByCategoryLevel2(pid);
        return JSON.toJSONString(categoryLevel3List);
    }
    //去往app审核页面
    @RequestMapping("/goAppCheck.html")
    public String goAppCheck(){
        return "backend/appcheck";
    }



}
