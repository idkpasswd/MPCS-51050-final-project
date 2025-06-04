package com.smartqueue.model;

import java.util.UUID;

public class SubTask {

    private String taskId;      // Unique ID for the sub-task
    private String jobId;       // Reference to the parent job
    private String operation;   // e.g., "resize", "compress", "upload"
    private String payload;     // Specific data for this operation
    private String status;      // pending, processing, done, error
    private String result;      // Optional: result string from processing

    // Default constructor (for Jackson)
    public SubTask() {
        this.taskId = UUID.randomUUID().toString();
        this.status = "pending";
    }

    // Constructor with fields
    public SubTask(String jobId, String operation, String payload) {
        this.taskId = UUID.randomUUID().toString();
        this.jobId = jobId;
        this.operation = operation;
        this.payload = payload;
        this.status = "pending";
    }

    // --- Getters and Setters ---

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // --- toString for logging/debugging ---

    @Override
    public String toString() {
        return "SubTask{" +
                "taskId='" + taskId + '\'' +
                ", jobId='" + jobId + '\'' +
                ", operation='" + operation + '\'' +
                ", payload='" + payload + '\'' +
                ", status='" + status + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
