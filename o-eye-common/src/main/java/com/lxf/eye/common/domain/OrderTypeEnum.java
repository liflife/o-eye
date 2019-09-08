package com.lxf.eye.common.domain;

public enum OrderTypeEnum {
    SHIPIN("食品"),
    DIANZI("电子"),
    HUAZHUANG("化妆");
    private String desc;
    OrderTypeEnum(String desc){
        this.desc=desc;
    }

    public static String getRandomOne(){
        OrderTypeEnum[] values = OrderTypeEnum.values();
        int random = CommonUtil.getRandom(values.length);
        return values[random].name();
    }
}
