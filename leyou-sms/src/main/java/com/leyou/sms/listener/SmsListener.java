package com.leyou.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.leyou.sms.property.SmsProperties;
import com.leyou.sms.util.SmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description:
 * @author: kangyong
 * @date: 2020/9/25 13:12
 * @version: v1.0
 */
@Component
public class SmsListener {

    @Autowired
    private SmsProperties smsProperties;

    @Autowired
    private SmsUtils smsUtils;

    /**
     * 监听短信发送消息
     *
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "leyou.sms.queue", durable = "true"),
            exchange = @Exchange(value = "leyou.sms.exchange", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {"sms.verify.code"}
    ))
    public void listenSms(Map<String, String> msg) throws ClientException {
        // 非空校验
        if (null == msg || msg.size() <= 0) {
            return;
        }

        // 获取参数
        String phone = msg.get("phone");
        String code = msg.get("code");
       if(StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
           return;
       }

       // 发送短信
        smsUtils.sendSms(phone, code, smsProperties.getSignName(), smsProperties.getVerifyCodeTemplate());
    }
}
