package com.leyou.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/6/22 0:41
 * @version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class LeyouGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeyouGatewayApplication.class, args);
    }
}
