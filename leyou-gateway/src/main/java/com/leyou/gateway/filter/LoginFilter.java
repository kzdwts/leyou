package com.leyou.gateway.filter;

import com.leyou.auth.utils.JwtUtils;
import com.leyou.common.utils.CookieUtils;
import com.leyou.gateway.config.FilterProperties;
import com.leyou.gateway.config.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 登录拦截器
 * @author: kangyong
 * @date: 2020/11/18 22:52
 * @version: v1.0
 */
@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class LoginFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private FilterProperties filterProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        // 获取上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取request域
        HttpServletRequest request = context.getRequest();
        // 获取请求路径
        String requestURI = request.getRequestURI();

        // 遍历需要放行的uri
        for (String path : this.filterProperties.getAllowPaths()) {
            // 判断是否需要放行
            if (requestURI.startsWith(path)) {
                return false;
            }
        }

        // true标识 拦截
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取运行上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取request对象
        HttpServletRequest request = context.getRequest();

        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());
        if (StringUtils.isBlank(token)) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        try {
            JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            logger.info("非法访问，未登录，地址：{}", request.getRemoteHost(), e);
        }

        return null;
    }
}
