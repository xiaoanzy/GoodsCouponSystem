package com.jhxaa.yhj.manage.trigger.job;


import com.jhxaa.yhj.common.Common;
import com.jhxaa.yhj.manage.redis.RedisUtil;
import com.jhxaa.yhj.service.AdminService;
import com.jhxaa.yhj.service.TGoodsInfoService;
import com.jhxaa.yhj.utli.LogUtil;
import com.jhxaa.yhj.utli.PropertiesUtil;
import com.jhxaa.yhj.utli.TimeUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class JobGoodsInfo implements Job {

    static Logger LOG = LogUtil.getLog(JobGoodsInfo.class);
    @Autowired
    TGoodsInfoService tGoodsInfoService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AdminService adminService;

    @Async
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        boolean batchReadExclInsertTGoodsInfo = false;
        boolean b = PropertiesUtil.getBoolean("trigger.job.boolean");
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        boolean dayBol = (day % 2 == 0);
        if (dayBol == b) {
            batchReadExclInsertTGoodsInfo = tGoodsInfoService.batchReadExclInsertTGoodsInfo();
        }
        Boolean aBoolean = false;
        TimeUtil.sleepSeconds(3000L);
        aBoolean = tGoodsInfoService.checkTGoodsInfo();
        TimeUtil.sleepSeconds(5000L);
        adminService.clearRepeatGoods();
        TimeUtil.sleepSeconds(5000L);
        redisUtil.del(Common.REDIS_DEI_NOW_JINGXUAN_GOODS, Common.REDIS_DEI_NOW_MONEY9_GOODS);
        LOG.info(String.format("检查数据：%s,更新添加：%s", aBoolean, batchReadExclInsertTGoodsInfo));
        System.gc();
    }
}