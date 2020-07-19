package com.jhxaa.yhj.interceptor;

import com.jhxaa.yhj.exception.BusiException;
import com.jhxaa.yhj.exception.ExceptionEnum;
import com.jhxaa.yhj.manage.redis.RedisUtil;
import com.jhxaa.yhj.utli.CommonUtil;
import com.jhxaa.yhj.utli.EmptyUtil;
import com.jhxaa.yhj.utli.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiSecurityInterceptor implements HandlerInterceptor {

    static Logger LOG = LogUtil.getLog(ApiSecurityInterceptor.class);

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String ip = CommonUtil.getIp(request);
        Object o = redisUtil.get(ip);
        Integer count = null;
        if (EmptyUtil.isEmpty(o)) {
            count = 0;
        } else {
            count = (Integer) o;
            if (count > 4) {
                throw new BusiException(ExceptionEnum.HTTP_REQUEST_ERROR_FREQUENCY.value());
            }
        }
        redisUtil.set(ip, count + 1, 3);
        return true;
    }

}
