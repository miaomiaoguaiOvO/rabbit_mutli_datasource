package com.mmg.rabbitmq_multi_datasource.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Auther: fan
 * @Date: 2022/5/7
 * @Description:
 */
@Configuration
public class RabbitConfig {
    /*==================================默认的rabbitmq配置========================================*/
    @Bean(name = "defaultConnectionFactory")
    @Primary
    public CachingConnectionFactory defaultConnectionFactory(
            @Value("${spring.rabbitmq.default.host}") String host,
            @Value("${spring.rabbitmq.default.port}") int port,
            @Value("${spring.rabbitmq.default.username}") String username,
            @Value("${spring.rabbitmq.default.password}") String password,
            @Value("${spring.rabbitmq.default.connection-timeout}") int connectionTimeout,
            @Value("${spring.rabbitmq.default.virtual-host}") String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setConnectionTimeout(connectionTimeout);
        return connectionFactory;
    }

    @Bean(name = "defaultContainerFactory")
    @Primary
    public SimpleRabbitListenerContainerFactory defaultContainerFactory(@Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory,
                                                                        @Value("${spring.rabbitmq.default.listener.simple.acknowledge-mode}") String acknowledge) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.valueOf(acknowledge.toUpperCase()));
        return factory;
    }

    @Bean(name = "defaultRabbitAdmin")
    @Primary
    public RabbitAdmin defaultRabbitAdmin(@Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean(name = "defaultRabbitTemplate")
    @Primary
    public RabbitTemplate defaultRabbitTemplate(@Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(false);
        return rabbitTemplate;
    }


    /*==================================其他rabbitmq配置========================================*/
    @Bean(name = "flowerConnectionFactory")
    public CachingConnectionFactory flowerConnectionFactory(
            @Value("${spring.rabbitmq.flower.host}") String host,
            @Value("${spring.rabbitmq.flower.port}") int port,
            @Value("${spring.rabbitmq.flower.username}") String username,
            @Value("${spring.rabbitmq.flower.password}") String password,
            @Value("${spring.rabbitmq.flower.connection-timeout}") int connectionTimeout,
            @Value("${spring.rabbitmq.flower.virtual-host}") String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setConnectionTimeout(connectionTimeout);
        return connectionFactory;
    }

    @Bean(name = "flowerContainerFactory")
    public SimpleRabbitListenerContainerFactory flowerContainerFactory(@Qualifier("flowerConnectionFactory") ConnectionFactory connectionFactory,
                                                                       @Value("${spring.rabbitmq.flower.listener.simple.acknowledge-mode}") String acknowledge) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.valueOf(acknowledge.toUpperCase()));
        return factory;
    }

    @Bean(name = "flowerRabbitAdmin")
    public RabbitAdmin flowerRabbitAdmin(@Qualifier("flowerConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean(name = "flowerRabbitTemplate")
    public RabbitTemplate flowerRabbitTemplate(@Qualifier("flowerConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(false);
        return rabbitTemplate;
    }
}
