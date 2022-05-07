package com.mmg.rabbitmq_multi_datasource.example;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: fan
 * @Date: 2022/5/7
 * @Description:
 */
@Component
public class MessageProvider {
    @Resource(name = "defaultRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "0/3 * * * * ?")
    public void sender() {
        rabbitTemplate.convertAndSend(QueueConfig.topicExchange, QueueConfig.topicRouteKey, sdf.format(new Date()));
    }
}
