package com.jhxaa.yhj.controller;

import com.jhxaa.yhj.common.Common;
import com.jhxaa.yhj.response.Result;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/")
@RestController
public class ShutDownController implements ApplicationContextAware {

    private ApplicationContext context;

    @PostMapping("admin/shutDownSystem")
    public Result shutDownContext() {
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) context;
        ctx.close();
        Result<Object> objectResult = new Result<>();
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        objectResult.setMessage(Common.SYSTEM_CLOSE_STRING);
        return objectResult;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
