package com.jhxaa.yhj.vo;

public class CountVo {

    private String title;
    private String icon;
    private Integer count;
    private String color;

    public CountVo() {

    }

    public CountVo(String title, String icon, Integer count, String color) {
        this.title = title;
        this.icon = icon;
        this.count = count;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
