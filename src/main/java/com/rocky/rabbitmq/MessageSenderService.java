package com.rocky.rabbitmq;

import com.rocky.model.RabbitMQMessage;

public interface MessageSenderService {

    public void  sendMessage(RabbitMQMessage rabbitMQMessage);
}
