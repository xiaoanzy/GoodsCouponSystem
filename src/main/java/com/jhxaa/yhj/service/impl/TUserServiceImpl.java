package com.jhxaa.yhj.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jhxaa.yhj.exception.BusiException;
import com.jhxaa.yhj.exception.ExceptionEnum;
import com.jhxaa.yhj.mapper.TUserMapper;
import com.jhxaa.yhj.pojo.TUser;
import com.jhxaa.yhj.service.TUserService;
import com.jhxaa.yhj.utli.JwtUtil;
import com.jhxaa.yhj.utli.LogUtil;
import com.jhxaa.yhj.vo.UserInfoVo;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(value = "TUserServiceImpl")
public class TUserServiceImpl implements TUserService {

    private static Logger LOG = LogUtil.getLog(TUserServiceImpl.class);

    @Autowired(required = false)
    TUserMapper tUserMapper;

    @Override
    public Map checkUser(String userName, String password) {
        TUser tUser = null;
        String token = null;
        TUser tUser1 = new TUser();
        tUser1.setfUserName(userName);
        tUser1.setfUserPassword(password);
        tUser = tUserMapper.selectByTUserObject(tUser1);
        if (tUser != null) {
            try {
                token = JwtUtil.create(tUser.getfUserName(), tUser);
            } catch (Exception e) {
                LOG.error(String.format("生成token失败[%s]", e.getMessage()), e);
                throw new BusiException(ExceptionEnum.CREATE_TOKEN_FAIL.value());
            }
        } else if (tUser == null) {
            throw new BusiException(ExceptionEnum.USER_CHECK_FAIL.value());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    @Override
    public UserInfoVo getUserInfo(String token) {
        Claims checkToken = JwtUtil.checkToken(token);
        String checkTokenSubjectJsonStr = checkToken.getSubject();
        TUser tUser = JSONObject.parseObject(checkTokenSubjectJsonStr, TUser.class);
        TUser selectByPrimaryKey = tUserMapper.selectByPrimaryKey(tUser.getId());
        return new UserInfoVo(
                selectByPrimaryKey.getId(),
                selectByPrimaryKey.getfUserUuid(),
                selectByPrimaryKey.getfUserName(),
                selectByPrimaryKey.getfCreateTime(),
                selectByPrimaryKey.getfModifyTime(),
                selectByPrimaryKey.getfManageType()
        );
    }


}
