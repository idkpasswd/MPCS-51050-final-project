package com.smartqueue.processor;

import com.smartqueue.model.JobResult;
import com.smartqueue.model.SubTask;
import com.smartqueue.model.Job;
import com.smartqueue.strategy.JobProcessorStrategy;
import com.smartqueue.strategy.StrategySelector;

public class JobExecutor {

	public static JobResult execute(Job job) {
		JobProcessorStrategy strategy = StrategySelector.getStrategy(job.getType());
		return strategy.process(job);
	}
	
	public static JobResult execute(SubTask subTask) {
   
        JobResult result = new JobResult();
        result.setSuccess(true);
        result.setMessage("Processed subtask: " + subTask.getTaskId());
        return result;
    }
}
