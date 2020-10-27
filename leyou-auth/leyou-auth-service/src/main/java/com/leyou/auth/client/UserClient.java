package com.leyou.auth.client;

import com.leyou.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/10/27 18:43
 * @version: v1.0
 */
@FeignClient("user-service")
public interface UserClient extends UserApi {

}
