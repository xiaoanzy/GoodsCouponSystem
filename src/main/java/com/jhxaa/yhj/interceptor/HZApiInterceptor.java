package com.jhxaa.yhj.interceptor;

import com.jhxaa.yhj.exception.BusiException;
import com.jhxaa.yhj.exception.ExceptionEnum;
import com.jhxaa.yhj.utli.EmptyUtil;
import com.jhxaa.yhj.utli.JwtUtil;
import com.jhxaa.yhj.utli.LogUtil;
import com.jhxaa.yhj.utli.TimeUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HZApiInterceptor implements HandlerInterceptor {


    static Logger LOG = LogUtil.getLog(HZApiInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LOG.info(String.format("：：：当前时间%s，进来了token校验拦截器", TimeUtil.getDate()));
        String token = request.getHeader("token");
        if (EmptyUtil.isEmptyString(token)) {
            throw new BusiException(ExceptionEnum.NOT_LOGIN.value());
        }
        Claims claims = JwtUtil.checkToken(token);
        LOG.info(String.format("当前姓名：【%s】", claims.getId()));
        return true;
    }
}
