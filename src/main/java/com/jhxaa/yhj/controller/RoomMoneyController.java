package com.jhxaa.yhj.controller;

import com.jhxaa.yhj.common.Common;
import com.jhxaa.yhj.exception.BusiException;
import com.jhxaa.yhj.exception.ExceptionEnum;
import com.jhxaa.yhj.pojo.TRoommatesMoneySystemCurrentMoney;
import com.jhxaa.yhj.pojo.TRoommatesMoneySystemUser;
import com.jhxaa.yhj.request.RoomMoneyRequestBean;
import com.jhxaa.yhj.response.Result;
import com.jhxaa.yhj.service.RoomMoneyService;
import com.jhxaa.yhj.utli.EmptyUtil;
import com.jhxaa.yhj.utli.LogUtil;
import com.jhxaa.yhj.vo.RecordMoneyVo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class RoomMoneyController {

    Logger log = LogUtil.getLog(RoomMoneyController.class);

    @Autowired
    RoomMoneyService roomMoneyService;

    @RequestMapping("/roommates/findOneUser")
    public Object findOneUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        TRoommatesMoneySystemUser user = roomMoneyService.getUserBytoken(token);
        Result<Object> result = new Result<>();
        result.setCode(Common.SYSTEM_SESSUES_0);
        result.setMessage(Common.SYSTEM_RES_OK);
        result.setData(user);
        return result;
    }


    @RequestMapping("/roommates/doLogin")
    public Object login(RoomMoneyRequestBean roomMoneyRequestBean) {
        String userName = roomMoneyRequestBean.getUserName();
        String passWord = roomMoneyRequestBean.getPassWord();
        if (EmptyUtil.isEmpty(userName) || EmptyUtil.isEmpty(passWord)) {
            throw new BusiException(ExceptionEnum.SERVICE_PARAM_EMPTY.value());
        }
        String login = roomMoneyService.doLogin(userName, passWord);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", login);
        Result<Object> result = new Result<>();
        result.setCode(Common.SYSTEM_SESSUES_0);
        result.setMessage(Common.SYSTEM_RES_OK);
        result.setData(map);
        return result;
    }

    @RequestMapping("/roommates/findCurrentMoney")
    public Object findCurrentMoney(HttpServletRequest request) {
        String token = request.getHeader("token");
        TRoommatesMoneySystemCurrentMoney currentMoney = roomMoneyService.findCurrentMoney();
        Result<Object> result = new Result<>();
        result.setCode(Common.SYSTEM_SESSUES_0);
        result.setMessage(Common.SYSTEM_RES_OK);
        result.setData(currentMoney);
        return result;
    }

    @RequestMapping("/roommates/moneyRecordAll")
    public Object findRoomMoneyAll(HttpServletRequest request) {
        String token = request.getHeader("token");
        List<RecordMoneyVo> moneyRecordList = roomMoneyService.selectMoneyRecordAll(token);
        Result<Object> result = new Result<>();
        result.setCode(Common.SYSTEM_SESSUES_0);
        result.setMessage(Common.SYSTEM_RES_OK);
        result.setData(moneyRecordList);
        return result;
    }

    @RequestMapping("/roommates/report")
    public Object report(HttpServletRequest request, RoomMoneyRequestBean roomMoneyRequestBean) {
        double money = roomMoneyRequestBean.getMoney();
        Integer recordType = roomMoneyRequestBean.getRecordType();
        if (EmptyUtil.isEmptyInteger(recordType)) {
            throw new BusiException(ExceptionEnum.SERVICE_PARAM_EMPTY.value());
        }
        log.info(String.format("money值:%s-recordType值:%s", money, recordType));
        log.info(String.format("money值:%s-recordType值:%s", (money <= 0.00), (recordType != 1 && recordType != 2)));
        if (money <= 0.00 || !(recordType == 1 || recordType == 2)) {
            throw new BusiException(ExceptionEnum.SYSTEM_PARAMS_ERROR.value());
        }
        String token = request.getHeader("token");
        roomMoneyService.reportMoney(recordType, money, token);
        Result<Object> result = new Result<>();
        result.setCode(Common.SYSTEM_SESSUES_0);
        result.setMessage(Common.SYSTEM_RES_OK);
        return result;
    }
}
