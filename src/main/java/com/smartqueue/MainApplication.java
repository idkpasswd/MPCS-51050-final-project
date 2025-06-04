package com.smartqueue;

import com.smartqueue.input.JobSubmissionController;
import com.smartqueue.input.JsonFileJobImporter;
import com.smartqueue.observer.JobStatusPublisher;
import com.smartqueue.observer.JobStatusSubscriber;
import com.smartqueue.observer.ObserverManager;
import com.smartqueue.routes.*;

import org.apache.camel.main.Main;

public class MainApplication {
    public static void main(String[] args) throws Exception {
        Main main = new Main();

        System.out.println("✅ Starting SmartTaskQueue Application...");

        // Register all Camel route builders (actual files in your project)
        main.configure().addRoutesBuilder(new JobSubmissionController());
        main.configure().addRoutesBuilder(new JsonFileJobImporter());
        main.configure().addRoutesBuilder(new GatewayRouteBuilder());
        main.configure().addRoutesBuilder(new JobRouteBuilder());
        main.configure().addRoutesBuilder(new SplitterRouteBuilder());
        main.configure().addRoutesBuilder(new AggregatorRouteBuilder());
        main.configure().addRoutesBuilder(new JobCompletionNotifierRoute());
        main.configure().addRoutesBuilder(new DeadLetterRouteBuilder());

        // Register WorkerConsumers (Competing Consumers)
        main.configure().addRoutesBuilder(new WorkerConsumer(1));
        main.configure().addRoutesBuilder(new WorkerConsumer(2));
        main.configure().addRoutesBuilder(new WorkerConsumer(3));

        // Register observer system
        ObserverManager.register(new JobStatusSubscriber("Client-A"));
        ObserverManager.register(new JobStatusSubscriber("Client-B"));
        main.bind("JobSplitterBean", new SplitterRouteBuilder.JobSplitterBean());
        main.bind("jobStatusPublisher", new JobStatusPublisher());
        
        main.run(args);
    }
}
