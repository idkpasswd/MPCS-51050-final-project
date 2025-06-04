package com.smartqueue.observer;

import com.smartqueue.model.JobResult;

public class JobStatusSubscriber implements JobStatusObserver {

	private final String subscriberId;

	public JobStatusSubscriber(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	@Override
	public void update(JobResult result) {
		System.out.println("🔔 [" + subscriberId + "] Received update: " + result);
	}
}
