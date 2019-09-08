package com.lxf.eye.common.domain;

public enum OrderCategoryEnum {
    ZIYING("自营"),
    SANFANG("三方");
    private String desc;
    OrderCategoryEnum(String desc){
        this.desc=desc;
    }

    public static String getRandomOne(){
        OrderCategoryEnum[] values = OrderCategoryEnum.values();
        int random = CommonUtil.getRandom(values.length);
        return values[random].name();
    }
}
