package com.lxf.eye.order.service;

import com.lxf.eye.common.domain.OrderInfo;
import com.lxf.eye.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    public int addOrder(OrderInfo order) {
        return orderMapper.insert(order);
    }
    /**
     * 查询全部订单
     *
     * @return
     */
    public OrderInfo selectByPrimaryKey(String orderNo) {
        Example example = new Example(OrderInfo.class);
        example.createCriteria().andEqualTo("orderNo", orderNo);
        return orderMapper.selectOneByExample(example);
    }
    /**
     * 查询全部订单
     *
     * @return
     */
    public List<OrderInfo> queryOrders() {
        List<OrderInfo> orders = orderMapper.selectAll();
        return orders;
    }

    /**
     * 根据状态查询订单
     *
     * @param orderStatus
     * @return
     */
    public List<OrderInfo> queryOrders(String orderStatus) {
        Example example = new Example(OrderInfo.class);
        example.createCriteria().andEqualTo("status", orderStatus);
        List<OrderInfo> orders = orderMapper.selectByExample(example);
        return orders;
    }

    /**
     * 根据id更新订单状态
     *
     * @param ids
     * @param newstatus
     * @return
     */
    public int updateOrders(List<Integer> ids, String newstatus) {
        Example example = new Example(OrderInfo.class);
        example.createCriteria().andIn("id", ids);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setStatus(newstatus);
        return orderMapper.updateByExampleSelective(orderInfo, example);
    }
}
