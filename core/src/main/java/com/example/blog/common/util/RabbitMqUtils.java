package com.example.blog.common.util;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqUtils {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String queueName, Object object) {
        this.rabbitTemplate.convertAndSend(queueName, object);
    }
}
