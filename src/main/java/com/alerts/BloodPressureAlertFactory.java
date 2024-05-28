package com.alerts;

/**
 * The {@code BloodPressureAlertFactory} class is a concrete factory for creating {@link BloodPressureAlert} objects.
 * It extends the {@link AlertFactory} class and provides an implementation for creating blood pressure alerts.
 */
public class BloodPressureAlertFactory extends AlertFactory {
    /**
     * Creates a {@link BloodPressureAlert} with the specified patient ID, condition, and timestamp.
     *
     * @param patientId the ID of the patient
     * @param condition the condition triggering the alert
     * @param timestamp the time the alert was generated
     * @return a new {@code BloodPressureAlert} object
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new BloodPressureAlert(patientId, condition, timestamp);
    }
}
