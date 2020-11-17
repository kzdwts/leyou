package com.leyou.user.api;

import com.leyou.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/10/27 18:44
 * @version: v1.0
 */
public interface UserApi {

    /**
     * 根据用户名密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @GetMapping("/query")
    public User queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );

}