package com.smartqueue.strategy;

import com.smartqueue.model.Job;
import com.smartqueue.model.JobResult;

public class FileConversionStrategy implements JobProcessorStrategy {

//    @Override
    public JobResult process(Job job) {
        String result = "Converted file: " + job.getPayload();
        return new JobResult(job.getJobId(), true, "File converted", result);
    }
}
