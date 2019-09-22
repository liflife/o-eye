package com.lxf.eye.show.listener;

import com.alibaba.fastjson.JSONObject;
import com.lxf.eye.common.domain.OrderInfo;
import com.lxf.eye.common.domain.OrderStatusEnum;
import com.lxf.eye.common.domain.RabbitMqQueueConfig;
import com.lxf.eye.common.service.EyeRedisTemplate;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@RabbitListener(queues = RabbitMqQueueConfig.STRING)
public class CanalRabbitListener {
    private static final Logger logger = LoggerFactory.getLogger(CanalRabbitListener.class);
    @Autowired
    private EyeRedisTemplate eyeRedisTemplate;

    @RabbitHandler
    public void process(String msgBody, Channel channel, Message message) throws IOException {
        logger.info("Receiver收到:{},收到时间 ", msgBody, new Date());
        try {
            parseMsg(msgBody);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            logger.info("receiver success");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("receiver fail:",e);
        }

    }
    /**
     * 解析消息
     */
    public void parseMsg(String msg) {
        String type = JSONObject.parseObject(msg).getString("type");
        JSONObject dataAfter = JSONObject.parseObject(msg).getJSONObject("dataAfter");
        JSONObject dataBefore = JSONObject.parseObject(msg).getJSONObject("dataBefore");
        OrderInfo orderInfoAfter = JSONObject.parseObject(dataAfter.toJSONString(), OrderInfo.class);
        OrderInfo orderInfoBefore = JSONObject.parseObject(dataBefore.toJSONString(), OrderInfo.class);
        statisticsOrder(type, orderInfoAfter, orderInfoBefore);
    }
    /**
     * 统计订单
     */
    public void statisticsOrder(String eventType, OrderInfo orderInfoAfter, OrderInfo orderInfoBefore) {
        /**
         *
         * 订单量  eye.show.order.total
         * 实际订单量  eye.show.order.total.reality
         * 待支付  eye.show.order.total.status.wait
         * 已支付  eye.show.order.total.status.already
         * 已取消  eye.show.order.total.status.cancel
         * 已入库  eye.show.order.total.status.inhouse
         * 已拣货  eye.show.order.total.status.pick
         * 已出库  eye.show.order.total.status.outhouse
         * 已配送  eye.show.order.total.status.send
         * 已签收  eye.show.order.total.status.sign
         *  INSERT(0, 1),
         UPDATE(1, 2),
         DELETE(2, 3),
         CREATE(3, 4),
         */
        switch (eventType) {
            case "INSERT":
                statisticsOrderINSERT(orderInfoAfter);
                break;
            case "UPDATE":
                statisticsOrderUPDATE(orderInfoAfter, orderInfoBefore);
                break;
            case "DELETE":
                statisticsOrderDELETE(orderInfoAfter);
                break;
            default:
        }

    }
    /**
     * 统计订单插入
     * @param orderInfo
     */
    public void statisticsOrderINSERT(OrderInfo orderInfo) {
        eyeRedisTemplate.incr("eye.show.order.total", 1);
        eyeRedisTemplate.incr("eye.show.order.total.reality", 1);
        eyeRedisTemplate.incr("eye.show.order.total.status.wait", 1);
        eyeRedisTemplate.incr("eye.show.order.amount", orderInfo.getActualAmount().longValue());
        eyeRedisTemplate.incr("eye.show.order.amount.reality", orderInfo.getActualAmount().longValue());
    }

    /**
     * 统计订单更新
     * @param orderInfoAfter
     * @param orderInfoBefore
     */
    public void statisticsOrderUPDATE(OrderInfo orderInfoAfter, OrderInfo orderInfoBefore) {
        String newStatus = orderInfoAfter.getStatus();
        String oldstatus = orderInfoBefore.getStatus();
        if (newStatus.equalsIgnoreCase(oldstatus)) return;
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.getOrderStatusEnum(newStatus);
        switch (orderStatusEnum) {
            case WAIT:
                eyeRedisTemplate.incr("eye.show.order.total", 1);
                break;
            case ALREADY:
                eyeRedisTemplate.incr("eye.show.order.total.status.already", 1);
                break;
            case INHOUSE:
                eyeRedisTemplate.incr("eye.show.order.total.status.inhouse", 1);
                break;
            case PICK:
                eyeRedisTemplate.incr("eye.show.order.total.status.pick", 1);
                break;
            case OUTHOUSE:
                eyeRedisTemplate.incr("eye.show.order.total.status.outhouse", 1);
                break;
            case SEND:
                eyeRedisTemplate.incr("eye.show.order.total.status.send", 1);
                break;
            case SIGN:
                eyeRedisTemplate.incr("eye.show.order.total.status.sign", 1);
                break;
            case CANCEL:
                eyeRedisTemplate.decr("eye.show.order.total.reality", 1);
                eyeRedisTemplate.incr("eye.show.order.amount.reality", orderInfoAfter.getActualAmount().longValue());
                break;
            default:
        }
    }

    public void statisticsOrderDELETE(OrderInfo orderInfo) {

    }


}
