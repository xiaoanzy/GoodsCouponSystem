package com.jhxaa.yhj.manage.easyexcl.excelEntity;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

public class GoodsInfoExcel {

    @ExcelProperty("商品id")
    private Long fGoodsId;

    @ExcelProperty("商品名称")
    private String fGoodsName;

    @ExcelProperty("商品主图")
    private String fGoodsImage;

    @ExcelProperty("商品一级类目")
    private String fGoodsLevelOneCategoryName;

    @ExcelProperty("商品价格(单位：元)")
    private double fGoodsPrice;

    @ExcelProperty("商品月销量")
    private Integer fGoodsSalesCount;

    @ExcelProperty("店铺名称")
    private String fGoodsStoreName;

    @ExcelProperty("平台类型")
    private String fGoodsPlatformType;

    @ExcelProperty("优惠券总量")
    private Integer fGoodsTotalCouponCount;

    @ExcelProperty("优惠券剩余量")
    private Integer fGoodsCouponRemainingCount;

    @ExcelProperty("优惠券面额")
    private String fGoodsCouponFaceValue;

    @ExcelProperty("优惠券开始时间")
    private Date fGoodsCouponStartTime;

    @ExcelProperty("优惠券结束时间")
    private Date fGoodsCouponEndTime;

    @ExcelProperty("商品优惠券推广链接")
    private String fGoodsCouponPromoteLink;


    public Long getfGoodsId() {
        return fGoodsId;
    }

    public void setfGoodsId(Long fGoodsId) {
        this.fGoodsId = fGoodsId;
    }

    public String getfGoodsName() {
        return fGoodsName;
    }

    public void setfGoodsName(String fGoodsName) {
        this.fGoodsName = fGoodsName;
    }

    public String getfGoodsImage() {
        return fGoodsImage;
    }

    public void setfGoodsImage(String fGoodsImage) {
        this.fGoodsImage = fGoodsImage;
    }

    public String getfGoodsLevelOneCategoryName() {
        return fGoodsLevelOneCategoryName;
    }

    public void setfGoodsLevelOneCategoryName(String fGoodsLevelOneCategoryName) {
        this.fGoodsLevelOneCategoryName = fGoodsLevelOneCategoryName;
    }

    public String getfGoodsCouponFaceValue() {
        return fGoodsCouponFaceValue;
    }

    public void setfGoodsCouponFaceValue(String fGoodsCouponFaceValue) {
        this.fGoodsCouponFaceValue = fGoodsCouponFaceValue;
    }

    public double getfGoodsPrice() {
        return fGoodsPrice;
    }

    public void setfGoodsPrice(double fGoodsPrice) {
        this.fGoodsPrice = fGoodsPrice;
    }

    public Integer getfGoodsSalesCount() {
        return fGoodsSalesCount;
    }

    public void setfGoodsSalesCount(Integer fGoodsSalesCount) {
        this.fGoodsSalesCount = fGoodsSalesCount;
    }

    public String getfGoodsStoreName() {
        return fGoodsStoreName;
    }

    public void setfGoodsStoreName(String fGoodsStoreName) {
        this.fGoodsStoreName = fGoodsStoreName;
    }

    public String getfGoodsPlatformType() {
        return fGoodsPlatformType;
    }

    public void setfGoodsPlatformType(String fGoodsPlatformType) {
        this.fGoodsPlatformType = fGoodsPlatformType;
    }

    public Integer getfGoodsTotalCouponCount() {
        return fGoodsTotalCouponCount;
    }

    public void setfGoodsTotalCouponCount(Integer fGoodsTotalCouponCount) {
        this.fGoodsTotalCouponCount = fGoodsTotalCouponCount;
    }

    public Integer getfGoodsCouponRemainingCount() {
        return fGoodsCouponRemainingCount;
    }

    public void setfGoodsCouponRemainingCount(Integer fGoodsCouponRemainingCount) {
        this.fGoodsCouponRemainingCount = fGoodsCouponRemainingCount;
    }

    public Date getfGoodsCouponStartTime() {
        return fGoodsCouponStartTime;
    }

    public void setfGoodsCouponStartTime(Date fGoodsCouponStartTime) {
        this.fGoodsCouponStartTime = fGoodsCouponStartTime;
    }

    public Date getfGoodsCouponEndTime() {
        return fGoodsCouponEndTime;
    }

    public void setfGoodsCouponEndTime(Date fGoodsCouponEndTime) {
        this.fGoodsCouponEndTime = fGoodsCouponEndTime;
    }

    public String getfGoodsCouponPromoteLink() {
        return fGoodsCouponPromoteLink;
    }

    public void setfGoodsCouponPromoteLink(String fGoodsCouponPromoteLink) {
        this.fGoodsCouponPromoteLink = fGoodsCouponPromoteLink;
    }


}
