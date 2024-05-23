package com.alerts;

public class BloodPressureStrategy implements AlertStrategy {
    private static final double CRITICAL_THRESHOLD = 140.0; // example threshold

    @Override
    public boolean checkAlert(String patientId, double reading) {
        // Simplified check for blood pressure alert
        return reading > CRITICAL_THRESHOLD;
    }
}
