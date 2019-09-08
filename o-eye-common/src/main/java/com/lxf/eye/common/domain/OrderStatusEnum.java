package com.lxf.eye.common.domain;

public enum OrderStatusEnum {
    WAIT("待支付"),
    ALREADY("已支付"),
    CANCEL("已取消"),
    INHOUSE("已入库"),
    PICK("已拣货"),
    OUTHOUSE("已出库"),
    SEND("已配送"),
    SIGN("已签收");
    private String desc;
    OrderStatusEnum(String desc){
        this.desc=desc;
    }

    public static OrderStatusEnum getOrderStatusEnum(String name){
        OrderStatusEnum[] values = OrderStatusEnum.values();
        for (OrderStatusEnum value : values) {
           if( value.name().equalsIgnoreCase(name)){
               return value;
           }
        }
        return null;
    }
    public static String getRandomOne(){
        OrderStatusEnum[] values = OrderStatusEnum.values();
        int random = CommonUtil.getRandom(values.length);
        return values[random].name();
    }
    public static OrderStatusEnum getRandomOneEnum(){
        OrderStatusEnum[] values = OrderStatusEnum.values();
        int random = CommonUtil.getRandom(values.length);
        return values[random];
    }
}
