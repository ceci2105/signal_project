package com.alerts;


public interface AlertStrategy {
    boolean checkAlert(String patientId, double reading);
}

