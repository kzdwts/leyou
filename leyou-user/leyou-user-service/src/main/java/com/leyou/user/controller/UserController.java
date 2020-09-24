package com.leyou.user.controller;

import com.leyou.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/9/24 13:02
 * @version: v1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

}
