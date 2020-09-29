package com.example.blog.search.config;

import com.example.blog.common.constants.RabbitMqConstants;
import com.example.blog.common.util.RabbitMqUtils;
import com.rabbitmq.client.ConnectionFactory;
import org.elasticsearch.client.ElasticsearchClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
@ConditionalOnClass(ElasticsearchClient.class)
public class InitialConfig {

    @Resource
    private RabbitMqUtils rabbitMqUtils;

    @PostConstruct
    public void initEsIndex() {
        rabbitMqUtils.send(RabbitMqConstants.REFRESH_ES_INDEX_QUEUE, "blog-search init index");
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setAutomaticRecoveryEnabled(false);
    }
}
