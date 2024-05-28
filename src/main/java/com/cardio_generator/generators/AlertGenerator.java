package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * The {@code AlertGenerator} class generates and evaluates patient data to trigger alerts based on specific conditions.
 */

public class AlertGenerator implements PatientDataGenerator {

    public static final Random randomGenerator = new Random();
    private boolean[] alertStates;
    private Queue<Double> systolicQueue;
    private Queue<Double> diastolicQueue;
    private final int trendWindowSize = 3;
    private final double trendThreshold = 10.0;
    private DataStorage dataStorage;

    public AlertGenerator(int patientCount, DataStorage dataStorage) {
        alertStates = new boolean[patientCount + 1];
        systolicQueue = new ArrayDeque<>();
        diastolicQueue = new ArrayDeque<>();
        this.dataStorage = dataStorage;
    }

    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        // General method to generate data
        bloodPressureAlert(patientId, outputStrategy);
        bloodSaturationAlert(patientId, outputStrategy);
        hypotensiveHypoxemiaAlert(patientId, outputStrategy);
        ECGalert(patientId, outputStrategy);
        triggerAlert(patientId, outputStrategy);
    }

    public void bloodPressureAlert(int patientId, OutputStrategy outputStrategy) {
        List<PatientRecord> records = dataStorage.getRecords(patientId, 0, System.currentTimeMillis());

        for (PatientRecord record : records) {
            if (record.getLabel().equals("BloodPressureSystolic")) {
                double systolic = record.getMeasurementValue();
                systolicQueue.add(systolic);
                if (systolicQueue.size() > trendWindowSize) {
                    systolicQueue.poll();
                }
                if (systolic > 180 || systolic < 90) {
                    outputStrategy.output(patientId, record.getTimestamp(), "Critical Threshold Alert", "triggered");
                }
                if (systolicQueue.size() == trendWindowSize && isTrending(systolicQueue)) {
                    outputStrategy.output(patientId, record.getTimestamp(), "Trend Alert", "triggered");
                }
            } else if (record.getLabel().equals("BloodPressureDiastolic")) {
                double diastolic = record.getMeasurementValue();
                diastolicQueue.add(diastolic);
                if (diastolicQueue.size() > trendWindowSize) {
                    diastolicQueue.poll();
                }
                if (diastolic > 120 || diastolic < 60) {
                    outputStrategy.output(patientId, record.getTimestamp(), "Critical Threshold Alert", "triggered");
                }
                if (diastolicQueue.size() == trendWindowSize && isTrending(diastolicQueue)) {
                    outputStrategy.output(patientId, record.getTimestamp(), "Trend Alert", "triggered");
                }
            }
        }
    }

    public void bloodSaturationAlert(int patientId, OutputStrategy outputStrategy) {
        List<PatientRecord> records = dataStorage.getRecords(patientId, 0, System.currentTimeMillis());

        for (PatientRecord record : records) {
            if (record.getLabel().equals("BloodSaturation")) {
                double bloodSaturation = record.getMeasurementValue();
                if (bloodSaturation < 92) {
                    outputStrategy.output(patientId, record.getTimestamp(), "Low Saturation Alert", "triggered");
                }
                long currentTime = System.currentTimeMillis();
                List<PatientRecord> recentRecords = dataStorage.getRecords(patientId, currentTime - 600000, currentTime);
                for (PatientRecord recentRecord : recentRecords) {
                    if (recentRecord.getLabel().equals("BloodSaturation")) {
                        double recentBloodSaturation = recentRecord.getMeasurementValue();
                        if (bloodSaturation < recentBloodSaturation - 5) {
                            outputStrategy.output(patientId, record.getTimestamp(), "Rapid Drop Alert", "triggered");
                            break;
                        }
                    }
                }
            }
        }
    }

    public void hypotensiveHypoxemiaAlert(int patientId, OutputStrategy outputStrategy) {
        List<PatientRecord> records = dataStorage.getRecords(patientId, 0, System.currentTimeMillis());

        double systolic = Double.MAX_VALUE;
        double bloodSaturation = Double.MAX_VALUE;

        for (PatientRecord record : records) {
            if (record.getLabel().equals("BloodPressureSystolic")) {
                systolic = record.getMeasurementValue();
            } else if (record.getLabel().equals("BloodSaturation")) {
                bloodSaturation = record.getMeasurementValue();
            }

            if (systolic < 90 && bloodSaturation < 92) {
                outputStrategy.output(patientId, record.getTimestamp(), "Hypotensive Hypoxemia Alert", "triggered");
                break;
            }
        }
    }

    public void ECGalert(int patientId, OutputStrategy outputStrategy) {
        List<PatientRecord> records = dataStorage.getRecords(patientId, 0, System.currentTimeMillis());
        Queue<Double> ecgQueue = new ArrayDeque<>();
        double sum = 0;
        int windowSize = 5; // Example window size

        for (PatientRecord record : records) {
            if (record.getLabel().equals("ECG")) {
                double value = record.getMeasurementValue();
                if (ecgQueue.size() >= windowSize) {
                    sum -= ecgQueue.poll();
                }
                ecgQueue.add(value);
                sum += value;

                double average = sum / ecgQueue.size();
                if (value > average * 1.5) { // Example condition for an abnormal peak
                    outputStrategy.output(patientId, record.getTimestamp(), "Abnormal Heart Rate Alert", "triggered");
                }
            }
        }
    }

    public void triggerAlert(int patientId, OutputStrategy outputStrategy) {
        outputStrategy.output(patientId, System.currentTimeMillis(), "Triggered Alert", "triggered");
    }

  
    public void evaluateData(Patient patient, OutputStrategy outputStrategy) {
        List<PatientRecord> records = dataStorage.getRecords(patient.getPatientId(), 0, System.currentTimeMillis());

        for (PatientRecord record : records) {
            switch (record.getLabel()) {
                case "HeartRate":
                    if (record.getMeasurementValue() < 60 || record.getMeasurementValue() > 100) {
                        outputStrategy.output(patient.getPatientId(), record.getTimestamp(), "Abnormal Heart Rate Alert", "triggered");
                    }
                    break;
                case "BloodPressureSystolic":
                    if (record.getMeasurementValue() > 140) {
                        outputStrategy.output(patient.getPatientId(), record.getTimestamp(), "Critical Threshold Alert", "triggered");
                    }
                    break;
                // Add more cases for other types of data
            }
        }
    }

    private boolean isTrending(Queue<Double> queue) {
        if (queue.size() < trendWindowSize) return false;

        Double[] values = queue.toArray(new Double[0]);
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < values.length; i++) {
            if (values[i] <= values[i - 1] + trendThreshold) {
                increasing = false;
            }
            if (values[i] >= values[i - 1] - trendThreshold) {
                decreasing = false;
            }
        }
        return increasing || decreasing;
    }

    @Override
    public void evaluateData(Patient patient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'evaluateData'");
    }

   
}
