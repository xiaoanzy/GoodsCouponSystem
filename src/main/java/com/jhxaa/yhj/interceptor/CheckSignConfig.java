package com.jhxaa.yhj.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CheckSignConfig implements WebMvcConfigurer {

    @Bean
    public ApiSecurityInterceptor getApiSecurityInterceptor() {
        return new ApiSecurityInterceptor();
    }

    @Bean
    public CheckSignInterceptor getCheckSignInterceptor() {
        return new CheckSignInterceptor();
    }


    @Bean
    public HZApiInterceptor getHZApiInterceptor() {
        return new HZApiInterceptor();
    }

    private void addIntercept(InterceptorRegistry registry, HandlerInterceptor handlerInterceptor, String path) {

        registry.addInterceptor(handlerInterceptor).addPathPatterns(path).excludePathPatterns(                         //添加不拦截路径
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.vue",              //js静态资源
                "/**/*.css",             //css静态资源
                "/**/*.woff",
                "/**/*.ttf",
                "/api/roommates/doLogin"
        );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
//        InterceptorRegistration registration = registry.addInterceptor(new CheckSignInterceptor());
//        registration.addPathPatterns(
//                "/**/admin/**"
////                "/**"
//        );
        addIntercept(registry, getApiSecurityInterceptor(), "/api/**");
        addIntercept(registry, getCheckSignInterceptor(), "/api/admin/**");
        addIntercept(registry, getHZApiInterceptor(), "/api/roommates/**");
        //所有路径都被拦截
//        registry.addInterceptor(getCheckSignInterceptor()).addPathPatterns("/api/admin/**").excludePathPatterns(                         //添加不拦截路径
//                "/**/*.html",            //html静态资源
//                "/**/*.js",              //js静态资源
//                "/**/*.vue",              //js静态资源
//                "/**/*.css",             //css静态资源
//                "/**/*.woff",
//                "/**/*.ttf"
//        );
//        registry.addInterceptor(getApiSecurityInterceptor()).addPathPatterns("/api/**").excludePathPatterns(                         //添加不拦截路径
//                "/**/*.html",            //html静态资源
//                "/**/*.js",              //js静态资源
//                "/**/*.vue",              //js静态资源
//                "/**/*.css",             //css静态资源
//                "/**/*.woff",
//                "/**/*.ttf"
//        );


    }
}