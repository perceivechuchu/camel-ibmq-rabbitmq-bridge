package com.example.queue.bridge.configs;

import com.ibm.mq.jms.MQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
public class QueueBridgeConfig {

    @Autowired
    CamelContext camelContext;

    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {

                ConnectionFactory connectionFactory = new MQConnectionFactory();
                try {
                    ((MQConnectionFactory) connectionFactory).setQueueManager("QM1");
                    ((MQConnectionFactory) connectionFactory).setTransportType(1);
                    ((MQConnectionFactory) connectionFactory).setPort(1414);
                    ((MQConnectionFactory) connectionFactory).setHostName("localhost");
                    ((MQConnectionFactory) connectionFactory).setChannel("DEV.APP.SVRCONN");
                } catch (JMSException e) {
                    e.printStackTrace();
                }

                UserCredentialsConnectionFactoryAdapter connectionFactoryAdapter=new UserCredentialsConnectionFactoryAdapter();
                connectionFactoryAdapter.setTargetConnectionFactory(connectionFactory);
                connectionFactoryAdapter.setUsername("app");
                connectionFactoryAdapter.setPassword("");

                context.addComponent("wmq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactoryAdapter));

                System.setProperty("hawtio.authenticationEnabled", "false");
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {
                // Do nothing
            }
        };
    }
}
