package com.smartqueue.routes;

import com.smartqueue.model.Job;
import com.smartqueue.model.SubTask;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import java.util.List;

public class SplitterRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		System.out.println("- SplitterRouteBuilder initialized");

		from("activemq:queue:job.split").routeId("splitter-route").log("Splitting job into sub-tasks: ${body}")
				.unmarshal().json(JsonLibrary.Jackson, Job.class).process(exchange -> {
					Job job = exchange.getIn().getBody(Job.class);
					System.out.println("Received Job object: " + job);
				}).split(method(JobSplitterBean.class, "splitIntoSubTasks")).process(exchange -> {
					SubTask subTask = exchange.getIn().getBody(SubTask.class);
					System.out.println("- Split subtask: " + subTask);
				}).log("Sent SubTask to worker: ${body}").marshal().json(JsonLibrary.Jackson)
				.to("activemq:queue:subtask.queue").process(exchange -> {
					System.out.println("- Send to subtask.queue:");
				});
	}

	/**
	 * Helper bean that extracts sub-tasks from a Job
	 */
	public static class JobSplitterBean {
		public List<SubTask> splitIntoSubTasks(Job job) {
			return job.getSubTasks();
		}
	}
}
