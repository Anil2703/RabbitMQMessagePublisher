package com.rocky.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${spring.rabbitmq.host}")
    private String rabbitMQHost;

    @Value("${spring.rabbitmq.port}")
    private String rabbitMQPort;

    @Value("${spring.rabbitmq.userName}")
    private String rabbitMQuserName;

    @Value("${spring.rabbitmq.password}")
    private String rabbitMQPassword;


    @Value("${spring.rabbitmq.exchange}")
    private String rabbitMQExchange;

    @Value("${spring.rabbitmq.queue}")
    private String rabbitMQQueue;

    @Value("${spring.rabbitmq.routing-key}")
    private String rabbitMQRoutingKey;

    @Bean
    public Queue queue(){
        return new Queue(rabbitMQQueue, true);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(rabbitMQExchange);
    }

    @Bean
    public Binding binding(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(rabbitMQRoutingKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(rabbitMQHost,Integer.parseInt(rabbitMQPort));
        cachingConnectionFactory.setUsername(rabbitMQuserName);
        cachingConnectionFactory.setPassword(rabbitMQPassword);
        cachingConnectionFactory.setVirtualHost("/");

        return cachingConnectionFactory;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
