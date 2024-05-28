package com.alerts;

/**
 * The {@code OxygenSaturationStrategy} class implements the {@link AlertStrategy} interface for checking oxygen saturation alerts.
 * It defines a critical threshold below which an alert should be triggered.
 */
public class OxygenSaturationStrategy implements AlertStrategy {
    private static final double CRITICAL_THRESHOLD = 90.0; // example threshold

    /**
     * Checks if an alert should be triggered based on the provided oxygen saturation reading.
     * An alert is triggered if the reading falls below the critical threshold.
     *
     * @param patientId the ID of the patient
     * @param reading the oxygen saturation reading to be evaluated
     * @return {@code true} if an alert should be triggered, {@code false} otherwise
     */
    @Override
    public boolean checkAlert(String patientId, double reading) {
        // Check for critical drops in oxygen saturation
        return reading < CRITICAL_THRESHOLD;
    }
}
