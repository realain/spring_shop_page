package com.shop.spring_study.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shop.spring_study.vo.MemberVo;

@Component
public class AdminInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//로그인이 되고 Grade가 2보다 작은 사람만
		HttpSession session=request.getSession();
		Object memVo_obj=session.getAttribute("memVo");
		if(memVo_obj!=null && ((MemberVo)memVo_obj).getGrade()<2) {
			return true;			
		}else{
			session.setAttribute("msg", "접근 권한이 없습니다.");
			response.sendRedirect("/");
			return false;
		}
	}
}
