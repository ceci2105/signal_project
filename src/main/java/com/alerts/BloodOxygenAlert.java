package com.alerts;

/**
 * The {@code BloodOxygenAlert} class represents a specific type of alert related to blood oxygen levels.
 * It extends the {@link Alert} class to provide additional context specific to blood oxygen alerts.
 */
public class BloodOxygenAlert extends Alert {
    /**
     * Constructs a {@code BloodOxygenAlert} with the specified patient ID, condition, and timestamp.
     *
     * @param patientId the ID of the patient
     * @param condition the condition triggering the alert
     * @param timestamp the time the alert was generated
     */
    public BloodOxygenAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }

    /**
     * Returns a string representation of the blood oxygen alert.
     *
     * @return a string detailing the blood oxygen alert, including patient ID, condition, and timestamp
     */
    @Override
    public String toString() {
        return "Blood Oxygen Alert for Patient " + getPatientId() + ": " + getCondition() + " at " + getTimestamp();
    }
}
