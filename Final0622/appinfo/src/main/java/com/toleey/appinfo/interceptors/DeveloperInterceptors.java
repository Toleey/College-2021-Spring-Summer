package com.toleey.appinfo.interceptors;

import com.toleey.appinfo.tools.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeveloperInterceptors implements HandlerInterceptor  {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(Constants.DEV_USER_SESSION)==null) { //为空，证明还未登陆了
            response.sendRedirect(request.getContextPath()+"/403.jsp");//拦截后到401.jsp页面去
            //重定向是从客户端发起的，他的起始URL是HTTP://localhost:8080/
            //请求转发是从服务器端发起的，他的起始URL是HTTP://localhost:8080/context
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
