package cn.smbms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()  //user处理需要拦截
@RequestMapping("/sys/user")
public class UserController {
	@RequestMapping(value = "/main.html",method = RequestMethod.GET)
	public String main() {
		return "frame";
	}

}
