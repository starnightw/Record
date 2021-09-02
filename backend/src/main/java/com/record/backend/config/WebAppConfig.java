package com.record.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Along
 * @Date: Created in 10:51 2021/9/2
 **/
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    // @Autowired
    // private CROSInterceptor crosInterceptor;

    @Autowired
    private TokenHandlerInterceptor tokenHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // registry.addInterceptor(crosInterceptor).addPathPatterns("/**");   // 跨域拦截器
        System.out.println("======token 验证拦截器已经开始执行===========");
        registry.addInterceptor(tokenHandlerInterceptor).addPathPatterns("/**");  // token 验证拦截器
    }
}
