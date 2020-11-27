package com.leyou.order.service.api;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/11/27 15:29
 * @version: v1.0
 */
@FeignClient(value = "leyou-gateway", path = "/api/item")
public interface GoodsService extends GoodsApi {
}
