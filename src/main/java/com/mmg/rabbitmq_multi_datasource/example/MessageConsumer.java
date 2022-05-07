package com.mmg.rabbitmq_multi_datasource.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: fan
 * @Date: 2022/5/7
 * @Description:
 */
@Slf4j
@Component
public class MessageConsumer {

    @RabbitListener(queues = QueueConfig.topicQueue, containerFactory = "defaultContainerFactory")
    public void listener(String message) {
        log.info("收到消息：{}", message);
    }
}
