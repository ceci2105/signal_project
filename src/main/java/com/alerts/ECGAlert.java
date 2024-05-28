package com.alerts;

/**
 * The {@code ECGAlert} class represents a specific type of alert related to ECG (Electrocardiogram) readings.
 * It extends the {@link Alert} class to provide additional context specific to ECG alerts.
 */
public class ECGAlert extends Alert {
    /**
     * Constructs an {@code ECGAlert} with the specified patient ID, condition, and timestamp.
     *
     * @param patientId the ID of the patient
     * @param condition the condition triggering the alert
     * @param timestamp the time the alert was generated
     */
    public ECGAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }

    /**
     * Returns a string representation of the ECG alert.
     *
     * @return a string detailing the ECG alert, including patient ID, condition, and timestamp
     */
    @Override
    public String toString() {
        return "ECG Alert for Patient " + getPatientId() + ": " + getCondition() + " at " + getTimestamp();
    }
}
