package com.ezen.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = {"com.ezen.www.controller", "com.ezen.www.service","com.ezen.www.handler","com.ezen.www.exception"})
public class ServletConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 리소스경로 설정 / 나중에 파일 업로드 경로 설정 추가
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/up/**").addResourceLocations("file:///C:\\YJM\\_myproject\\_java\\_fileUpload\\");
		
	}
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// views 경로 설정
		InternalResourceViewResolver viewResovler = new InternalResourceViewResolver();
		viewResovler.setPrefix("/WEB-INF/views/");
		viewResovler.setSuffix(".jsp");
		viewResovler.setViewClass(JstlView.class);
		registry.viewResolver(viewResovler);
	}
	
	//multipartResolver 설정
	//빈 이름이 반드시 multipartResolver 여야 에러가 안남
	@Bean(name="multipartResolver")
	public MultipartResolver getMultipartResolver() {
		StandardServletMultipartResolver multipartResolver = 
				new StandardServletMultipartResolver();
		return multipartResolver;
	}
	
	
}
