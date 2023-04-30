package com.example.queue.bridge.configs;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import org.apache.camel.component.jackson.JacksonDataFormat;

import static com.example.queue.bridge.configs.RabbitmqConfiguration.RABBIT_URI;

import static org.apache.camel.LoggingLevel.INFO;;

@Component
public class QueueBridgeRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("wmq:queue:DEV.QUEUE.1").id("ibmq-rabbitmq-route").marshal(new JacksonDataFormat(String.class))
				.to(RABBIT_URI).log(INFO, "Received message in RabbitMQ: ${body}").end();

		/*
		 * from("timer:randomMessage?period=5000")
		 * .transform(simple("Random number ${random(0,100)}")) .to("wmq:DEV.QUEUE.1")
		 * .log(INFO, "Sent message to IBM MQ: ${body}");
		 */

	}

}
