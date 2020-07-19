package com.jhxaa.yhj.system;

import com.jhxaa.yhj.utli.LogUtil;
import com.jhxaa.yhj.utli.TimeUtil;
import org.slf4j.Logger;

import javax.annotation.PreDestroy;

public class CloseSystem {

    static Logger LOG = LogUtil.getLog(CloseSystem.class);

    @PreDestroy
    public void preDestroy() {
        LOG.warn("正在关闭系统。。。");
        TimeUtil.sleepMilliseconds(3000L);
        LOG.warn("系统已关闭");
    }

}
