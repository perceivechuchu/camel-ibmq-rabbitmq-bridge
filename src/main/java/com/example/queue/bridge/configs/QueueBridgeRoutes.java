package com.example.queue.bridge.configs;

import static com.example.queue.bridge.configs.RabbitmqConfiguration.RABBIT_URI;
import static org.apache.camel.LoggingLevel.INFO;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;;

@Component
public class QueueBridgeRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("wmq:queue:DEV.QUEUE.1").id("ibmq-rabbitmq-route").marshal(new JacksonDataFormat(String.class))
				.to(RABBIT_URI).log(INFO, "RabbitMQ - received message => ${body}").end();
	}
}
