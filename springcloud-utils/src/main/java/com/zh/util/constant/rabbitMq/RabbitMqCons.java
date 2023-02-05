package com.zh.util.constant.rabbitMq;

import lombok.Data;

/**
 * 消息队列常量
 */
@Data
public class RabbitMqCons {

    public static final String FANOUT_EXCHANGE = "fanout_order_exchange";
    public static final String SMS_QUEUE = "sms.fanout.queue";

}
