package com.data_management;

public class PatientRecord {
    private int patientId;
    private String label;
    private double measurementValue;
    private long timestamp;


    // New constructor matching the usage in the Patient class
    public PatientRecord(int patientId, String label, double measurementValue, long timestamp) {
        this.patientId = patientId;
        this.label = label;
        this.measurementValue = measurementValue;
        this.timestamp = timestamp;
    }

    public int getPatientId() {
        return patientId;
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


