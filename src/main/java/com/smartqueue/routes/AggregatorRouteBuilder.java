package com.smartqueue.routes;

import com.smartqueue.model.JobResult;
import com.smartqueue.model.SubTask;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import java.util.ArrayList;
import java.util.List;

public class AggregatorRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		System.out.println("- [DEBUG] AggregatorRouteBuilder initialized");

		from("activemq:queue:subtask.result.queue").routeId("aggregator-route").log("Received sub-task result: ${body}")
				.unmarshal().json(JsonLibrary.Jackson, SubTask.class)
				.aggregate(header("jobId"), new SubTaskAggregationStrategy()).completionSize(2) // Set to number of
																								// subtasks per job
				.completionTimeout(2000).process(exchange -> {
					System.out.println("Aggregator");
					List<SubTask> completedSubTasks = exchange.getIn().getBody(List.class);
					String jobId = (String) exchange.getIn().getHeader("jobId");

					StringBuilder summary = new StringBuilder();
					boolean allSuccess = true;

					for (SubTask task : completedSubTasks) {
						summary.append("[").append(task.getOperation()).append(": ").append(task.getResult())
								.append("], ");
						if (!"done".equalsIgnoreCase(task.getStatus())) {
							allSuccess = false;
						}
					}

					JobResult result = new JobResult(jobId, allSuccess,
							allSuccess ? "All sub-tasks completed" : "Some sub-tasks failed", summary.toString());
					System.out.println("- Aggregated JobResult: " + result);
					exchange.getMessage().setBody(result);
				}).marshal().json(JsonLibrary.Jackson).to("activemq:queue:result.queue").process(exchange -> {
					System.out.println("- Send to queue:result.queue:");
				}).log("Aggregated result sent: ${body}");
	}

	/**
	 * AggregationStrategy that combines SubTasks into a list.
	 */
	static class SubTaskAggregationStrategy implements AggregationStrategy {
		@Override
		public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
			SubTask newTask = newExchange.getIn().getBody(SubTask.class);

			if (oldExchange == null) {
				List<SubTask> list = new ArrayList<>();
				list.add(newTask);
				newExchange.getIn().setBody(list);
				return newExchange;
			}

			List<SubTask> list = oldExchange.getIn().getBody(List.class);
			list.add(newTask);
			return oldExchange;
		}
	}
}
