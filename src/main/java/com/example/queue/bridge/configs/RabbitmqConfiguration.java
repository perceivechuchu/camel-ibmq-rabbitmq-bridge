package com.example.queue.bridge.configs;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {

    @Autowired
    CamelContext camelContext;
    
    public static final String RABBIT_URI = "rabbitmq:amq.direct?queue=javainuse.queue&routingKey=javainuse.routing&autoDelete=false";

    @Bean
    public com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory() {
    	com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        factory.setHost("camel-rabbitmq-messaging");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory;
    }
}
