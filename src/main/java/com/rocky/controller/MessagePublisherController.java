package com.rocky.controller;


import com.rocky.model.RabbitMQMessage;
import com.rocky.service.MessagePublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagePublisherController {

    private static final Logger LOG = LoggerFactory.getLogger(MessagePublisherController.class);

    @Autowired
    private MessagePublishService messagePublishService;

    @PostMapping("/publishMessage")
    public String publishMessageController(@RequestBody RabbitMQMessage rabbitMQMessage) {

        LOG.info("Received RabbitMQ Message in MessagePublisherController with customerID", rabbitMQMessage.getCustomerID());

        try {
            messagePublishService.publishMessage(rabbitMQMessage);

            LOG.info("Message Published to RabbitMQ Successfully");
            return "Message Published to RabbitMQ Successfully";
        } catch (Exception e) {
            LOG.error("Error sending message to RabbitMQ");
            return "Error Publishing Message to RabbitMQ";
        }

    }
}
