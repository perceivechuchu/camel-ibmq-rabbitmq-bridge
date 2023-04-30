package com.example.camel.queue.bridge.configs;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitmqConfiguration {

	@Autowired
	CamelContext camelContext;

	@Value("${rabbitmq.host}")
	private String rabbitmqHost;

	@Value("${rabbitmq.port}")
	private int rabbitmqPort;

	@Value("${rabbitmq.username}")
	private String rabbitmqUsername;

	@Value("${rabbitmq.password}")
	private String rabbitmqPassword;

	public static final String RABBIT_URI = "rabbitmq:amq.direct?queue=bridge-test.queue&routingKey=bridge-test.routing.queue&autoDelete=false";

	@Bean
	public ConnectionFactory rabbitConnectionFactory() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(rabbitmqHost);
		factory.setPort(rabbitmqPort);
		factory.setUsername(rabbitmqUsername);
		factory.setPassword(rabbitmqPassword);
		return factory;
	}
}
