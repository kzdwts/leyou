package com.leyou.order.service;

import com.leyou.order.mapper.OrderStatusMapper;
import com.leyou.order.pojo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 订单业务实现层
 * @author: kangyong
 * @date: 2020/11/27 14:52
 * @version: v1.0
 */
@Service
public class OrderService {

    @Autowired
    private OrderStatusMapper statusMapper;

    /**
     * 更新订单状态(开启事务)
     *
     * @param id
     * @param status
     * @return
     */
    @Transactional
    public Boolean updateStatus(Long id, Integer status) {
        OrderStatus record = new OrderStatus();
        record.setOrderId(id);
        record.setStatus(status);
        // 根据状态判断要修改的时间
        switch (status) {
            case 2:
                record.setPaymentTime(new Date());// 付款
                break;
            case 3:
                record.setConsignTime(new Date());// 发货
                break;
            case 4:
                record.setEndTime(new Date());// 确认收获，订单结束
                break;
            case 5:
                record.setCloseTime(new Date());// 交易失败，订单关闭
                break;
            case 6:
                record.setCommentTime(new Date());// 评价时间
                break;
            default:
                return null;
        }
        int count = this.statusMapper.updateByPrimaryKeySelective(record);
        return count == 1;
    }
}
