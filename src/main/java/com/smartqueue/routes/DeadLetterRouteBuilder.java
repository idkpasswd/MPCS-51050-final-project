package com.smartqueue.routes;

import org.apache.camel.builder.RouteBuilder;

public class DeadLetterRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// Route to consume from error.queue (dead letter)
		from("activemq:queue:error.queue").routeId("dead-letter-route").log("Dead letter message received: ${body}")
				.to("file:data/dead-letters?fileName=dlq-${date:now:yyyyMMddHHmmssSSS}.txt")
				.log("Dead letter message written to file.");
	}
}
