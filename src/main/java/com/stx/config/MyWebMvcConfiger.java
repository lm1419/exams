package com.stx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfiger implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("admin/login");
        registry.addViewController("/index.html").setViewName("admin/index");
        registry.addViewController("/login.html").setViewName("admin/login");
        registry.addViewController("/main_admin.html").setViewName("admin/index");
        registry.addViewController("/admin/stumanger.html").setViewName("admin/student_manager");
        registry.addViewController("/test.html").setViewName("admin/exam_add");
    }

    /**
     * 将拦截器配置到IOC中
     * @return
     */
    @Bean
    public HandlerInterceptor myHI(){
        return new MyHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login.html","/","/admin/logins","/css/**","/js/**","/fonts/**","/i/**","/img/**","admin/exitlogin");
    }


}
