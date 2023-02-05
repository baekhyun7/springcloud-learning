package com.zh.config;

import com.zh.util.constant.rabbitMq.RabbitMqCons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 */
@Slf4j
@Configuration
public class RabbitMqConfiguration {
    //1.声明注册fanout模式的交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMqCons.FANOUT_EXCHANGE, true, false);
    }

    //2.声明队列
    @Bean
    public Queue smsQueue() {
        return new Queue(RabbitMqCons.SMS_QUEUE, true);
    }

    //3.完成绑定关系   队列和交换机  的绑定
    @Bean
    public Binding smsBingding() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("消息发送交换机{}:correlationData({}),ack({}),cause({})", ack ? "成功" : "失败", correlationData, ack, cause);
            if (!ack) {
                // 发送失败进行重试机制

            }
        });
        rabbitTemplate.setReturnsCallback(message -> {
            log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", message.getExchange()
                    , message.getRoutingKey(), message.getReplyCode(), message.getReplyText(), message.getMessage());
            // 路由失败，进行重试机制

        });
        return rabbitTemplate;
    }
}
