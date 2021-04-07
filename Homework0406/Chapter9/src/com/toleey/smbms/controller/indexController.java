package com.toleey.smbms.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.toleey.smbms.entity.User;

import java.util.Map;

@Controller
@RequestMapping("User") //    http://localhost:8080/Chapte9/user/index
public class indexController {


        Logger log = Logger.getLogger(indexController.class);

        @RequestMapping("/index") //url地址
        // 访问xxx/index访问下面的index.jsp
        public String index(){ //String 返回映射的地址
            return "/index.jsp"; //访问对应的index.jsp
        }

        @RequestMapping("/welcome")
        public String welcome(){
            return "/welcome.jsp";
        }


        @RequestMapping(value = "/index2",method = RequestMethod.GET)
        public String index2(
                @RequestParam(value = "userName",required = false) String userName    //1.浏览器url 2.表单提交 3.AJAX传递 进来
        ){
            System.out.println(userName);
            log.info(userName);
            return "index";
        }

        //Model怎么往View里传值
        //视图和控制器映射,
        //视图把数据传给控制器，控制器把Model数据传给视图
        //方法一：使用ModelAndView对象
        @RequestMapping(value = "/index3",method = RequestMethod.GET)
        public ModelAndView index3(
                @RequestParam(value = "userName",required = false) String userName    //1.浏览器url 2.表单提交 3.AJAX传递 进来
        ){
            //System.out.println("userName");
            log.info(userName);
            ModelAndView mv = new ModelAndView();
            mv.addObject("userName",userName);
            mv.addObject(userName);

            User user = new User();
            user.setUserName(userName);

            mv.addObject("u",user);
            mv.addObject(user);

            mv.setViewName("index");
            return mv;
        }

        //方法二：用Model参数传模型给视图
        @RequestMapping(value = "/index4",method = RequestMethod.GET)
        public String index4(
                @RequestParam(value = "userName",required = false) String userName    //请求参数
                , Model model //视图参数
                 ){
            //System.out.println("userName");
            log.info(userName);

            model.addAttribute("userName",userName);
            model.addAttribute(userName);

            User user = new User();
            user.setUserName(userName);

            model.addAttribute("u",user);
            model.addAttribute(user);

            return "index"; //返回的字符串是视图(view)，model用参数来传
        }

        //方法三：
        @RequestMapping(value = "/index5",method = RequestMethod.GET)
        public String index5(
                @RequestParam(value = "userName",required = false) String userName    //请求参数
                , Map<String,Object> model
        ){
            //System.out.println("userName");
            log.info(userName);

            model.put("userName",userName);
            //model.addAttribute(userName); 类型不能入参 map必须是健值对

            User user = new User();
            user.setUserName(userName);

            model.put("u",user);
            //model.addAttribute(user);

            return "index"; //返回的字符串是视图(view)，model用参数来传
        }
}