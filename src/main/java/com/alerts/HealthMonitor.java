package com.alerts;

public class HealthMonitor {
    private AlertStrategy alertStrategy;

    public HealthMonitor(AlertStrategy alertStrategy) {
        this.alertStrategy = alertStrategy;
    }

    public void setAlertStrategy(AlertStrategy alertStrategy) {
        this.alertStrategy = alertStrategy;
    }

    public boolean checkAlert(String patientId, double reading) {
        return alertStrategy.checkAlert(patientId, reading);
    }
}

