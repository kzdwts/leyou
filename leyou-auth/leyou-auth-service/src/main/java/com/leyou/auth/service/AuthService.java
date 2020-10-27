package com.leyou.auth.service;

import com.leyou.auth.client.UserClient;
import com.leyou.auth.config.JwtProperties;
import com.leyou.auth.entity.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/10/21 23:00
 * @version: v1.0
 */
@Service
public class AuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties jwtProp;

    /**
     * 鉴权 验证用户名密码，返回token
     *
     * @param username
     * @param password
     * @return
     */
    public String authentication(String username, String password) {
        try {
            // 查询用户
            User user = userClient.queryUser(username, password);
            if (null == user) {
                return null;
            }

            // 如果有查询结果，生成token
            String token = JwtUtils.generateToken(new UserInfo(user.getId(), user.getUsername()), jwtProp.getPrivateKey(), jwtProp.getExpire());
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
