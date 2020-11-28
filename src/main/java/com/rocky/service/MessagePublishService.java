package com.rocky.service;

import com.rocky.model.RabbitMQMessage;

public interface MessagePublishService {

    public void publishMessage(RabbitMQMessage rabbitMQMessage);
}
