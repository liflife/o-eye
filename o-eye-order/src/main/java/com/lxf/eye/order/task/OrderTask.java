package com.lxf.eye.order.task;

import com.lxf.eye.common.domain.*;
import com.lxf.eye.order.service.OrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderTask {
    private static final Logger logger = LoggerFactory.getLogger(OrderTask.class);
    @Resource
    private OrderService orderService;

    /**
     * 添加订单
     */
    @Scheduled(cron = "0/10 * * * * ? ") // 间隔20秒执行
    public void addOrder() {
        OrderInfo newOrder = OrderRandomBuilder.getNewOrder();
        logger.info("create one order:{}",newOrder);
        orderService.addOrder(newOrder);
    }

    /**
     *更新订单状态
     */
    @Scheduled(cron = "0/5 * * * * ? ") // 间隔5秒执行
    public void updateOrderStatus() {
        OrderStatusEnum status = OrderStatusEnum.getRandomOneEnum();
        List<OrderInfo> orderInfos = orderService.queryOrders(status.name());
        logger.info("query order status:{},total:{}",status.name(),orderInfos.size());
        List<Integer> ids = orderInfos.stream().map(orderInfo -> {
            return orderInfo.getId();
        }).collect(Collectors.toList());
        String newstatus = "";
        switch(status){
            case WAIT :
                int random = CommonUtil.getRandom(3);
                if(random==1){
                    newstatus = OrderStatusEnum.ALREADY.name();
                }else{
                    newstatus = OrderStatusEnum.CANCEL.name();
                }
                break; //可选
            case ALREADY :
                newstatus = OrderStatusEnum.INHOUSE.name();
                break; //可选
            case INHOUSE :
                newstatus = OrderStatusEnum.PICK.name();
                break; //可选
            case PICK :
                newstatus = OrderStatusEnum.OUTHOUSE.name();
                break; //可选
            case OUTHOUSE :
                newstatus = OrderStatusEnum.SEND.name();
                break; //可选
            case SEND :
                newstatus = OrderStatusEnum.SIGN.name();
                break; //可选
            case SIGN :
                newstatus = "";
                break; //可选
            //你可以有任意数量的case语句
            default : //可选
                newstatus = "";
        }
        if(CollectionUtils.isNotEmpty(ids) && StringUtils.isNotEmpty(newstatus)){
            logger.info("update order old status:{},new status:{}",status.name(),newstatus);
            orderService.updateOrders(ids, newstatus);
        }
    }

}
