package com.smartqueue.strategy;

import com.smartqueue.model.Job;
import com.smartqueue.model.JobResult;

public class ImageProcessingStrategy implements JobProcessorStrategy {

//    @Override
    public JobResult process(Job job) {
        // Simulate image processing
        String payload = job.getPayload();
        String result = "Processed image from: " + payload;
        return new JobResult(job.getJobId(), true, "Image processed", result);
    }
}
