package com.leyou.goods.client;

import com.leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/8/25 23:19
 * @version: v1.0
 */
@FeignClient("item-service")
public interface BrandClient extends BrandApi {


}
