package com.smartqueue.observer;

import com.smartqueue.model.JobResult;

public class JobStatusPublisher {

	public void publish(JobResult result) {
	    System.out.println("- Publishing JobResult: " + result);
	    ObserverManager.notifyAll(result);
	}

}
