package com.data_management;

public class PatientRecord {
    private String label;
    private double measurementValue;
    private long timestamp;

    public PatientRecord(String label, double measurementValue, long timestamp) {
        this.label = label;
        this.measurementValue = measurementValue;
        this.timestamp = timestamp;
    }

    public String getLabel() {
        return label;
    }

    public double getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(double measurementValue) {
        this.measurementValue = measurementValue;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

