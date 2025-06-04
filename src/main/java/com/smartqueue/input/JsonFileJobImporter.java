package com.smartqueue.input;

import org.apache.camel.builder.RouteBuilder;

public class JsonFileJobImporter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("file:data/input-jobs?noop=true&include=.*\\.json")
            .routeId("file-job-importer")
            .log("Imported job file: ${file:name}")
            .convertBodyTo(String.class)
            .to("direct:submit-job");
    }
}
