package com.mmg.rabbitmq_multi_datasource.example;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Auther: fan
 * @Date: 2022/5/7
 * @Description:
 */
@Component
public class QueueConfig {

    @Resource(name = "defaultRabbitAdmin")
    private RabbitAdmin defaultRabbitAdmin;

    private static final String topicQueue = "topic.default.queue";
    private static final String topicExchange = "topic.default.exchange";
    private static final String topicRouteKey = "topic.default.route";

    @PostConstruct
    public void topicQueue() {
        Queue queue = new Queue(topicQueue);
        defaultRabbitAdmin.declareQueue(queue);
        TopicExchange exchange = new TopicExchange(topicExchange);
        defaultRabbitAdmin.declareExchange(exchange);
        defaultRabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(topicRouteKey));
    }
}
