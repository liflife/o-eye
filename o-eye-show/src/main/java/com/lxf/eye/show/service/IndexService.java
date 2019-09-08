package com.lxf.eye.show.service;

import com.alibaba.fastjson.JSONObject;
import com.lxf.eye.show.common.RedisUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
    @Autowired
    private RedisUtil redisUtil;
    public JSONObject queryShowData() {
        JSONObject json=new JSONObject();
        /**
         * * 订单量  eye.show.order.total
         * 实际订单量  eye.show.order.total.reality
         * 待支付  eye.show.order.total.status.wait
         * 已支付  eye.show.order.total.status.already
         * 已取消  eye.show.order.total.status.cancel
         * 已入库  eye.show.order.total.status.inhouse
         * 已拣货  eye.show.order.total.status.pick
         * 已出库  eye.show.order.total.status.outhouse
         * 已配送  eye.show.order.total.status.send
         * 已签收  eye.show.order.total.status.sign

         * 订单金额  eye.show.order.amount
         * 实际订单金额  eye.show.order.amount.reality
         */
        String orderTotal = (String)redisUtil.get("eye.show.order.total");
        String orderTotalReality = (String)redisUtil.get("eye.show.order.total.reality");
        String orderStatusTotal = (String)redisUtil.get("eye.show.order.total.status.wait");
        String orderStatusAlready = (String)redisUtil.get("eye.show.order.total.status.already");
        String orderStatusCancel = (String)redisUtil.get("eye.show.order.total.status.cancel");
        String orderTotalStatusInhouse = (String)redisUtil.get("eye.show.order.total.status.inhouse");
        String orderStatusPick = (String)redisUtil.get("eye.show.order.total.status.pick");
        String orderStatusOuthouse = (String)redisUtil.get("eye.show.order.total.status.outhouse");
        String orderStatusSend = (String)redisUtil.get("eye.show.order.total.status.send");
        String orderStatusSign = (String)redisUtil.get("eye.show.order.total.status.sign");

        String orderAmount = (String)redisUtil.get("eye.show.order.amount");
        String orderAmountReality = (String)redisUtil.get("eye.show.order.amount.reality");
        json.put("orderTotal",orderTotal);
        json.put("orderTotalReality",orderTotalReality);
        json.put("orderStatusTotal",orderStatusTotal);
        json.put("orderStatusAlready",orderStatusAlready);
        json.put("orderStatusCancel",orderStatusCancel);
        json.put("orderTotalStatusInhouse",orderTotalStatusInhouse);
        json.put("orderStatusPick",orderStatusPick);
        json.put("orderStatusOuthouse",orderStatusOuthouse);
        json.put("orderStatusSend",orderStatusSend);
        json.put("orderStatusSign",orderStatusSign);
        json.put("orderAmount",orderAmount);
        json.put("orderAmountReality",orderAmountReality);
        return json;
    }

}
