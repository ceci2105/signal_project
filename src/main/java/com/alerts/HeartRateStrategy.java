package com.alerts;

public class HeartRateStrategy implements AlertStrategy {
    private static final double MIN_HEART_RATE = 60.0; // example minimum heart rate
    private static final double MAX_HEART_RATE = 100.0; // example maximum heart rate

    @Override
    public boolean checkAlert(String patientId, double reading) {
        // Check for abnormal heart rate
        return reading < MIN_HEART_RATE || reading > MAX_HEART_RATE;
    }
}
