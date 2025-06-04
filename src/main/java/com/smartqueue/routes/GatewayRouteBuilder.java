package com.smartqueue.routes;

import org.apache.camel.builder.RouteBuilder;

public class GatewayRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		System.out.println("GatewayRouteBuilder initialized");

		from("direct:submit-job").process(exchange -> {
		}).routeId("gateway-route").log("Gateway received job: ${body}").to("activemq:queue:job.split");
	}
}
