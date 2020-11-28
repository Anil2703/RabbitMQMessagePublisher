package com.rocky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQMessagePublisherApplication {

    public static void main(String[] args) {

        SpringApplication.run(RabbitMQMessagePublisherApplication.class,args);
    }

}
