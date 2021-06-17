package cn.smbms.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.smbms.tools.Constants;

public class SystemInterceptors implements HandlerInterceptor {

	@Override //后
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override //进行中
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override //前
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute(Constants.USER_SESSION)==null) { //为空，证明还未登陆了
			response.sendRedirect(request.getContextPath()+"/401.jsp");//拦截后到401.jsp页面去
			//重定向是从客户端发起的，他的起始URL是HTTP://localhost:8080/
			//请求转发是从服务器端发起的，他的起始URL是HTTP://localhost:8080/context
			return false;
		}
		return true;
	}

}
