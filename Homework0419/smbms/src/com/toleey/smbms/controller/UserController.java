package com.toleey.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.toleey.smbms.entity.Role;
import com.toleey.smbms.entity.User;
import com.toleey.smbms.exception.LoginException;
import com.toleey.smbms.service.role.RoleService;
import com.toleey.smbms.service.user.UserService;
import com.toleey.smbms.util.Pager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
                return "forward:/user/login.html";

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
        return "/user/useradd";
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
    //进行新增用户的实际操作
    @RequestMapping(value = "/addUserSave.do",method = RequestMethod.POST)
    public String addUserSave(User user,HttpSession session){
        User loginUser = (User) session.getAttribute("userSession");
        user.setCreatedBy(loginUser.getId());
        user.setCreationDate(new Timestamp(new Date().getTime()));
        userService.addUser(user);
        return "redirect:/user/user.do";
    }

    @RequestMapping(value = "/pwdmodify.do",method = RequestMethod.GET)
    public String modifyPwd(){
        return "pwdmodify";
    }

}
