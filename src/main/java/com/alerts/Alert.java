package com.alerts;

public class Alert {
    private String patientId;
    private String condition;
    private long timestamp;

    public Alert(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getCondition() {
        return condition;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void triggerAlert() {
        System.out.println("Alert for patient " + patientId + ": " + condition + " at " + timestamp);
    }

    public String getDetails() {
        return "Patient ID: " + patientId + ", Condition: " + condition + ", Timestamp: " + timestamp;
    }
}
