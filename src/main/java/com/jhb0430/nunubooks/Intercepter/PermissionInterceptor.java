package com.jhb0430.nunubooks.Intercepter;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(
			HttpServletRequest request
			,HttpServletResponse response
			, Object handler
			) throws IOException{
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		String uri = request.getRequestURI();
		
				// 로그인이 안된 상태에서  주문과 관련된 기능 페이지의 접근을 막는다 
				// 로그인 페이지로 이동
				if(userId == null) {
					// /cart /order 로 시작하는 url path 확인
					if(uri.startsWith("/cart")) {
						
						// 로그인 페이지로 리다이렉트 정보를 response에 담는다. 
						response.sendRedirect("/nunubooks/account/login");
						return false;
					}
					if(uri.startsWith("/order")) {
						
						// 로그인 페이지로 리다이렉트 정보를 response에 담는다. 
						response.sendRedirect("/nunubooks/account/login");
						return false;
					}
				} 
				
//				로그인이 되어있는 경우는 막을 게 없지 않나...?
//				else {
//					// 로그인이 되어 있는 경우 사용자와 관련된 페이지 접근을 막는다
//					// 메모 리스트 페이지로 이동
//					// /user 로 시작하는 url path 확인
//					if(uri.startsWith("/")) {
//						response.sendRedirect("/nunubooks/main");
//						return false;
//					}
//				}
				
				return true;
		
		
	}
	
	
}
