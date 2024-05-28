package com.alerts;

/**
 * The {@code HeartRateStrategy} class implements the {@link AlertStrategy} interface for checking heart rate alerts.
 * It defines a range of acceptable heart rates and triggers an alert if the reading falls outside this range.
 */
public class HeartRateStrategy implements AlertStrategy {
    private static final double MIN_HEART_RATE = 60.0; // example minimum heart rate
    private static final double MAX_HEART_RATE = 100.0; // example maximum heart rate

    /**
     * Checks if an alert should be triggered based on the provided heart rate reading.
     * An alert is triggered if the reading falls below the minimum or above the maximum acceptable range.
     *
     * @param patientId the ID of the patient
     * @param reading the heart rate reading to be evaluated
     * @return {@code true} if an alert should be triggered, {@code false} otherwise
     */
    @Override
    public boolean checkAlert(String patientId, double reading) {
        // Check for abnormal heart rate
        return reading < MIN_HEART_RATE || reading > MAX_HEART_RATE;
    }
}
