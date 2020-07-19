package com.jhxaa.yhj.interceptor;


import com.jhxaa.yhj.exception.BusiException;
import com.jhxaa.yhj.exception.ExceptionEnum;
import com.jhxaa.yhj.utli.EmptyUtil;
import com.jhxaa.yhj.utli.JwtUtil;
import com.jhxaa.yhj.utli.LogUtil;
import com.jhxaa.yhj.utli.TimeUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CheckSignInterceptor implements HandlerInterceptor {

    static Logger LOG = LogUtil.getLog(CheckSignInterceptor.class);

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LOG.info(String.format("：：：当前时间%s，进来了token校验拦截器", TimeUtil.getDate()));
        String token = request.getHeader("token");
        //LOG.info(String.format("token码：%s", token));
        if (EmptyUtil.isEmptyString(token)) {
            throw new BusiException(ExceptionEnum.NOT_LOGIN.value());
        }
        Claims claims = JwtUtil.checkToken(token);
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //    System.out.println(response.getStatus());
        //  System.out.println("postHandle");
        //  super.checkHttpStatusCode(response);
        //   System.out.println("请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）:" + response.getStatus());
//         System.out.println("执行了TestInterceptor的postHandle方法");


    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
        //  System.out.println("afterCompletion");
    }
}
