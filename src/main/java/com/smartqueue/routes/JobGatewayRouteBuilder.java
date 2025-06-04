package com.smartqueue.routes;

import com.smartqueue.model.Job;
import org.apache.camel.builder.RouteBuilder;

public class JobGatewayRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:job-ingest")
            .routeId("job-gateway")
            .log("Received job: ${body}")
            .to("activemq:queue:job.queue");  // Sends to ActiveMQ
    }
}
