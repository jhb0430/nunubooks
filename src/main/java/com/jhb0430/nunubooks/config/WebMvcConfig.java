package com.jhb0430.nunubooks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jhb0430.nunubooks.Intercepter.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	
//	@Override -> 정적 파일들의 경로를 잡아주는 메서드
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/images/**");
////		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/");
//	}

	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new PermissionInterceptor())
		.addPathPatterns("/**");
//		.excludePathPatterns("/user/logout", "/static/**", "/images/**");
		
		
		
	}
	
	
}
