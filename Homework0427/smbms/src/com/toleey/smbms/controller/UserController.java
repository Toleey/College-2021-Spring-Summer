package com.toleey.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.validation.Valid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.toleey.smbms.entity.Role;
import com.toleey.smbms.entity.User;
import com.toleey.smbms.exception.LoginException;
import com.toleey.smbms.service.role.RoleService;
import com.toleey.smbms.service.user.UserService;
import com.toleey.smbms.util.Pager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {   //操作用.do 展示用.html
    Logger logger = Logger.getLogger(UserController.class);

    @Resource(name = "userService")
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource(name = "roleService")
    private RoleService roleService;
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    //进入登录页面
    @RequestMapping(value = "/login.html")
    public String login(){
        logger.info("===》展示登陆界面");
        return "login";
    }

    //登陆处理，用户名密码出错使用传统的方式解决
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String doLogin(
            @RequestParam("userCode") String userCode,
            @RequestParam("userPassword")String password,
            Model model, HttpSession session, HttpServletRequest request
    ){
        User user = userService.findUserByUserCode(userCode);
        if (user!=null){
            if (user.getUserPassword().equals(password)) {
                session.setAttribute("userSession", user);
            }else {
                request.setAttribute("error", "密码输入错误");
                return "forwardh:/user/login.html";

                //model.addAttribute("error", "密码输入有误");
                //return "login"; //与视图解析器的前缀后缀匹配 自动添加.jsp   去webinf/jsp/里找  这是映射，默认转发请求
                //通过视图映器映射哪个视图(jsp)
                //return "redirect:/user/login.html";只重定向不转发数据
            }

        }else{
            request.setAttribute("error", "用户名不存在");
            return "forward:/user/login.html";

            //user=null 没有值
            //model.addAttribute("error","用户名不存在"); 当到model浪费
            //还可以用异常来做
            //modelandview mapper model request
            //return "login";
        }
        //return "frame"; 必须转发请求
        return "redirect:/user/main.html";
    }
    //登陆功能，密码用户名出错，使用异常来处理
    @RequestMapping(value = "/exlogin.do",method = RequestMethod.POST)
    public String exdoLogin(
            @RequestParam("userCode") String userCode,
            @RequestParam("userPassword")String password,
            Model model, HttpSession session, HttpServletRequest request
    ){
        User user = userService.findUserByUserCode(userCode);
        if (user!=null){
            if (user.getUserPassword().equals(password)) {
                session.setAttribute("userSession", user);
            }else {
                RuntimeException e = new LoginException("密码输入有误");
                request.setAttribute("error",e.getMessage());
                throw e;
               //throw new Exception("密码输入有误"); 这是检查异常
               //throw new RuntimeException("密码输入有误");//运行时异常
            }
        }else{
            RuntimeException e = new LoginException("用户不存在");
            request.setAttribute("error",e.getMessage());
            throw e;
        }
        return "redirect:/user/main.html";
    }

    //专门进入主页的方法
    @RequestMapping("/main.html")
    public String main(HttpSession session){
        User user = (User) session.getAttribute("userSession");
        if (user!=null){
            return "frame";
        }
        return "login";
    }

//处理本Controller异常 自动处理异常(局部异常处理)-只能处理当前控制器的异常 不如全局异常好，局部只适用于特殊处理，会优先执行局部 异常指的是Spring的异常
//    @ExceptionHandler(value = {RuntimeException.class,})
//    public String handlerException(RuntimeException e,HttpServletRequest req){
//        req.setAttribute("error",e.getMessage());
//        return "login";
//    }

    //退出账号
    @RequestMapping(value = "/logout.do",method = RequestMethod.GET)
    public String doLoginOut(HttpSession session){


        return "login";
    }

    //打开并显示用户列表
    @RequestMapping(value = "/user.do",method = RequestMethod.GET)
    public String userList(
            @RequestParam(value = "queryname",required = false) String userName,
            @RequestParam(value = "queryUserRole",required = false) String userRole,
            @RequestParam(value = "pageIndex",required = false) String currentPageNum,
            Model model
    ){
        //获得角色
        List<Role> roleList = roleService.findRoleList();
        model.addAttribute("roleList",roleList);
        //当前页
        int currentpage = 1;
        if (currentPageNum!=null && !"".equals(currentPageNum)){
            currentpage = Integer.parseInt(currentPageNum);
        }
        model.addAttribute("pageCount",currentpage);
        //总条数
        Integer roleId = null;
        if (userRole!=null && !"".equals(userRole)) {
            roleId = Integer.parseInt(userRole);
        }
        int rowCount = userService.findUserCountByNameAndRole(userName,roleId);
        model.addAttribute("totalCount",rowCount);
        //每页显示条数
        int rowPerPage = 5;
        //总页数
        Pager pager = new Pager(rowCount,rowPerPage,currentpage);
        int pageCount = pager.getPageCount();
        model.addAttribute("totalPageCount",pageCount);
        model.addAttribute("queryUserName",userName);
        model.addAttribute("currentPageNo",currentpage);
        //获得分页记录
        int fromLineNum=(currentpage-1)*rowPerPage;

        List<User> userList = userService.findUserListByNameAndRole(userName,roleId,fromLineNum,rowPerPage);
        model.addAttribute("userList",userList);
        return "userlist";
    }

    //以下是新增操作
    //打开useradd界面
    @RequestMapping(value = "/useradd.html",method = RequestMethod.GET)
    public String addUser(@ModelAttribute("user") User user){
        return "useradd";
    }

    //进行新增操作
    @RequestMapping(value = "/checkUserCodeExists",method = RequestMethod.GET)
    @ResponseBody
    public Object checkUserCodeExists(@RequestParam("userCode") String userCode){
        User user2 = userService.findUserByUserCode(userCode);
        String data = null;
        if (user2 == null){ //编码没找到
            data = "noexists";
        }else{
            data = "exists";
        }
        String dataStr = JSON.toJSONString(data);
        return dataStr;
    }
    //进行新增用户的实际操作，普通表单
//    @RequestMapping(value = "/addUserSave.do",method = RequestMethod.POST)
//    public String addUserSave(@Valid User user,BindingResult bindingResult, HttpSession session){
//        if (bindingResult.hasErrors()){
//            return "/user/useradd";
//        }
//        User loginUser = (User) session.getAttribute("userSession");
//        user.setCreatedBy(loginUser.getId());
//        user.setCreationDate(new Timestamp(new Date().getTime()));
//        userService.addUser(user);
//        return "redirect:/user/user.do";
//    }
    //进行新增操作，Spring表单
//    @RequestMapping(value = "/addUserSave.do",method = RequestMethod.POST)
//    public String addUserSaveSpringForm(User user,
//                                        @RequestParam(value = "a_idPicPath",required = false) MultipartFile multipartFile,
//                                        HttpServletRequest request,HttpSession session){
//
//        String idPicPath = "";
//        User loginUser = (User) session.getAttribute("userSession");
//        if (multipartFile.isEmpty){//如果图片为空，直接跳过添加部分
//            request.setAttribute("uploadFileError","请选择上传的图片");
//            return "useradd";
//        }else{//如果图片不为空，进行添加操作
//            //1.先获得上传文件夹的位置
//            String uploadPath = request.getSession().getServletContext().getRealPath("statics"+ File.separator+"uploadfile");
//                // /Users/toby/Java/Tomcat/apache-tomcat-8.5.59/wtpwebapps/smbms/uploadfile
//            //2.获得原来的文件名
//            String oldFileName = multipartFile.getOriginalFilename();
//                // a.png
//            //3.获得原文件的后缀
//            String suffix = FilenameUtils.getExtension(oldFileName);
//                //png
//            //4.后缀判断
//            if (suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("png") || suffix.equals("pneg")) {
//                //5.判断文件大小
//                long size = multipartFile.getSize() / 1024; //kb
//                System.out.println("size::::::::"+size);
//                if (size > 0 && size < 500) { //判断图片大小 单位Kb 范围0kb - 500kb 不符合
//
//                    // /Users/toby/Java/Tomcat/apache-tomcat-8.5.59/wtpwebapps/smbms/uploadFile/16-312498305809-a.png
//
//                    //6.给文件命名新的文件名
//                    String newFileName = System.currentTimeMillis()+ RandomUtils.nextInt(10000)+"personal."+suffix;
//                    //6.定义上传路径
//                    idPicPath+=uploadPath+File.separator+newFileName;
//
//                    //定义上传文件对象 uploadPath+"/" 不好，win和Linux分隔符相反，自己从系统获取
//                    File newFile = new File(uploadPath+File.separator,newFileName);
//                    File file = new File(uploadPath);
//                    if(!file.exists()){
//                        file.mkdir();//如果file路径不存在，就创建一个
//                    }
//                    try {
//                        multipartFile.transferTo(newFile);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    //6.路径写入数据库
//                    user.setIdPicPath(idPicPath); //写进数据库，图片存储路径
//
//                }else{ //文件大小不符合
//                    String uploadFileError = "文件大小不符合";
//                    request.setAttribute("uploadFileError",uploadFileError);
//                    return "useradd";
//                }
//            }else{ //后缀不符合 跳过
//                String uploadFileError = "文件后缀不符合";
//                request.setAttribute("uploadFileError",uploadFileError);
//                return "useradd";
//            }
//        }
//        //数据库新增操作
//        user.setCreatedBy(loginUser.getId());
//        user.setCreationDate(new Timestamp(new Date().getTime()));
//        userService.addUser(user);
//        return "redirect:/user/user.do";
//    }

    //多文件上传
    @RequestMapping(value = "/addUserSave.do",method = RequestMethod.POST)
    public String addUserSaveSpringForm(User user,                                                  //多个要用数组来接，循环
                                        @RequestParam(value = "attachs",required = false) MultipartFile[] multipartFile,
                                        HttpServletRequest request,HttpSession session){

        String idPicPath = "";
        String workPicPath = "";

        //手动选取两个 multipartFile
        MultipartFile idPicPathFile = multipartFile[0];     //证件照
        MultipartFile workPicPathFile = multipartFile[1];   //工作证照
        if (idPicPathFile.isEmpty()){ //为true时，没上传证件照片
                request.setAttribute("uploadFileError","证件照片未上传");
                return "useradd";
        }else if(workPicPathFile.isEmpty()){ //为true时，没上传工作证照片
                request.setAttribute("uploadWpError","工作证照片未上传");
                return "useradd";
        }else{ //两个照片都上传了
            String uploadPath = request.getSession().getServletContext().getRealPath("statics"+ File.separator+"uploadfile");//获得上传路径
            String oldIdPicPathFileName = idPicPathFile.getOriginalFilename();//获得证件照原文件名
            String oldIdPicPathFileNameSuffix = FilenameUtils.getExtension(oldIdPicPathFileName);//获得工作证照原文件名.后缀
            String oldWorkPicPathFileName = workPicPathFile.getOriginalFilename();//获得工作照原文件名
            String oldWorkPicPathFileNameSuffix = FilenameUtils.getExtension(oldWorkPicPathFileName);//获得工作照原文件名.后缀

            //证件照片 idPicPathFile
            if (oldIdPicPathFileNameSuffix.equals("jpg") || oldIdPicPathFileNameSuffix.equals("jpeg") //后缀判断
                    || oldIdPicPathFileNameSuffix.equals("png") || oldIdPicPathFileNameSuffix.equals("pneg")) {
                long size = idPicPathFile.getSize() / 1024; //kb
                if (size > 0 && size < 500) { //大小判断
                    String newIdPicPathFileName =   //设置新文件名
                            System.currentTimeMillis()+ RandomUtils.nextInt(10000)+"IdPic."+oldIdPicPathFileNameSuffix;
                    idPicPath+=uploadPath+File.separator+newIdPicPathFileName;//拼接新文件路径及名称
                    File newIdPicPathFile = new File(idPicPath);//对目标路径建立映射
                    File file = new File(uploadPath);//如果没有目录就新建
                    if(!file.exists()){file.mkdir();}
                    try {
                        idPicPathFile.transferTo(newIdPicPathFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    user.setIdPicPath(idPicPath); //写进数据库，图片存储路径
                }else{ //文件大小不符合
                    request.setAttribute("uploadFileError","文件大小不符合");
                    return "useradd";
                }
            }else{ //后缀不符合 跳过
                request.setAttribute("uploadFileError","文件后缀不符合");
                return "useradd";
            }

            //工作证照片  workPicPathFile
            if (oldWorkPicPathFileNameSuffix.equals("jpg") || oldWorkPicPathFileNameSuffix.equals("jpeg") //后缀判断
                    || oldWorkPicPathFileNameSuffix.equals("png") || oldWorkPicPathFileNameSuffix.equals("pneg")) {
                long size = workPicPathFile.getSize() / 1024; //kb
                if (size > 0 && size < 500) { //大小判断
                    String newWorkPicPathFileName =   //设置新文件名
                            System.currentTimeMillis()+ RandomUtils.nextInt(10000)+"WorkPic."+oldWorkPicPathFileNameSuffix;
                    workPicPath+=uploadPath+File.separator+newWorkPicPathFileName;//拼接新文件路径及名称
                    File newWorkPicPathFile = new File(workPicPath);//对目标路径建立映射
                    File file = new File(uploadPath);//如果没有目录就新建
                    if(!file.exists()){file.mkdir();}
                    try {
                        workPicPathFile.transferTo(newWorkPicPathFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    user.setWorkPicPath(workPicPath); //写进数据库，图片存储路径
                }else{ //文件大小不符合
                    request.setAttribute("uploadWpError","文件大小不符合");
                    return "useradd";
                }
            }else{ //后缀不符合 跳过
                request.setAttribute("uploadWpError","文件后缀不符合");
                return "useradd";
            }
        }

        User loginUser = (User) session.getAttribute("userSession");
        user.setCreatedBy(loginUser.getId());
        user.setCreationDate(new Timestamp(new Date().getTime()));
        userService.addUser(user);
        return "redirect:/user/user.do";

//        for (int i = 0; i < 0 ; i++){
//            MultipartFile multipartFile1 = multipartFile[i];
//            if (i=0){//传第一个idpic
//            }else{//传第二个workpic
//            }
//        }
    }

    @RequestMapping(value = "/pwdmodify.do",method = RequestMethod.GET)
    public String modifyPwd(){
        return "pwdmodify";
    }

    //修改用户操作
    //显示用户修改界面
    @RequestMapping(value = "/modifyuser.html",method = RequestMethod.GET)
    public String modifyUser(@ModelAttribute User user,@RequestParam("uid") String uid,Model model){
        //查询编号，查询要修改的用户，把用户的值填到修改页面的表单元素中
        Integer id = null;
        if (uid!=null){
            id = Integer.parseInt(uid);
        }
        User user2 = userService.findUserById(id);
        model.addAttribute("user",user2);
        return "/user/usermodify";
    }

    //修改操作
    @RequestMapping(value = "/modifyUserSave.do",method = RequestMethod.POST)
    public String modifyUserSave(@Valid User user,BindingResult bindingResult,HttpSession session){
        //进行操作
        //进行服务器端验证
        if (bindingResult.hasErrors()) {
            return "/user/usermodify";
        }
        User loginUser = (User) session.getAttribute("userSession");
        user.setModifyBy(loginUser.getId());
        user.setModifyDate(new Timestamp(new Date().getTime()));
        boolean boo = userService.modifyUser(user);
        if (!boo){
            return "/user/usermodify";
        }
        return "redirect:/user/user.do";


    }

    //删除操作
    @RequestMapping(value = "/deleteUser.do",method = RequestMethod.GET)
    @ResponseBody
    public Object deleteUser(@RequestParam("uid") Integer uid){
        boolean result =  userService.deleteUser(uid);
        String data = null;
        if (result){
            data = "true";
        }else {
            data = "false";
        }
        String dataStr = JSON.toJSONString(data);
        return dataStr;
    }

    //查看用户
    @RequestMapping(value = "/viewuser.html/{uid}",method = RequestMethod.GET)
    public String viewUser (@PathVariable String uid,Model model,HttpServletRequest request){
        Integer id = null;
        try {
            id = Integer.parseInt(uid);
        }catch (Throwable e){
            e.printStackTrace();
            NumberFormatException nfe = new NumberFormatException("传送数据必须是数值");
            request.setAttribute("errorinfo",nfe.getMessage());
            return "error";


        }

        User user = userService.findViewUserById(id);
        model.addAttribute("user",user);
        return "userview";
    }

    //AJAX验证旧密码
    @RequestMapping(value = "/checkUserPassword.do",method = RequestMethod.GET)
    @ResponseBody
    public Object checkUserPassword (
            @RequestParam("oldpassword") String oldpassword,HttpSession session
    ){
        User loginUser = (User) session.getAttribute("userSession");//获得session中的当前用户id
        String userPassword = userService.findUserPasswordById(loginUser.getId());
        String data = null;

        if(loginUser.getId() == null){
            data = "sessionerror";
        }else if (oldpassword.equals(userPassword)){
            data = "true";
        }else if(oldpassword == null || oldpassword.equals("")){
            data = "error";
        } else if(!oldpassword.equals(userPassword)){
            data = "false";
        }

        String dataStr = JSON.toJSONString(data);
        return dataStr;
    }

    //修改密码
    @RequestMapping(value = "/doUserPasswordSave.do",method = RequestMethod.POST)
    public String pwdModify(@RequestParam("newpassword") String newpassword,HttpSession session){
        User loginUser = (User) session.getAttribute("userSession");
        userService.modifyUserPasswordById(loginUser.getId(),newpassword);
        return "redirect:/user/logout.do";
    }

    //ajax查看用户
    @RequestMapping(value = "/view",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Object viewAjaxUser(@RequestParam("id") String id){

        User user = new User();
        if (id == null || "".equals(id)){
            return JSON.toJSONString("nodata");
        }else {
            try {
                Integer uid = Integer.parseInt(id);
                user = userService.findViewUserById(uid);
            }catch (Exception e){ //数据用可能传过来不是数字
                e.printStackTrace();
                return JSON.toJSONString("failed");
            }

        }
        return JSON.toJSONString(user);
    }



}
