package com.jhxaa.yhj.pojo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class TGoodsInfo implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;
    private Long fGoodsId;

    private String fGoodsName;

    private String fGoodsImage;

    private String fGoodsLevelOneCategoryName;

    private BigDecimal fGoodsPrice;

    private Integer fGoodsSalesCount;

    private String fGoodsStoreName;

    private Integer fGoodsPlatformType;

    private Integer fGoodsTotalCouponCount;

    private Integer fGoodsCouponRemainingCount;


    private BigDecimal fGoodsCouponFaceValue;


    private Date fGoodsCouponStartTime;

    private Date fGoodsCouponEndTime;

    private String fGoodsCouponPromoteLink;

    private Date fCreateTime;

    private Date fModifyTime;

    private Integer fEffectiveType;


    public TGoodsInfo() {

    }

    public TGoodsInfo(
            Integer id,
            Long fGoodsId,
            String fGoodsName,
            String fGoodsImage,
            String fGoodsLevelOneCategoryName,
            BigDecimal fGoodsPrice,
            Integer fGoodsSalesCount,
            String fGoodsStoreName,
            Integer fGoodsPlatformType,
            Integer fGoodsTotalCouponCount,
            Integer fGoodsCouponRemainingCount,
            BigDecimal fGoodsCouponFaceValue,
            Date fGoodsCouponStartTime,
            Date fGoodsCouponEndTime,
            String fGoodsCouponPromoteLink,
            Date fCreateTime,
            Date fModifyTime,
            Integer fEffectiveType
    ) {
        this.id = id;
        this.fGoodsId = fGoodsId;
        this.fGoodsName = fGoodsName;
        this.fGoodsImage = fGoodsImage;
        this.fGoodsLevelOneCategoryName = fGoodsLevelOneCategoryName;
        this.fGoodsPrice = fGoodsPrice;
        this.fGoodsSalesCount = fGoodsSalesCount;
        this.fGoodsStoreName = fGoodsStoreName;
        this.fGoodsPlatformType = fGoodsPlatformType;
        this.fGoodsTotalCouponCount = fGoodsTotalCouponCount;
        this.fGoodsCouponRemainingCount = fGoodsCouponRemainingCount;
        this.fGoodsCouponFaceValue = fGoodsCouponFaceValue;
        this.fGoodsCouponStartTime = fGoodsCouponStartTime;
        this.fGoodsCouponEndTime = fGoodsCouponEndTime;
        this.fGoodsCouponPromoteLink = fGoodsCouponPromoteLink;
        this.fCreateTime = fCreateTime;
        this.fModifyTime = fModifyTime;
        this.fEffectiveType = fEffectiveType;
    }

    public boolean equals(Object obj) {
        TGoodsInfo u = (TGoodsInfo) obj;
        Long in1 = fGoodsId;
        Long in2 = u.fGoodsId;
        return in1.equals(in2);
    }

    public int hashCode() {
        Long in = fGoodsId;
        return in.hashCode();
    }

    public BigDecimal getfGoodsCouponFaceValue() {
        return fGoodsCouponFaceValue;
    }

    public void setfGoodsCouponFaceValue(BigDecimal fGoodsCouponFaceValue) {
        this.fGoodsCouponFaceValue = fGoodsCouponFaceValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        this.fGoodsName = fGoodsName == null ? null : fGoodsName.trim();
    }

    public String getfGoodsImage() {
        return fGoodsImage;
    }

    public void setfGoodsImage(String fGoodsImage) {
        this.fGoodsImage = fGoodsImage == null ? null : fGoodsImage.trim();
    }

    public String getfGoodsLevelOneCategoryName() {
        return fGoodsLevelOneCategoryName;
    }

    public void setfGoodsLevelOneCategoryName(String fGoodsLevelOneCategoryName) {
        this.fGoodsLevelOneCategoryName = fGoodsLevelOneCategoryName == null ? null : fGoodsLevelOneCategoryName.trim();
    }

    public BigDecimal getfGoodsPrice() {
        return fGoodsPrice;
    }

    public void setfGoodsPrice(BigDecimal fGoodsPrice) {
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
        this.fGoodsStoreName = fGoodsStoreName == null ? null : fGoodsStoreName.trim();
    }

    public Integer getfGoodsPlatformType() {
        return fGoodsPlatformType;
    }

    public void setfGoodsPlatformType(Integer fGoodsPlatformType) {
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
        this.fGoodsCouponPromoteLink = fGoodsCouponPromoteLink == null ? null : fGoodsCouponPromoteLink.trim();
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Date getfModifyTime() {
        return fModifyTime;
    }

    public void setfModifyTime(Date fModifyTime) {
        this.fModifyTime = fModifyTime;
    }

    public Integer getfEffectiveType() {
        return fEffectiveType;
    }

    public void setfEffectiveType(Integer fEffectiveType) {
        this.fEffectiveType = fEffectiveType;
    }
}