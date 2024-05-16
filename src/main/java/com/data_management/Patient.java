package com.data_management;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private int patientId;
    private List<PatientRecord> records;

    public Patient(int patientId) {
        this.patientId = patientId;
        this.records = new ArrayList<>();
    }

    public void addRecord(double measurementValue, String recordType, long timestamp) {
        records.add(new PatientRecord(recordType, measurementValue, timestamp));
    }

    public void addOrUpdateRecord(double measurementValue, String recordType, long timestamp) {
        for (PatientRecord record : records) {
            if (record.getTimestamp() == timestamp && record.getLabel().equals(recordType)) {
                // Update existing record
                record.setMeasurementValue(measurementValue);
                return;
            }
        }
        // Add new record if not found
        addRecord(measurementValue, recordType, timestamp);
    }

    public List<PatientRecord> getRecords(long startTime, long endTime) {
        List<PatientRecord> result = new ArrayList<>();
        for (PatientRecord record : records) {
            if (record.getTimestamp() >= startTime && record.getTimestamp() <= endTime) {
                result.add(record);
            }
        }
        return result;
    }

    public int getPatientId() {
        return patientId;
    }
}

