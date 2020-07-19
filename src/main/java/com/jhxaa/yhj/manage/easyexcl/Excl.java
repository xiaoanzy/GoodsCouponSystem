package com.jhxaa.yhj.manage.easyexcl;

import com.alibaba.excel.EasyExcel;
import com.jhxaa.yhj.manage.easyexcl.excelEntity.GoodsInfoExcel;

public class Excl {

    public void readExcl(String fileNamePath) {
        //String fileName = PropertiesUtil.getString("file.path");
        // 这里默认读取第一个sheet
        DataListener dataListener = new DataListener();

        EasyExcel.read(fileNamePath, GoodsInfoExcel.class, dataListener).sheet().doRead();
    }
}
