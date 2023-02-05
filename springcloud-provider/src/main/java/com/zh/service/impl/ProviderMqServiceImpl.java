package com.zh.service.impl;

import com.zh.service.ProviderMqService;
import com.zh.util.constant.rabbitMq.RabbitMqCons;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderMqServiceImpl implements ProviderMqService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMqCons.FANOUT_EXCHANGE, "", message);
    }
}
