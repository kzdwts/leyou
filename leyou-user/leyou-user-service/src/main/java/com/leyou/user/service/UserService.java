package com.leyou.user.service;

import com.leyou.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/9/24 13:02
 * @version: v1.0
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

}
