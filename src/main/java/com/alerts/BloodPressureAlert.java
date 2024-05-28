package com.alerts;

/**
 * The {@code BloodPressureAlert} class represents a specific type of alert related to blood pressure levels.
 * It extends the {@link Alert} class to provide additional context specific to blood pressure alerts.
 */
public class BloodPressureAlert extends Alert {
    /**
     * Constructs a {@code BloodPressureAlert} with the specified patient ID, condition, and timestamp.
     *
     * @param patientId the ID of the patient
     * @param condition the condition triggering the alert
     * @param timestamp the time the alert was generated
     */
    public BloodPressureAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }

    /**
     * Returns a string representation of the blood pressure alert.
     *
     * @return a string detailing the blood pressure alert, including patient ID, condition, and timestamp
     */
    @Override
    public String toString() {
        return "Blood Pressure Alert for Patient " + getPatientId() + ": " + getCondition() + " at " + getTimestamp();
    }
}

