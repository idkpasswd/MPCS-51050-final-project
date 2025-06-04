package com.smartqueue.observer;

import com.smartqueue.model.JobResult;

public interface JobStatusObserver {
	void update(JobResult result);
}
