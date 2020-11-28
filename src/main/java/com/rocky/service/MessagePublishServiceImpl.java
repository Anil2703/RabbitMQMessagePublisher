package com.rocky.service;

import com.rocky.model.RabbitMQMessage;
import com.rocky.rabbitmq.MessageSenderService;
import com.rocky.util.RabbitMQMessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagePublishServiceImpl implements MessagePublishService {

    @Autowired
    RabbitMQMessageValidator rabbitMQMessageValidator;

    @Autowired
    MessageSenderService messageSenderService;


    @Override
    public void publishMessage(RabbitMQMessage rabbitMQMessage) {

        if(rabbitMQMessageValidator.isValidMessage(rabbitMQMessage)){
            messageSenderService.sendMessage(rabbitMQMessage);
        }

    }
}
