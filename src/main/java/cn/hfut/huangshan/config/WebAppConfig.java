package cn.hfut.huangshan.config;//package com.pivot.server.config;
//
//import com.pivot.server.interceptor.BeforeInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.util.List;
//
//@Configuration
//public class WebAppConfig extends WebMvcConfigurationSupport {
//    final private BeforeInterceptor beforeInterceptor;
//
//    @Autowired
//    public WebAppConfig(BeforeInterceptor beforeInterceptor) {
//        this.beforeInterceptor = beforeInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry interceptorRegistry){
////        List<String> strings = null;
////        strings.add("/**");
////        strings.add("/**/**");
////        strings.add("/**/**/**");
//        interceptorRegistry.addInterceptor(beforeInterceptor);
////        super.addInterceptors(interceptorRegistry);
//    }
//}
