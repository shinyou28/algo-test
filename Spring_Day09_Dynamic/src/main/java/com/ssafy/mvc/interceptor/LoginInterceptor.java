package com.ssafy.mvc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//로그인 했니? 
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null) {
			//서울8반 정재영님 명예!
			response.sendRedirect("login");
			return false;
		}
		
		
		return true;
	}

}
