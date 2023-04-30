package com.example.queue.bridge.configs;

import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;

import com.ibm.mq.jms.MQConnectionFactory;

@Configuration
public class IbmqConfiguration {

    @Autowired
    CamelContext camelContext;
    
    @Value("${ibmq.host}")
	private String ibmqHost;
    
    @Value("${ibmq.port}")
	private int ibmqPort;
    
    @Value("${ibmq.queue-manager}")
	private String ibmqManager;
    
    @Value("${ibmq.queue-connection-type}")
	private int ibmqConnectionType;
    
    @Value("${ibmq.channel}")
	private String ibmqChannel;
    
    static Logger logger = Logger.getLogger(IbmqConfiguration.class.getName());
    
    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {

                ConnectionFactory connectionFactory = new MQConnectionFactory();
                try {
                    ((MQConnectionFactory) connectionFactory).setQueueManager(ibmqManager);
                    ((MQConnectionFactory) connectionFactory).setTransportType(ibmqConnectionType);
                    ((MQConnectionFactory) connectionFactory).setPort(ibmqPort);
                    ((MQConnectionFactory) connectionFactory).setHostName(ibmqHost);
                    ((MQConnectionFactory) connectionFactory).setChannel(ibmqChannel);
                } catch (JMSException e) {
                	logger.severe("Failed to initialize IBM MQ connection.");
                }

                UserCredentialsConnectionFactoryAdapter connectionFactoryAdapter=new UserCredentialsConnectionFactoryAdapter();
                connectionFactoryAdapter.setTargetConnectionFactory(connectionFactory);
                connectionFactoryAdapter.setUsername("app");
                connectionFactoryAdapter.setPassword("");

                context.addComponent("wmq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactoryAdapter));
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {
            	logger.severe("Application successfully started.");
            }
        };
    }
}
