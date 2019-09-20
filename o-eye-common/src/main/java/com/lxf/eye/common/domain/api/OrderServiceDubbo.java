package com.lxf.eye.common.domain.api;

import com.lxf.eye.common.domain.OrderInfo;

public interface OrderServiceDubbo {
    /**
     * 增加调用方基本信息
     * @return
     */
    public OrderInfo selectByPrimaryKey(String orderNo);
}
