package com.alerts;

/**
 * The {@code BloodOxygenAlertFactory} class is a concrete factory for creating {@link BloodOxygenAlert} objects.
 * It extends the {@link AlertFactory} class and provides an implementation for creating blood oxygen alerts.
 */
public class BloodOxygenAlertFactory extends AlertFactory {
    /**
     * Creates a {@link BloodOxygenAlert} with the specified patient ID, condition, and timestamp.
     *
     * @param patientId the ID of the patient
     * @param condition the condition triggering the alert
     * @param timestamp the time the alert was generated
     * @return a new {@code BloodOxygenAlert} object
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new BloodOxygenAlert(patientId, condition, timestamp);
    }
}
