package com.alerts;

/**
 * The {@code Alert} class represents an alert related to a patient's condition.
 * It contains information about the patient, the condition, and the time the alert was generated.
 */
public class Alert {
    private String patientId;
    private String condition;
    private long timestamp;

    /**
     * Constructs an {@code Alert} with the specified patient ID, condition, and timestamp.
     *
     * @param patientId the ID of the patient
     * @param condition the condition triggering the alert
     * @param timestamp the time the alert was generated
     */
    public Alert(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    /**
     * Returns the patient ID associated with this alert.
     *
     * @return the patient ID
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Returns the condition that triggered this alert.
     *
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Returns the timestamp of when this alert was generated.
     *
     * @return the timestamp in milliseconds since epoch
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Triggers the alert by printing a message to the standard output.
     * The message includes the patient ID, condition, and timestamp of the alert.
     */
    public void triggerAlert() {
        System.out.println("Alert for patient " + patientId + ": " + condition + " at " + timestamp);
    }

    /**
     * Returns the details of the alert as a string.
     * The details include the patient ID, condition, and timestamp.
     *
     * @return a string representation of the alert details
     */
    public String getDetails() {
        return "Patient ID: " + patientId + ", Condition: " + condition + ", Timestamp: " + timestamp;
    }
}
