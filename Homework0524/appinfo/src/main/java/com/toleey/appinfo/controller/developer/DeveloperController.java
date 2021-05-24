package com.toleey.appinfo.controller.developer;

import com.alibaba.fastjson.JSON;
import com.toleey.appinfo.pojo.AppCategory;
import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.DataDictionary;
import com.toleey.appinfo.pojo.DevUser;
import com.toleey.appinfo.service.developer.app.DevAppService;
import com.toleey.appinfo.service.developer.user.DevUserService;
import com.toleey.appinfo.tools.PageSupport;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
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
    //AllCategoryLevelList AJAX
    @RequestMapping(value = "getAllCategoryLevelList.json")
    @ResponseBody
    public Object getAllCategoryLevelList(){
        return JSON.toJSONString(devAppService.findAllCategoryLevel());
    }
    //flatFormList AJAX
    @RequestMapping(value = "getFlatFormList.json")
    @ResponseBody
    public Object getFlatFormListList(){
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
    public String goAppInfoAdd(@ModelAttribute("AppInfo") AppInfo appInfo, Model model){
        return "developer/appinfoadd";
    }
    //新增App基础信息 表单提交地址
    @RequestMapping("doAppInfoAdd.do")
    public String doAppInfoAdd(
            AppInfo appInfo,
            @RequestParam("a_logoPicPath") MultipartFile multipartFile,
            HttpSession session, HttpServletRequest request, Model model
    ){

        String idPicPath = "";
        DevUser devUser = (DevUser) session.getAttribute("devUserSession");
        if (multipartFile.isEmpty()){//如果图片为空，直接跳过添加部分
            request.setAttribute("fileUploadError","请选择上传的图片");
            return "developer/appinfoadd";
        }else{//如果图片不为空，进行添加操作
            //1.先获得上传文件夹的位置
            String uploadPath = request.getSession().getServletContext().getRealPath("statics"+ File.separator+"uploadfiles");
            //2.获得原来的文件名
            String oldFileName = multipartFile.getOriginalFilename();
            //3.获得原文件的后缀
            String suffix = FilenameUtils.getExtension(oldFileName);
            //4.后缀判断
            if (suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("png")) {
                //5.判断文件大小
                long size = multipartFile.getSize() / 1024; //kb
                if (size > 0 && size < 50) { //判断图片大小 单位Kb 范围0kb - 50kb 不符合
                    //6.给文件命名新的文件名
                    String newFileName = devUser.getDevCode()+"-"+System.currentTimeMillis()+"-devApp."+suffix;
                    //67定义上传路径
                    idPicPath+=uploadPath+File.separator+newFileName;
                    //定义上传文件对象 uploadPath+"/" 不好，win和Linux分隔符相反，自己从系统获取
                    File newFile = new File(uploadPath+File.separator,newFileName);
                    File file = new File(uploadPath);
                    if(!file.exists()){
                        file.mkdir();//如果file路径不存在，就创建一个
                    }
                    try {
                        multipartFile.transferTo(newFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //8.路径写入数据库
                    appInfo.setLogoPicPath(idPicPath); //url
                    appInfo.setLogoLocPath(idPicPath); //服务器存储路径

                }else{ //文件大小不符合
                    String uploadFileError = "文件大小不符合";
                    request.setAttribute("uploadFileError",uploadFileError);
                    return "developer/appinfoadd";
                }
            }else{ //后缀不符合 跳过
                String uploadFileError = "文件后缀不符合";
                request.setAttribute("uploadFileError",uploadFileError);
                return "developer/appinfoadd";
            }
        }

        appInfo.setCreatedBy(devUser.getId());//设置createdBy
        appInfo.setCreationDate(new Timestamp(new Date().getTime()));//设置creationDate
        devAppService.addAnAppInfo(appInfo);
        return "redirect:/dev/goAppInfoList.html";
    }

    //请选择 新增版本
    @RequestMapping("addVersion.html")
    public String addVersion(){
        return "developer/appversionadd";
    }

    //请选择 新增版本 表单提交地址
    @RequestMapping("doVersionAdd.do")
    public String doVersionAdd(){
        return "developer/appversionadd";
    }

    //请选择 修改版本
    @RequestMapping("modifyVersion.html")
    public String updateVersion(){
        return "developer/appversionmodify";
    }

    //请选择 修改版本 表单提交地址
    @RequestMapping("doVersionModify.do")
    public String doVersionUpdate(){
        return "developer/appversionmodify";
    }

    //请选择 修改
    @RequestMapping("modifyAppInfo.html")
    public String modifyAppInfo(){
        return "developer/appinfomodify";
    }

    //请选择 修改 表单提交地址
    @RequestMapping("doAppInfoModify.do")
    public String doAppInfoModify(){
        return "developer/appinfomodify";
    }

    //请选择 查看
    @RequestMapping("viewAppInfo.html")
    public String viewAppInfo(){
        return "developer/appinfoview";
    }

    //请选择 查看 表单提交地址
    @RequestMapping("doAppInfoView.do")
    public String doAppInfoView(){
        return "developer/appinfoview";
    }

    //请选择 删除 AJAX
    @RequestMapping("deleteAppInfo.json")
    @ResponseBody
    public Object deleteAppInfo(@RequestParam("id") Integer id){
        devAppService.deleteAnAppInfoById(id);
        return "";
    }




}
