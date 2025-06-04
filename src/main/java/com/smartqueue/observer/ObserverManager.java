package com.smartqueue.observer;

import com.smartqueue.model.JobResult;

import java.util.ArrayList;
import java.util.List;

public class ObserverManager {

	private static final List<JobStatusObserver> observers = new ArrayList<JobStatusObserver>();

	public static void register(JobStatusObserver observer) {
		System.out.println("[DEBUG] Registering observer: " + observer);
		observers.add(observer);
	}

	public static void notifyAll(JobResult result) {
		System.out.println("[DEBUG] Notifying observers. Count: " + observers.size());
		for (JobStatusObserver observer : observers) {
			observer.update(result);
		}
	}

}
