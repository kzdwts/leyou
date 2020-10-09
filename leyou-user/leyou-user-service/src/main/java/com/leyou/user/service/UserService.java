package com.leyou.user.service;

import com.leyou.common.utils.NumberUtils;
import com.leyou.user.mapper.UserMapper;
import com.leyou.user.pojo.User;
import com.leyou.user.utils.CodecUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 验证码前缀
     */
    private static final String KEY_PREFIX = "user:verify:";

    /**
     * 校验用户数据
     *
     * @param data 要校验的数据
     * @param type 要校验的数据类型：1，用户名；2，手机；
     * @return
     */
    public Boolean checkData(String data, Integer type) {
        User record = new User();
        switch (type) {
            case 1:
                record.setUsername(data);
                break;
            case 2:
                record.setPassword(data);
                break;
            default:
                return null;
        }
        // 如果查询结果为0代表没查到，没查到代表没有重复数据，即校验成功
        return this.userMapper.selectCount(record) == 0;
    }

    /**
     * 发送验证码
     *
     * @param phone
     */
    public void sendVerifyCode(String phone) {
        // 生成验证码
        String code = NumberUtils.generateCode(6);

        // 发送消息到rabbitmq
        Map<String, String> msg = new HashMap<>();
        msg.put("phone", phone);
        msg.put("code", code);
        amqpTemplate.convertAndSend("leyou.sms.exchange", "verifycode.sms", msg);

        // 保存验证码到redis
        redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);
    }

    /**
     * 用户注册
     *
     * @param user
     * @param code
     */
    public Boolean register(User user, String code) {
        // 验证码校验
        String cacheCode = this.redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());
        if (!StringUtils.equals(cacheCode, code)) {
            return false;
        }

        // 生成加密盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        // 生成密码
        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));

        // 补全参数
        user.setId(null);
        user.setCreated(new Date());

        // 入库
        return this.userMapper.insert(user) > 0 ? true : false;
    }

    /**
     * 查询用户信息
     *
     * @param username
     * @param password
     * @return
     */
    public User queryUser(String username, String password) {
        User record = new User();
        record.setUsername(username);
        User user = this.userMapper.selectOne(record);

        // 将客户端传过来的用户密码进行盐加密
        password = CodecUtils.md5Hex(password, user.getSalt());
        if (StringUtils.equals(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
