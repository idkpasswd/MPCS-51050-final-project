package com.smartqueue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class Job {

	private String jobId;
	private String type;
	private String status;
	private String payload;
	private List<SubTask> subTasks;

	// Default constructor (required for Jackson)
	public Job() {
		this.jobId = UUID.randomUUID().toString(); // Auto-generate ID
		this.status = "pending";
	}

	// Parameterized constructor
	public Job(String type, String payload) {
		this();
		this.type = type;
		this.payload = payload;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public List<SubTask> getSubTasks() {
//		System.out.println("get");
		if (subTasks == null) {
			System.out.println("null");
			return java.util.Collections.emptyList();
		}
		return subTasks;
	}

	public void setSubTasks(List<SubTask> subTasks) {
		this.subTasks = subTasks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Job{" + "jobId='" + jobId + '\'' + ", type='" + type + '\'' + ", status='" + status + '\''
				+ ", payload='" + payload + '\'' + ", subTasks=" + subTasks + '}';
	}
}
