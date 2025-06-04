package com.smartqueue.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartqueue.model.Job;

public class JobFactory {

    // Use Jackson's ObjectMapper to parse JSON into Job objects
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Creates a Job object from a raw JSON string.
     *
     * @param jsonString the raw JSON input representing a Job
     * @return Job instance
     * @throws Exception if JSON is invalid or missing required fields
     */
    public static Job createJobFromJson(String jsonString) throws Exception {
        return objectMapper.readValue(jsonString, Job.class);
    }

    // Optional: Future extensions for XML or REST input
    // public static Job createJobFromXml(String xml) { ... }
}
