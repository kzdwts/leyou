package com.leyou.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/11/27 13:59
 * @version: v1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LeyouOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeyouOrderApplication.class, args);
    }

}
