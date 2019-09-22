package com.lxf.eye.show.service;

import com.alibaba.fastjson.JSONObject;
import com.lxf.eye.common.service.EyeRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
    @Autowired
    private EyeRedisTemplate eyeRedisTemplate;
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
        Object orderTotal = eyeRedisTemplate.get("eye.show.order.total");
        Object orderTotalReality =eyeRedisTemplate.get("eye.show.order.total.reality");
        Object orderStatusTotal =eyeRedisTemplate.get("eye.show.order.total.status.wait");
        Object orderStatusAlready = eyeRedisTemplate.get("eye.show.order.total.status.already");
        Object orderStatusCancel = eyeRedisTemplate.get("eye.show.order.total.status.cancel");
        Object orderTotalStatusInhouse =eyeRedisTemplate.get("eye.show.order.total.status.inhouse");
        Object orderStatusPick =eyeRedisTemplate.get("eye.show.order.total.status.pick");
        Object orderStatusOuthouse =eyeRedisTemplate.get("eye.show.order.total.status.outhouse");
        Object orderStatusSend =eyeRedisTemplate.get("eye.show.order.total.status.send");
        Object orderStatusSign = eyeRedisTemplate.get("eye.show.order.total.status.sign");

        String orderAmount = (String)eyeRedisTemplate.get("eye.show.order.amount");
        String orderAmountReality = (String)eyeRedisTemplate.get("eye.show.order.amount.reality");
        json.put("orderTotal",tranInter(orderTotal));
        json.put("orderTotalReality",tranInter(orderTotalReality));
        json.put("orderStatusTotal",tranInter(orderStatusTotal));
        json.put("orderStatusAlready",tranInter(orderStatusAlready));
        json.put("orderStatusCancel",tranInter(orderStatusCancel));
        json.put("orderTotalStatusInhouse",tranInter(orderTotalStatusInhouse));
        json.put("orderStatusPick",tranInter(orderStatusPick));
        json.put("orderStatusOuthouse",tranInter(orderStatusOuthouse));
        json.put("orderStatusSend",tranInter(orderStatusSend));
        json.put("orderStatusSign",tranInter(orderStatusSign));
        json.put("orderAmount",tranInter(orderAmount));
        json.put("orderAmountReality",tranInter(orderAmountReality));
        return json;
    }
    private Integer tranInter(Object object){
        if(object==null){
            return 0;
        }else {
            return Integer.parseInt(object.toString());
        }
    }

}
