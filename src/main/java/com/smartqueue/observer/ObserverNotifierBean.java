package com.smartqueue.observer;

import com.smartqueue.model.JobResult;

public class ObserverNotifierBean {

	public void notifyObservers(JobResult result) {
		System.out.println("[NOTIFY] JobResult sent to observers: " + result);
	}
}
