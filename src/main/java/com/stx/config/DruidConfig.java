package com.stx.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控
    @Bean
    public ServletRegistrationBean a(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //增加配置
        bean.addInitParameter("loginUserName","admin"); //登录的key是固定的 loginUserName
        bean.addInitParameter("loginPassWord","123456"); //loginPassWord

        //允许谁可以访问
        bean.addInitParameter("allow",""); //参数为空，就是所有人可以访问

        //禁止谁可以访问 initParameters.put("kuangshen","192.168.11.123");

        //后台需要有人登陆，帐号密码登录

        return bean;
    }

    //过滤器配置
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>(new WebStatFilter());


        // /*表示过滤所有请求：
        bean.addUrlPatterns("/*");

        //exclusions：设置哪些请求可以过滤排除掉，从而不进行统计
        bean.addInitParameter("exclusions","*.css,*.js,/druid/*");


        return bean;
    }

}
