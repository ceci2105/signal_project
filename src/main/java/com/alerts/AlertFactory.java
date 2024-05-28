package com.alerts;

/**
 * The {@code AlertFactory} class is an abstract factory class for creating {@link Alert} objects.
 * Subclasses of this factory will provide concrete implementations for creating specific types of alerts.
 */
public abstract class AlertFactory {
    /**
     * Creates an {@code Alert} object with the specified patient ID, condition, and timestamp.
     *
     * @param patientId the ID of the patient
     * @param condition the condition triggering the alert
     * @param timestamp the time the alert was generated
     * @return an {@code Alert} object
     */
    public abstract Alert createAlert(String patientId, String condition, long timestamp);
}
