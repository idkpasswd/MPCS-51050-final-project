package com.smartqueue.input;

import org.apache.camel.builder.RouteBuilder;

public class JobSubmissionController extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		restConfiguration().component("undertow").port(8084);

		rest("/jobs").post("/submit").consumes("application/json").to("direct:submit-job");

		from("direct:gateway-input").process(exchange -> {
			System.out.println("Received job via REST: " + exchange.getIn().getBody(String.class));
		}).to("direct:gateway");

	}
}
