package com.leyou.goods.listener;

import com.leyou.goods.service.GoodsHtmlService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 商品DML消息监听
 * @author: kangyong
 * @date: 2020/9/22 23:34
 * @version: v1.0
 */
@Component
public class GoodsListener {

    @Autowired
    private GoodsHtmlService goodsHtmlService;

    /**
     * 监听新增&更新商品数据消息
     *
     * @param id
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "LEYOU.ITEM.INSERT.QUEUE", durable = "true"),
            exchange = @Exchange(value = "LEYOU.ITEM.EXCHANGE", ignoreDeclarationExceptions = "true"),
            key = {"item.insert", "item.update"}
    ))
    public void save(Long id) {
        if (null == id) {
            return;
        }
        this.goodsHtmlService.createHtml(id);
    }

    /**
     * 监听删除商品数据消息
     *
     * @param id 商品id
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "LEYOU.ITEM.DELETE.QUEUE", durable = "true"),
            exchange = @Exchange(value = "LEYOU.ITEM.EXCHANGE", ignoreDeclarationExceptions = "true"),
            key = {"item.delete"}
    ))
    public void delete(Long id) {
        if (null == id) {
            return;
        }
        this.goodsHtmlService.deleteHtml(id);
    }

}
