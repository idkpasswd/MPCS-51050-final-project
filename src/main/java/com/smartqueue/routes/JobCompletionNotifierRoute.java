package com.smartqueue.routes;

import com.smartqueue.model.JobResult;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class JobCompletionNotifierRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		System.out.println("- [DEBUG] JobCompletionNotifierRoute initialized");

		from("activemq:queue:result.queue").routeId("job-completion-notifier-route")
	    .log("Job completed: ${body}")
	    .unmarshal().json(JsonLibrary.Jackson, JobResult.class)
	    .process(exchange -> {
	        Object body = exchange.getIn().getBody();
	        System.out.println("- Recieve queue:result.queue: body class = " + (body == null ? "null" : body.getClass().getName()));
	    })
	    .to("bean:jobStatusPublisher?method=publish")
	    .process(exchange -> {
					System.out.println("- Publish done");
				});

	}
}
