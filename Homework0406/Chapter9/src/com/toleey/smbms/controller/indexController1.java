package com.toleey.smbms.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class indexController1 extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
    ) throws Exception {

        ModelAndView view = new ModelAndView();
        view.setViewName("/index.jsp");

        return view;
    }
}
