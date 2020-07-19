package com.jhxaa.yhj.manage.filter;


import com.jhxaa.yhj.utli.LogUtil;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSFilter implements Filter {

    static Logger LOG = LogUtil.getLog(CORSFilter.class);

    public void destroy() {


    }


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;


        LOG.info("--->进来了跨域拦截器");

        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Method", "POST,GET,OPTIONS,DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type,token");
        // 配置options的请求返回
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpStatus.OK.value());
            try {
                response.getWriter().write("OPTIONS returns OK");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            chain.doFilter(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void init(FilterConfig config) {
    }

}
