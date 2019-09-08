package com.lxf.eye.order.controller;

import com.alibaba.fastjson.JSON;

import com.lxf.eye.common.domain.OrderInfo;
import com.lxf.eye.common.domain.OrderRandomBuilder;
import com.lxf.eye.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Resource
    private OrderService orderService;
    @RequestMapping("/")
    @ResponseBody
    public String sayHello(String name) {
        return "hello world:" + name;
    }
    @RequestMapping("/addOrder")
    @ResponseBody
    public String addOrder() {
        OrderInfo newOrder = OrderRandomBuilder.getNewOrder();
        int i = orderService.addOrder(newOrder);
        if(i>0){
            return "OK:"+newOrder ;
        }else {
            return "Error:" ;
        }
    }
    @RequestMapping("/queryOrders")
    @ResponseBody
    public String queryOrders() {
        List<OrderInfo> orders = orderService.queryOrders();
        return JSON.toJSONString(orders);
    }
}
