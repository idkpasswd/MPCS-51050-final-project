package com.smartqueue.model;

public class JobResult {

	private String jobId; // ID of the original Job
	private boolean success; // Overall success status
	private String message; // Summary or error message
	private String resultPayload; // Optional: final output data (JSON, link, etc.)

	// Default constructor
	public JobResult() {
	}

	// Constructor with fields
	public JobResult(String jobId, boolean success, String message, String resultPayload) {
		this.jobId = jobId;
		this.success = success;
		this.message = message;
		this.resultPayload = resultPayload;
	}

	// --- Getters and Setters ---

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResultPayload() {
		return resultPayload;
	}

	public void setResultPayload(String resultPayload) {
		this.resultPayload = resultPayload;
	}

	// --- toString for logging/debugging ---

	@Override
	public String toString() {
		return "JobResult{" + "jobId='" + jobId + '\'' + ", success=" + success + ", message='" + message + '\''
				+ ", resultPayload='" + resultPayload + '\'' + '}';
	}
}
