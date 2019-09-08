package com.lxf.eye.common.domain;

public enum OrderSourceNoEnum {
    PC("电脑"),
    APPANDROID("安卓"),
    APPAPPLE("苹果"),
    WEBSITE("小程序");
    private String desc;
    OrderSourceNoEnum(String desc){
        this.desc=desc;
    }
    public static String getRandomOne(){
        OrderSourceNoEnum[] values = OrderSourceNoEnum.values();
        int random = CommonUtil.getRandom(values.length);
        return values[random].name();
    }
}
