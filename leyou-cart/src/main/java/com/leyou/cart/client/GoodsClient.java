package com.leyou.cart.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/12/3 20:08
 * @version: v1.0
 */
@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {
}
