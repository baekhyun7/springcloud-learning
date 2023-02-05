package com.zh.service.impl;

import com.zh.service.ConsumerMqService;
import com.zh.util.constant.rabbitMq.RabbitMqCons;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerMqServiceImpl implements ConsumerMqService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerMqServiceImpl.class);

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(RabbitMqCons.SMS_QUEUE))
    public void process(String messageStr) {
        logger.info("mq接受到的消息： {}", messageStr);
    }
}
