package com.leyou.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: cors过滤类
 * @author: kangyong
 * @date: 2020/7/19 23:28
 * @version: v1.0
 */
@Configuration
public class LeyouCorsConfiguration {

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();
        // 允许的域
        config.addAllowedOrigin("http://manage.leyou.com");
        config.addAllowedOrigin("http://www.leyou.com");
        //
        config.setAllowCredentials(true);
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("GET");
        config.addAllowedHeader("*");

        // 添加映射路径
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configurationSource);
    }
}
