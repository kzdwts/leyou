package com.leyou.search.client;

import com.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/8/25 23:20
 * @version: v1.0
 */
@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {
}
