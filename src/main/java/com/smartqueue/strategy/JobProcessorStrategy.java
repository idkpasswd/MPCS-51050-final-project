package com.smartqueue.strategy;

import com.smartqueue.model.Job;
import com.smartqueue.model.JobResult;

public interface JobProcessorStrategy {
    JobResult process(Job job);
}
