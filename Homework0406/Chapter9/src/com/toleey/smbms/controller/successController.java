package com.toleey.smbms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("User")
public class successController {



    @RequestMapping(value = "/userCode1",method = RequestMethod.GET)
    public ModelAndView successModelAndView(
            @RequestParam(value = "userCode",required = false) String userCode
    ){
        ModelAndView mav = new ModelAndView();
        mav.addObject("userCode",userCode);
        mav.setViewName("success");
        return mav;
    }

    @RequestMapping(value = "/userCode2",method = RequestMethod.GET)
    public String successModel(
            @RequestParam(value = "userCode",required = false) String userCode,
            Model model
     ){
        model.addAttribute("userCode",userCode);
        return "success";
    }

    @RequestMapping(value = "/userCode3",method = RequestMethod.GET)
    public String successMap(
            @RequestParam(value = "userCode",required = false) String userCode,
            Map<String,Object> model
    ){
        model.put("userCode",userCode);
        return "success";
    }
}
