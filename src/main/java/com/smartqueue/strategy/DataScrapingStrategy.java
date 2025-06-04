package com.smartqueue.strategy;

import com.smartqueue.model.Job;
import com.smartqueue.model.JobResult;

public class DataScrapingStrategy implements JobProcessorStrategy {

//    @Override
    public JobResult process(Job job) {
        String result = "Scraped data from: " + job.getPayload();
        return new JobResult(job.getJobId(), true, "Data scraped", result);
    }
}
