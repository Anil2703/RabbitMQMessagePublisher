package com.rocky.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocky.model.RabbitMQMessage;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class MessageSenderServiceImpl implements MessageSenderService{

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String rabbitMQExchange;

    @Value("${spring.rabbitmq.routing-key}")
    private String rabbitMQRoutingKey;

    public MessageSenderServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(RabbitMQMessage rabbitMQMessage) {

        ObjectMapper mapper = new ObjectMapper();

        String json;

        try{
            json = mapper.writeValueAsString(rabbitMQMessage);
            Message jsonMessage = MessageBuilder.withBody(json.getBytes(StandardCharsets.UTF_8))
                                  .andProperties(MessagePropertiesBuilder.newInstance().setContentType("application/json").build())
                                  .build();

            rabbitTemplate.convertAndSend(rabbitMQExchange, rabbitMQRoutingKey, jsonMessage,
                    new MessagePostProcessor() {
                        @Override
                        public Message postProcessMessage(Message message) throws AmqpException {
                            message.getMessageProperties().setContentType("application/json");
                            message.getMessageProperties().setTimestamp(new Date());
                            return message;
                        }
                    });

        }catch (Exception e){

        }

    }
}
