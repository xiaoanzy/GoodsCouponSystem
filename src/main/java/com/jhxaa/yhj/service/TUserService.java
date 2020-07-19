package com.jhxaa.yhj.service;


import com.jhxaa.yhj.vo.UserInfoVo;

import java.util.Map;

public interface TUserService {

    Map checkUser(String userName, String password);

    UserInfoVo getUserInfo(String token);


}
