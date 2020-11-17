package com.leyou.auth.controller;

import com.leyou.auth.config.JwtProperties;
import com.leyou.auth.service.AuthService;
import com.leyou.common.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 鉴权 控制层
 * @author: kangyong
 * @date: 2020/10/21 23:00
 * @version: v1.0
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProp;

    /**
     * 鉴权 获取token
     *
     * @param username 用户名
     * @param password 密码
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/accredit")
    public ResponseEntity<Void> authentication(@RequestParam("username") String username,
                                               @RequestParam("password") String password,
                                               HttpServletRequest request,
                                               HttpServletResponse response) {
        // 登录校验
        String token = this.authService.accredit(username, password);
        if (StringUtils.isBlank(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 将token写入cookie，并指定httpOnly为true，防止通过js获取和修改
        CookieUtils.setCookie(request, response, jwtProp.getCookieName(), token, jwtProp.getCookieMaxAge(), null, true);
        return ResponseEntity.ok().build();
    }

}
