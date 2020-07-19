package com.jhxaa.yhj.manage.easyexcl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.jhxaa.yhj.manage.easyexcl.excelEntity.GoodsInfoExcel;
import com.jhxaa.yhj.utli.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

public class DataListener extends AnalysisEventListener<GoodsInfoExcel> {

    public static List<GoodsInfoExcel> list = null;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public DataListener() {
        init();
    }

    public void init() {
        if (EmptyUtil.isEmpty(list)) {
            list = new ArrayList<>();
        }
    }

    public void clearList() {
        list.clear();
        list = null;
    }

    public List<GoodsInfoExcel> getList() {
        return this.list;
    }


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(GoodsInfoExcel data, AnalysisContext context) {
        // System.out.println("解析到一条数据: "+ JSON.toJSONString(data));
        list.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        //System.out.println(JSON.toJSONString(list));
    }

}
