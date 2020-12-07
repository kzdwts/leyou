package com.leyou.cart.service;

import com.leyou.auth.entity.UserInfo;
import com.leyou.cart.client.GoodsClient;
import com.leyou.cart.interceptor.LoginInterceptor;
import com.leyou.cart.pojo.Cart;
import com.leyou.common.utils.JsonUtils;
import com.leyou.item.pojo.Sku;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/12/3 19:29
 * @version: v1.0
 */
@Service
public class CartService {

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:cart:";

    /**
     * 添加商品到购物车
     *
     * @param cart
     */
    public void addCart(Cart cart) {
        // 获取用户信息
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        // 查询购物车记录
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());
        // 新增的商品数量
        Integer num = cart.getNum();
        String key = cart.getSkuId().toString();

        // 判断当前商品是否在购物车中
        if (hashOperations.hasKey(key)) {
            // 在，更新数量
            String cartJson = hashOperations.get(key).toString();
            cart = JsonUtils.parse(cartJson, Cart.class);
            cart.setNum(cart.getNum() + num);
        } else {
            // 不在，存入购物车
            // 查询商品详情
            Sku sku = this.goodsClient.querySkuBySkuId(cart.getSkuId());
            cart.setOwnSpec(sku.getOwnSpec());
            cart.setPrice(sku.getPrice());
            cart.setTitle(sku.getTitle());
            cart.setUserId(userInfo.getId());
            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
        }
        hashOperations.put(key, JsonUtils.serialize(cart));
    }

    /**
     * 查询购物车数据
     *
     * @return 购物车数据
     */
    public List<Cart> queryCarts() {
        // 获取用户信息
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        // 判断是否有当前用户的购物车数据
        if (!redisTemplate.hasKey(KEY_PREFIX + userInfo.getId())) {
            return null;
        }

        // 获取购物车详情数据
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());
        List<Object> cartsJsonList = hashOperations.values();

        // 判断购物车详情数据是否为空
        if (CollectionUtils.isEmpty(cartsJsonList)) {
            return null;
        }

        // 返回购物车数据
        return cartsJsonList.stream().map(cartJson -> JsonUtils.parse(cartJson.toString(), Cart.class)).collect(Collectors.toList());
    }

    /**
     * 更新购物车商品数量
     *
     * @param cart
     */
    public void updateNum(Cart cart) {
        // 获取用户信息
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        // 判断是否有当前用户的购物车数据
        if (!redisTemplate.hasKey(KEY_PREFIX + userInfo.getId())) {
            return;
        }

        // 获取购物车数据
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());
        String cartJson = hashOperations.get(cart.getSkuId().toString()).toString();

        Integer num = cart.getNum();
        cart = JsonUtils.parse(cartJson, Cart.class);
        cart.setNum(num);

        // 写入缓存
        hashOperations.put(cart.getSkuId().toString(), JsonUtils.serialize(cart));
    }

    /**
     * 删除购物车商品
     *
     * @param skuId
     */
    public void deleteCart(String skuId) {
        // 获取用户信息
        UserInfo userInfo = LoginInterceptor.getUserInfo();

        // 判断是否有当前用户的购物车数据
        if (!redisTemplate.hasKey(KEY_PREFIX + userInfo.getId())) {
            return;
        }

        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(KEY_PREFIX + userInfo.getId());
        hashOperations.delete(skuId);
    }

}
