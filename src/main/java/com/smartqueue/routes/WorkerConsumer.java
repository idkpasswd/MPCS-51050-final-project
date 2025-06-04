package com.smartqueue.routes;

import com.smartqueue.model.SubTask;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class WorkerConsumer extends RouteBuilder {

	private final int instanceId;

	public WorkerConsumer(int instanceId) {
		this.instanceId = instanceId;
	}

	@Override
	public void configure() throws Exception {
		System.out.println("- [DEBUG] WorkerConsumer " + instanceId + " initialized");

		from("activemq:queue:subtask.queue").routeId("worker-consumer-" + instanceId).unmarshal()
				.json(JsonLibrary.Jackson, SubTask.class).process(exchange -> {
					SubTask subTask = exchange.getIn().getBody(SubTask.class);
					System.out.println("- Worker " + instanceId + " started processing: " + subTask);
					// Simulate actual work
					subTask.setStatus("done");
					subTask.setResult("success");
					// Set updated SubTask as the message body and jobId as header
					exchange.getMessage().setBody(subTask);
					exchange.getMessage().setHeader("jobId", subTask.getJobId()); // <<<< ADD THIS!
				}).log("- Worker " + instanceId + " finished subtask: ${body}").marshal().json(JsonLibrary.Jackson)
				.to("activemq:queue:subtask.result.queue").process(exchange -> {
					System.out.println("- Send to subtask.result.queue:");
				}).log("✅ Worker " + instanceId + " finished subtask, sent result: ${body}");

	}
}
