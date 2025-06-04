package com.smartqueue.routes;

import com.smartqueue.factory.JobFactory;
import com.smartqueue.model.Job;
import com.smartqueue.model.JobResult;
import com.smartqueue.strategy.JobProcessorStrategy;
import com.smartqueue.strategy.StrategySelector;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class JobRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
        System.out.println("- [DEBUG] JobRouteBuilder initialized");

		// Error handling: move failed messages to dead letter queue
		errorHandler(deadLetterChannel("activemq:queue:error.queue").maximumRedeliveries(3).redeliveryDelay(1000));

		from("activemq:queue:job.queue").routeId("job-processing-route").log("Received job message: ${body}")

				// Step 1: Filter invalid jobs (Message Filter)
				.filter().method(JobValidationFilter.class, "isValid").log("Job is valid. Proceeding...")

				// Step 2: Translate JSON to Job (Message Translator)
				.unmarshal().json(Job.class) // Assumes JSON message if needed
				.process(new JobStrategyProcessor())

				// Step 3: Send result to result queue
				.to("activemq:queue:result.queue")

				.end() // end filter
				.log("Invalid job discarded.");
	}

	/**
	 * Processor that uses StrategySelector to process jobs.
	 */
	static class JobStrategyProcessor implements Processor {
		@Override
		public void process(Exchange exchange) throws Exception {
			Job job = exchange.getIn().getBody(Job.class);

			JobProcessorStrategy strategy = StrategySelector.getStrategy(job.getType());
			JobResult result = strategy.process(job);

			exchange.getMessage().setBody(result);
		}
	}

	/**
	 * Simple filter to reject invalid jobs.
	 */
	public static class JobValidationFilter {
		public boolean isValid(String body) {
			return body != null && body.contains("type") && body.contains("payload");
		}
	}
}
