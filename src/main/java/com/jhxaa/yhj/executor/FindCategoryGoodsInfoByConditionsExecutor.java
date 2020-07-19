package com.jhxaa.yhj.executor;

import com.jhxaa.yhj.mapper.TGoodsInfoMapper;
import com.jhxaa.yhj.pojo.TGoodsInfo;

import java.math.BigDecimal;
import java.util.List;

public abstract class FindCategoryGoodsInfoByConditionsExecutor {


    public abstract List<TGoodsInfo> findCategoryGoodsInfoByConditions(TGoodsInfoMapper tGoodsInfoMapper, FindCategoryGoodsInfoByConditions findCategoryGoodsInfoByConditions);


    public static class FindCategoryGoodsInfoByConditions {
        String goodsName;
        Integer pageIndex;
        Integer pageSize;
        BigDecimal startPrice;
        BigDecimal endPrice;
        String screeningType;
        String navType;


        public FindCategoryGoodsInfoByConditions(String goodsName, Integer pageIndex, Integer pageSize, BigDecimal startPrice, BigDecimal endPrice, String screeningType, String navType) {

            this.goodsName = goodsName;
            this.pageIndex = pageIndex;
            this.pageSize = pageSize;
            this.startPrice = startPrice;
            this.endPrice = endPrice;
            this.screeningType = screeningType;
            this.navType = navType;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public Integer getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(Integer pageIndex) {
            this.pageIndex = pageIndex;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public BigDecimal getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(BigDecimal startPrice) {
            this.startPrice = startPrice;
        }

        public BigDecimal getEndPrice() {
            return endPrice;
        }

        public void setEndPrice(BigDecimal endPrice) {
            this.endPrice = endPrice;
        }

        public String getScreeningType() {
            return screeningType;
        }

        public void setScreeningType(String screeningType) {
            this.screeningType = screeningType;
        }

        public String getNavType() {
            return navType;
        }

        public void setNavType(String navType) {
            this.navType = navType;
        }
    }
}
