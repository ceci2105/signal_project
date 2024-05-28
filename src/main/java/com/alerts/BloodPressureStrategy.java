package com.alerts;

/**
 * The {@code BloodPressureStrategy} class implements the {@link AlertStrategy} interface for checking blood pressure alerts.
 * It defines a specific threshold above which an alert should be triggered.
 */
public class BloodPressureStrategy implements AlertStrategy {
    private static final double CRITICAL_THRESHOLD = 140.0; // example threshold

    /**
     * Checks if an alert should be triggered based on the provided blood pressure reading.
     * If the reading exceeds the critical threshold, an alert is triggered.
     *
     * @param patientId the ID of the patient
     * @param reading the blood pressure reading to be evaluated
     * @return {@code true} if the reading exceeds the critical threshold, {@code false} otherwise
     */
    @Override
    public boolean checkAlert(String patientId, double reading) {
        // Simplified check for blood pressure alert
        return reading > CRITICAL_THRESHOLD;
    }
}
