package com.mmg.rabbitmq_multi_datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitmqMultiDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqMultiDatasourceApplication.class, args);
    }

}
