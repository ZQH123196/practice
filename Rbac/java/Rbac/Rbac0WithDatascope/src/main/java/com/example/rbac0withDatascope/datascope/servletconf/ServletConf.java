package com.example.rbac0withDatascope.datascope.servletconf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class ServletConf implements WebMvcConfigurer {

    @Resource
    InitSecurityInterceptor initSecurityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(initSecurityInterceptor);
    }
}
