package com.alerts;

/**
 * The {@code ECGAlertFactory} class is a concrete factory for creating {@link ECGAlert} objects.
 * It extends the {@link AlertFactory} class and provides an implementation for creating ECG alerts.
 */
public class ECGAlertFactory extends AlertFactory {
    /**
     * Creates an {@link ECGAlert} with the specified patient ID, condition, and timestamp.
     *
     * @param patientId the ID of the patient
     * @param condition the condition triggering the alert
     * @param timestamp the time the alert was generated
     * @return a new {@code ECGAlert} object
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new ECGAlert(patientId, condition, timestamp);
    }
}
