package com.lxf.eye.order.api;

import com.lxf.eye.common.domain.OrderInfo;
import com.lxf.eye.common.domain.api.OrderServiceDubbo;
import com.lxf.eye.common.service.EyeRedisTemplate;
import com.lxf.eye.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceDubboImpl implements OrderServiceDubbo {

    private Logger logger = LoggerFactory.getLogger(OrderServiceDubboImpl.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private EyeRedisTemplate eyeRedisTemplate;

    /**
     * 增加调用方基本信息
     * @return
     */
    public OrderInfo selectByPrimaryKey(String orderNo) {
        logger.info(orderNo);
        eyeRedisTemplate.incr("selectByPrimaryKey",1l);
        return orderService.selectByPrimaryKey(orderNo);
    }
}
