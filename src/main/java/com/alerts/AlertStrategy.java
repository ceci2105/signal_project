package com.alerts;

/**
 * The {@code AlertStrategy} interface defines a strategy for checking if an alert should be triggered
 * based on a patient's reading.
 */
public interface AlertStrategy {
    /**
     * Checks if an alert should be triggered for the given patient based on the provided reading.
     *
     * @param patientId the ID of the patient
     * @param reading the reading to be evaluated
     * @return {@code true} if an alert should be triggered, {@code false} otherwise
     */
    boolean checkAlert(String patientId, double reading);
}

