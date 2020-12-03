package com.leyou.cart.pojo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 购物车对象
 * @author: kangyong
 * @date: 2020/12/3 19:10
 * @version: v1.0
 */
@Data
public class Cart {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String image;

    /**
     * 加入购物车时价格
     */
    private Long price;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 商品规格参数
     */
    private String ownSpec;

}
