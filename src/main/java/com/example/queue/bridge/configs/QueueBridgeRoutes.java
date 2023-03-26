package com.example.queue.bridge.configs;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class QueueBridgeRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("wmq:queue:DEV.QUEUE.1")
                .to("log:JMSReceiver?level=INFO");


        from("timer:foo")
                .transform(simple("Random number ${random(0,100)}"))
                .to("wmq:DEV.QUEUE.1");

    }

}
