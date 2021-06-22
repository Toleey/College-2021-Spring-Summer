package com.toleey.appinfo.controller.developer;

import com.alibaba.fastjson.JSON;
import com.toleey.appinfo.pojo.*;
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
import java.io.Console;
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
    public String doLogout(HttpSession session) {
        session.invalidate();
        //session.getAttribute("userSession"); 怎么单独销毁这个session？不然一退出开发者也没了
        return "redirect:/index.jsp";
    }

    //返回主页
    @RequestMapping("/goMain.html")
    public String goMain() {
        return "developer/main";
    }

    //进入appinfoList页面
    @RequestMapping("goAppInfoList.html")
    public String goAppInfoList(
            @RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
            @RequestParam(value = "queryStatus", required = false) Integer queryStatus,
            @RequestParam(value = "queryFlatformId", required = false) Integer queryFlatformId,
            @RequestParam(value = "queryCategoryLevel1", required = false) Integer queryCategoryLevel1,
            @RequestParam(value = "queryCategoryLevel2", required = false) Integer queryCategoryLevel2,
            @RequestParam(value = "queryCategoryLevel3", required = false) Integer queryCategoryLevel3,
            @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
            Model model, HttpSession session
    ) {
        DevUser devUser = (DevUser) session.getAttribute("devUserSession");

        //状态
        List<DataDictionary> statusList = devAppService.findAppStatus();
        model.addAttribute("statusList", statusList);
        //平台
        List<DataDictionary> flatFormList = devAppService.findFlatForm();
        model.addAttribute("flatFormList", flatFormList);
        //目录1
        List<AppCategory> categoryLevel1List = devAppService.findCategoryLevel1();
        model.addAttribute("categoryLevel1List", categoryLevel1List);

        //定义每页显示数量
        Integer pageSize = 5;
        //进行分页设置
        if (pageIndex == null) {
            pageIndex = 1;
        }
        PageSupport pageSupport = new PageSupport(
                pageIndex,//currentPageNo 当前页码
                devAppService.findCountAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3
                        ((int) devUser.getId(), querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3),
                pageSize
        );
        pageSupport.setTotalPageCountByRs();//计算总页数
        model.addAttribute("pages", pageSupport);

        List<AppInfo> appInfoList = devAppService.
                findAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3
                        ((int) devUser.getId(), querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, (pageSupport.getCurrentPageNo() - 1) * pageSize, pageSupport.getPageSize());
        model.addAttribute("appInfoList", appInfoList);

        model.addAttribute("querySoftwareName", querySoftwareName);
        model.addAttribute("queryFlatformId", queryFlatformId);
        model.addAttribute("queryStatus", queryStatus);
        model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
        model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
        model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
        return "developer/appinfolist";
    }

    //categoryLevel2List AJAX
    @RequestMapping(value = "getCategoryLevel2List.json")
    @ResponseBody
    public Object getCategoryLevel2List(@RequestParam("pid") Integer pid) {
        return JSON.toJSONString(devAppService.findCategoryLevel2ByCategoryLevel1(pid));
    }

    //categoryLevel3List AJAX
    @RequestMapping(value = "getCategoryLevel3List.json")
    @ResponseBody
    public Object getCategoryLevel3List(@RequestParam("pid") Integer pid) {
        return JSON.toJSONString(devAppService.findCategoryLevel3ByCategoryLevel2(pid));
    }

    //categoryLevel1List AJAX
    @RequestMapping(value = "getCategoryLevel1List.json")
    @ResponseBody
    public Object getCategoryLevel1List(@RequestParam("pid") Integer pid) {
        return JSON.toJSONString(devAppService.findCategoryLevel1());
    }

    //AllCategoryLevelList AJAX
    @RequestMapping(value = "getAllCategoryLevelList.json")
    @ResponseBody
    public Object getAllCategoryLevelList() {
        return JSON.toJSONString(devAppService.findAllCategoryLevel());
    }

    //flatFormList AJAX
    @RequestMapping(value = "getFlatFormList.json")
    @ResponseBody
    public Object getFlatFormListList() {
        return JSON.toJSONString(devAppService.findFlatForm());
    }

    //APKName AJAX
    @RequestMapping(value = "checkAPKName.json")
    @ResponseBody
    public Object getFlatFormListList(@RequestParam("APKName") String APKName) {
        AppInfo appInfo = devAppService.findAppInfoByAPKName(APKName);
        String data = "";
        if (appInfo == null) {
            data = "noexist";
        } else {
            data = "exist";
        }
        return JSON.toJSONString(data);
    }

    //新增App基础信息
    @RequestMapping("goAppInfoAdd.html")
    public String goAppInfoAdd(@ModelAttribute("AppInfo") AppInfo appInfo, Model model) {
        return "developer/appinfoadd";
    }

    //新增App基础信息 表单提交地址
    @RequestMapping("doAppInfoAdd.do")
    public String doAppInfoAdd(
            AppInfo appInfo,
            @RequestParam("a_logoPicPath") MultipartFile multipartFile,
            HttpSession session, HttpServletRequest request
    ) {

        if (multipartFile.isEmpty()) {//如果图片为空，直接跳过添加部分
            request.setAttribute("fileUploadError", "请选择上传的图片");
            return "developer/appinfoadd";
        } else {//如果图片不为空，进行添加操作
            //1.先获得上传文件夹的位置
            String uploadPath = "/Users/toby/Java/appinfo/src/main/webapp/statics/uploadfiles";//LOGO图片的服务器存储路径
            //2.获得原来的文件名 a
            String oldFileName = multipartFile.getOriginalFilename();
            //3.获得原文件的后缀 png
            String suffix = FilenameUtils.getExtension(oldFileName);
            //4.后缀判断
            if (suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("png")) {
                //5.判断文件大小
                long size = multipartFile.getSize() / 1024; //kb
                if (size > 0 && size < 50) { //判断图片大小 单位Kb 范围0kb - 50kb 不符合
                    //6.给文件命名新的文件名
                    String newFileName = appInfo.getAPKName() + "-" + appInfo.getVersionNo() + "-devApp." + suffix;
                    //7.定义上传文件对象
                    File newFile = new File(uploadPath + File.separator, newFileName);
                    //8.进行上传操作
                    try {
                        multipartFile.transferTo(newFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String logoPicPath = "/AppInfoSystem/statics/uploadfiles/" + newFileName;//LOGO图片url路径
                    appInfo.setLogoPicPath(logoPicPath); //url

                    String logoLocPath = "/Users/toby/Java/appinfo/src/main/webapp/statics/uploadfiles/" + newFileName;//LOGO图片的服务器存储路径
                    appInfo.setLogoLocPath(logoLocPath); //服务器存储路径

                    DevUser devUser = (DevUser) session.getAttribute("devUserSession");
                    appInfo.setCreatedBy(devUser.getId());//设置createdBy
                    appInfo.setCreationDate(new Timestamp(new Date().getTime()));//设置creationDate
                    appInfo.setDevId(devUser.getId());
                    devAppService.addAnAppInfo(appInfo);
                    return "redirect:/dev/goAppInfoList.html";

                } else { //文件大小不符合
                    String uploadFileError = "文件大小不符合";
                    request.setAttribute("uploadFileError", uploadFileError);
                    return "developer/appinfoadd";
                }
            } else { //后缀不符合 跳过
                String uploadFileError = "文件后缀不符合";
                request.setAttribute("uploadFileError", uploadFileError);
                return "developer/appinfoadd";
            }
        }
    }

    //请选择 新增版本
    @RequestMapping("addVersion.html")
    public String addVersion(
            @ModelAttribute("AppVersion") AppVersion appVersion,
            @RequestParam("id") Integer id,
            Model model
    ) {
        List<AppVersion> appVersionList = devAppService.findAppVersionByAppId(id);
        model.addAttribute("appVersionList", appVersionList);
        return "developer/appversionadd";
    }

    //请选择 新增版本 表单提交地址
    @RequestMapping("doVersionAdd.do")
    public String doVersionAdd(
            AppVersion appVersion,
            @RequestParam("a_downloadLink") MultipartFile multipartFile,
            HttpSession session, HttpServletRequest request, Model model
    ) {
        if (multipartFile.isEmpty()){//如果图片为空，直接跳过添加部分
            request.setAttribute("fileUploadError","请选择上传的文件");
            return "developer/appversionadd";
        }else{//如果图片不为空，进行添加操作
            //1.先获得上传文件夹的位置
            String uploadPath = "/Users/toby/Java/Projects/appinfo/src/main/webapp/statics/uploadfiles";
            //2.获得原来的文件名
            String oldFileName = multipartFile.getOriginalFilename();
            //3.获得原文件的后缀
            String suffix = FilenameUtils.getExtension(oldFileName);
            //4.后缀判断
            if (suffix.equals("apk")) {
                //5.判断文件大小
                long size = multipartFile.getSize() / 1024*1024; //MB
                if (size > 0 && size < 500) { //判断图片大小 单位Kb 范围0kb - 500MB 不符合
                    //6.给文件命名新的文件名
                    String newFileName = appVersion.getAppName()+"-"+appVersion.getVersionNo()+"-devApp."+suffix;
                    //7.定义上传文件对象
                    File newFile = new File(uploadPath+File.separator,newFileName);
                    //9.上传文件操作
                    try {
                        multipartFile.transferTo(newFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //10.数据库写入路径
                    String apkPath = "/AppInfoSystem/statics/uploadfiles/"+newFileName;

                    //路径数据库
                    appVersion.setDownloadLink(apkPath); // /Users/toby/Java/Projects/appinfo/src/main/webapp/statics/uploadfiles/a.apk
                    appVersion.setApkLocPath(uploadPath+File.separator+newFileName);// ../statics/uploadfiles/a.apk
                    appVersion.setApkFileName(newFileName); //a.apk

                    DevUser devUser = (DevUser) session.getAttribute("devUserSession");
                    appVersion.setCreatedBy(devUser.getId());//设置createdBy
                    appVersion.setCreationDate(new Timestamp(new Date().getTime()));//设置creationDate

                    devAppService.addAnAppVersion(appVersion);
                    return "redirect:/dev/goAppInfoList.html";

                }else{ //文件大小不符合
                    String uploadFileError = "文件大小不符合";
                    request.setAttribute("uploadFileError",uploadFileError);
                    return "developer/appversionadd";
                }
            }else{ //后缀不符合 跳过
                String uploadFileError = "文件后缀不符合";
                request.setAttribute("uploadFileError",uploadFileError);
                return "developer/appversionadd";
            }
        }

    }

    //请选择 修改版本
    @RequestMapping("modifyVersion.html")
    public String updateVersion(
            AppVersion appVersion,
            @RequestParam("vid") Integer vid,
            @RequestParam("aid") Integer aid,
            Model model
    ) {
        List<AppVersion> appVersionList = devAppService.findAppVersionByAppId(aid);
        model.addAttribute("appVersionList", appVersionList);
        AppVersion appVersion2 = devAppService.findAnAppVersionById(vid);
        model.addAttribute("appVersion", appVersion2);

        return "developer/appversionmodify";
    }

    //请选择 修改版本 表单提交地址
    @RequestMapping("doVersionModify.do")
    public String doVersionUpdate(
            AppVersion appVersion,
            @RequestParam("attach") MultipartFile multipartFile,
            HttpSession session, HttpServletRequest request
    ) {

        if (multipartFile.isEmpty()) {//如果图片为空，直接跳过添加部分
            request.setAttribute("fileUploadError", "请选择上传的文件");
            return "developer/appversionmodify";
        } else {//如果图片不为空，进行添加操作
            //1.先获得上传文件夹的位置
            String uploadPath = "/Users/toby/Java/Projects/appinfo/src/main/webapp/statics/uploadfiles";
            //2.获得原来的文件名
            String oldFileName = multipartFile.getOriginalFilename();
            //3.获得原文件的后缀
            String suffix = FilenameUtils.getExtension(oldFileName);
            //4.后缀判断
            if (suffix.equals("apk")) {
                //5.判断文件大小
                long size = multipartFile.getSize() / 1024 * 1024; //MB
                if (size > 0 && size < 500) { //判断图片大小 单位Kb 范围0kb - 500MB 不符合
                    //6.给文件命名新的文件名
                    String newFileName = appVersion.getAppName() + "-" + appVersion.getVersionNo() + "-devApp." + suffix;
                    //7.定义上传文件对象
                    File newFile = new File(uploadPath + File.separator, newFileName);
                    //9.上传文件操作
                    try {
                        multipartFile.transferTo(newFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //10.数据库写入路径
                    String apkPath = "/AppInfoSystem/statics/uploadfiles/" + newFileName;

                    //路径数据库
                    appVersion.setDownloadLink(apkPath); // /Users/toby/Java/Projects/appinfo/src/main/webapp/statics/uploadfiles/a.apk
                    appVersion.setApkLocPath(uploadPath + File.separator + newFileName);// ../statics/uploadfiles/a.apk
                    appVersion.setApkFileName(newFileName); //a.apk

                    DevUser devUser = (DevUser) session.getAttribute("devUserSession");
                    appVersion.setModifyBy(devUser.getId());//设置createdBy
                    appVersion.setModifyDate(new Timestamp(new Date().getTime()));//设置creationDate

                    devAppService.updateAnAppVersion(appVersion);
                    return "developer/appversionmodify";


                } else { //文件大小不符合
                    String uploadFileError = "文件大小不符合";
                    request.setAttribute("uploadFileError", uploadFileError);
                    return "developer/appversionmodify";
                }
            } else { //后缀不符合 跳过
                String uploadFileError = "文件后缀不符合";
                request.setAttribute("uploadFileError", uploadFileError);
                return "developer/appversionmodify";
            }
        }

    }

    //请选择 修改
    @RequestMapping("modifyAppInfo.html")
    public String modifyAppInfo(
            @ModelAttribute("appInfo") AppInfo appInfo,
            @RequestParam("id") Integer id,
            Model model
    ) {
        AppInfo appInfo2 = devAppService.findAnAppInfoByIdToUpdate(id);
        model.addAttribute("appInfo", appInfo2);
        return "developer/appinfomodify";
    }

    //请选择 修改 表单提交地址
    @RequestMapping("doAppInfoModify.do")
    public String doAppInfoModify(
            AppInfo appInfo,
            @RequestParam("attach") MultipartFile multipartFile,
            HttpServletRequest request, HttpSession session
    ) {

        if (multipartFile.isEmpty()) {//如果图片为空，直接跳过添加部分
            request.setAttribute("fileUploadError", "请选择上传的图片");
            return "developer/appinfomodify";
        } else {//如果图片不为空，进行添加操作
            //1.先获得上传文件夹的位置
            String uploadPath = "/Users/toby/Java/appinfo/src/main/webapp/statics/uploadfiles";//LOGO图片的服务器存储路径
            //2.获得原来的文件名 a
            String oldFileName = multipartFile.getOriginalFilename();
            //3.获得原文件的后缀 png
            String suffix = FilenameUtils.getExtension(oldFileName);
            //4.后缀判断
            if (suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("png")) {
                //5.判断文件大小
                long size = multipartFile.getSize() / 1024; //kb
                if (size > 0 && size < 50) { //判断图片大小 单位Kb 范围0kb - 50kb 不符合
                    //6.给文件命名新的文件名
                    String newFileName = appInfo.getAPKName() + "-" + appInfo.getVersionNo() + "-devApp." + suffix;
                    //7.定义上传文件对象
                    File newFile = new File(uploadPath + File.separator, newFileName);
                    //8.进行上传操作
                    try {
                        multipartFile.transferTo(newFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String logoPicPath = "/AppInfoSystem/statics/uploadfiles/" + newFileName;//LOGO图片url路径
                    appInfo.setLogoPicPath(logoPicPath); //url

                    String logoLocPath = "/Users/toby/Java/appinfo/src/main/webapp/statics/uploadfiles/" + newFileName;//LOGO图片的服务器存储路径
                    appInfo.setLogoLocPath(logoLocPath); //服务器存储路径

                    DevUser devUser = (DevUser) session.getAttribute("devUserSession");
                    appInfo.setModifyBy(devUser.getId());
                    appInfo.setModifyDate(new Timestamp(new Date().getTime()));

                    devAppService.updateAnAppInfo(appInfo);
                    return "redirect:/dev/goAppInfoList.html";


                } else { //文件大小不符合
                    String uploadFileError = "文件大小不符合";
                    request.setAttribute("uploadFileError", uploadFileError);
                    return "developer/appinfomodify";
                }
            } else { //后缀不符合 跳过
                String uploadFileError = "文件后缀不符合";
                request.setAttribute("uploadFileError", uploadFileError);
                return "developer/appinfomodify";
            }
        }


    }

    //请选择 查看
    @RequestMapping("viewAppInfo.html")
    public String viewAppInfo(@RequestParam("id") Integer id, Model model) {
        AppInfo appInfo = devAppService.findAnAppInfoById(id);
        model.addAttribute("appInfo", appInfo);
        List<AppVersion> appVersionList = devAppService.findAppVersionByAppId(id);
        model.addAttribute("appVersionList", appVersionList);
        return "developer/appinfoview";
    }


    //请选择 删除 AJAX
    @RequestMapping("deleteAppInfo.json")
    @ResponseBody
    public Object deleteAppInfo(@RequestParam("id") Integer id) {
        boolean result = devAppService.deleteAnAppInfoById(id);
        String data = "";
        if (result = true) {
            data = "true";
        } else {
            data = "false";
        }
        return JSON.toJSONString(data);
    }

    //删除APK文件
    @RequestMapping("deleteAnAPKFile.json")
    @ResponseBody
    public Object deleteAnAPKFile(@RequestParam("id") Integer id) {
        boolean result = devAppService.updateAnAPKFileToNull(id);
        String data = "";
        if (result == true) {
            data = "success";
        } else {
            data = "failed";
        }
        return JSON.toJSONString(data);
    }

    //删除图片文件
    @RequestMapping("deleteALogoPicture.json")
    @ResponseBody
    public Object deleteALogoPicture(@RequestParam("id") Integer id) {
        boolean result = devAppService.updateALogoPictureToNull(id);
        String data = "";
        if (result == true) {
            data = "success";
        } else {
            data = "failed";
        }
        return JSON.toJSONString(data);
    }

    //App上架
    @RequestMapping("appLaunch.json")
    @ResponseBody
    public Object appLaunch(@RequestParam("appId") Integer appId) {
        System.out.println(appId);
        boolean result = devAppService.updateAppInfoStatusLaunch(appId);
        String data = "";
        if (result == true) {
            data = "success";
        } else {
            data = "failed";
        }
        return JSON.toJSONString(data);
    }

    //App下架
    @RequestMapping("appRemoval.json")
    @ResponseBody
    public Object appRemoval(@RequestParam("appId") Integer appId) {
        boolean result = devAppService.updateAppInfoStatusRemoval(appId);
        String data = "";
        if (result == true) {
            data = "success";
        } else {
            data = "failed";
        }
        return JSON.toJSONString(data);
    }

    //AppModify getCategoryLevelList AJAX by pid
    //方法会调用三次，一次查询一次CategoryLevel
    @RequestMapping("getCategoryLevelListByPid.json")
    @ResponseBody
    public Object getCategoryLevelListByPid(@RequestParam("pid") Integer pid) {
        if (pid == null) {
            return JSON.toJSONString(devAppService.findCategoryLevel1());
        } else {
            List<AppCategory> CategoryLevel2 = devAppService.findCategoryLevel2ByCategoryLevel1(pid);
            if (CategoryLevel2 == null) {
                return JSON.toJSONString(devAppService.findCategoryLevel3ByCategoryLevel2(pid));
            } else {
                return JSON.toJSONString(CategoryLevel2);
            }

        }
    }
}
