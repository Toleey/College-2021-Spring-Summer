package cn.smbms.controller;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Pager;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller  //user处理需要拦截
@RequestMapping("/sys/user")
public class UserController {

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
	
    @RequestMapping("/main.html")
    public String main(HttpSession session){
        User user = (User) session.getAttribute("userSession");
        if (user!=null){
            return "frame";
        }
        return "login";
    }

	//退出账号
	@RequestMapping(value = "/logout.do",method = RequestMethod.GET)
	public String doLoginOut(HttpSession session){
		 session.invalidate();
		return "redirect:/login.html";
	}

	//打开并显示用户列表
	@RequestMapping(value = "/user.do",method = RequestMethod.GET)
	public String userList(
			@RequestParam(value = "queryname",required = false) String userName,
			@RequestParam(value = "queryUserRole",required = false) String userRole,
			@RequestParam(value = "pageIndex",required = false) String currentPageNum,
			Model model
	){
		model.addAttribute("queryname",userName);
		model.addAttribute("queryUserRole",userRole);
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
		return "redirect:/sys/user/user.do";

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
		return "usermodify";
	}

	//修改操作
	@RequestMapping(value = "/modifyUserSave.do",method = RequestMethod.POST)
	public String modifyUserSave(@Valid User user, BindingResult bindingResult, HttpSession session){
		//进行操作
		//进行服务器端验证
		if (bindingResult.hasErrors()) {
			return "usermodify";
		}
		User loginUser = (User) session.getAttribute("userSession");
		user.setModifyBy(loginUser.getId());
		user.setModifyDate(new Timestamp(new Date().getTime()));
		boolean boo = userService.modifyUser(user);
		if (!boo){
			return "usermodify";
		}
		return "redirect:/sys/user/user.do";


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
		return "redirect:/sys/user/logout.do";
	}

	//ajax查看用户
	@RequestMapping(value = "/view2",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
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
		return user; //不要直接返回JSON，让配置来转换成JSON
	}

	//多视图处理控制器
	@RequestMapping(value = "/view",method = RequestMethod.GET)
	@ResponseBody
	public Object viewUserMulti(@RequestParam("id") String id){
		Integer uid= null;
		if (id != null && !"".equals(id)){
			uid = Integer.parseInt(id);
		}
		User user = userService.findViewUserById(uid);
		return user;
	}




}
