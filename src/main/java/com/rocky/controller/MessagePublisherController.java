package com.rocky.controller;


import com.rocky.model.RabbitMQMessage;
import com.rocky.service.MessagePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagePublisherController {

    @Autowired
    private MessagePublishService messagePublishService;

    public String publishMessageController(@RequestBody RabbitMQMessage rabbitMQMessage) {

        try {
            messagePublishService.publishMessage(rabbitMQMessage);
            return "Message Published to RabbitMQ Successfully";
        } catch (Exception e) {
            return "Error Publishing Message to RabbitMQ";
        }

    }
}
