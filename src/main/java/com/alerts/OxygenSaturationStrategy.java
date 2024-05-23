package com.alerts;

public class OxygenSaturationStrategy implements AlertStrategy {
    private static final double CRITICAL_THRESHOLD = 90.0; // example threshold

    @Override
    public boolean checkAlert(String patientId, double reading) {
        // Check for critical drops in oxygen saturation
        return reading < CRITICAL_THRESHOLD;
    }
}

