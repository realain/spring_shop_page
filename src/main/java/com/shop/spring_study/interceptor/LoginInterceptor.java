package com.shop.spring_study.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	@Override //controller에 요청이 들어가기 전
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle :"+request.getServletPath());
		HttpSession session=request.getSession();
		Object memVo_obj=session.getAttribute("memVo");
		if(memVo_obj!=null) {
			return true;			
		}else {
			response.sendRedirect("/mem/login");
			return false;
		}
	}
	@Override //controller에 요청이 끝나고 나서 view에 들어가기 전
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle :"+request.getServletPath());
	}
	@Override //afterCompletion 은 view처리가 끝나고
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion :"+request.getServletPath());
		response.getWriter().append("<script>console.log('afterCompletion까지 작동 완료')</script>");
	}
	
}
