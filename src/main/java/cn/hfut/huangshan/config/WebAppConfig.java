//package cn.hfut.huangshan.config;
//
//import cn.hfut.huangshan.interceptor.MyInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
///**
// * 配置拦截器
// * @author pcy
// */
//@Configuration
//public class WebAppConfig extends WebMvcConfigurationSupport {
//
//    @Autowired
//    MyInterceptor myInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/tourist/register");
//    }
//}
