package com.jhxaa.yhj.service;

import java.util.Map;

public interface AdminService {

    Map<String, Integer> selectCount();

    void clearRepeatGoods();
}
