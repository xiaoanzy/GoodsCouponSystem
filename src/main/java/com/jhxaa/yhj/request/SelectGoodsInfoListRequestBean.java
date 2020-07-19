package com.jhxaa.yhj.request;

public class SelectGoodsInfoListRequestBean extends ObjectRequestBean {

    Integer pageIndex;
    Integer pageSize;
    String goodsName;
    //------------------------------------
    Long goodsId;

    //------------------------------------------
    //startPrice
    Double startPrice;
    Double endPrice;
    //------------------------------------------

    String screeningType;


    //------------------------------------------

    /**
     * navType
     * <p>
     * index       主页
     * price9.9    九块九
     * hotGoods    热门商品
     * newGoods    新入库商品
     * <p>
     * 导航类型
     */
    String navType;


//------------------------------------------


    /**
     * categoryType
     * 全部   女装  男装  美妆   鞋品   箱包   配饰   内衣   日用   母婴   其他
     * <p>
     * all                全部
     * femaleClothing     女装
     * maleClothing       男装
     * makeup             美妆
     * footwear           鞋品
     * bags               箱包
     * accessories        配饰
     * underwear          内衣
     * daily              日用
     * bady               母婴
     * other              其他
     */
    String categoryType;

//------------------------------------------

    Integer id;
//------------------------------------------

    String catoryName;


//------------------------------------------


    public String getCatoryName() {
        return catoryName;
    }

    public void setCatoryName(String catoryName) {
        this.catoryName = catoryName;
    }


    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getNavType() {
        return navType;
    }

    public void setNavType(String navType) {
        this.navType = navType;
    }

    public String getScreeningType() {
        if (screeningType == null || "".equals(screeningType)) {
            screeningType = "";
        }
        return screeningType;
    }

    public void setScreeningType(String screeningType) {
        this.screeningType = screeningType;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Double endPrice) {
        this.endPrice = endPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
