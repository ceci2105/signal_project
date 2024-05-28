package com.alerts;

/**
 * The {@code HealthMonitor} class represents a health monitoring system that checks for alerts based on readings.
 * It uses an {@link AlertStrategy} to determine if an alert should be triggered for a given patient and reading.
 */
public class HealthMonitor {
    private AlertStrategy alertStrategy;

    /**
     * Constructs a {@code HealthMonitor} with the specified alert strategy.
     *
     * @param alertStrategy the strategy used to determine if an alert should be triggered
     */
    public HealthMonitor(AlertStrategy alertStrategy) {
        this.alertStrategy = alertStrategy;
    }

    /**
     * Sets the alert strategy used by the health monitor.
     *
     * @param alertStrategy the new alert strategy to set
     */
    public void setAlertStrategy(AlertStrategy alertStrategy) {
        this.alertStrategy = alertStrategy;
    }

    /**
     * Checks if an alert should be triggered for the specified patient based on the provided reading.
     *
     * @param patientId the ID of the patient
     * @param reading the reading to be evaluated
     * @return {@code true} if an alert should be triggered, {@code false} otherwise
     */
    public boolean checkAlert(String patientId, double reading) {
        return alertStrategy.checkAlert(patientId, reading);
    }
}

