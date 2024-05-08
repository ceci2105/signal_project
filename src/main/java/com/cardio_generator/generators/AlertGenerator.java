package com.cardio_generator.generators;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * Generates alerts for patients based on randomized triggers.
 */
public class AlertGenerator implements PatientDataGenerator {

   
    public static final Random randomGenerator = new Random();
    private boolean[] alertStates;
    private Queue<Double> systolicQueue;
    private Queue<Double> diastolicQueue;
    private final int trendWindowSize = 3;
    private final double trendThreshold = 10.0;
    private DataStorage dataStorage;

    

    /**
     * Constructs an AlertGenerator with the specified number of patients.
     * Initializes the alert states for each patient.
     *
     * @param patientCount The number of patients for whom alerts will be generated.
     */
    public AlertGenerator(int patientCount, DataStorage dataStorage) {
       
        alertStates = new boolean[patientCount + 1];
        systolicQueue = new ArrayDeque<>();
        diastolicQueue = new ArrayDeque<>();
        this.dataStorage = dataStorage;
    }

    /**
     * Generates blood pressure alerts for the specified patient ID and outputs them using the provided output strategy.
     * 
     * @param patientId      The ID of the patient for whom alerts are generated.
     * @param outputStrategy The strategy for outputting the generated alerts.
     */
    @Override
    public void bloodPressureAlert(int patientId, OutputStrategy outputStrategy) {
    try {
        // Generate simulated blood pressure data
        double systolicPressure = generateRandomValue(80, 200);
        double diastolicPressure = generateRandomValue(60, 120);

        // Update queues with latest readings
        updateQueues(systolicPressure, diastolicPressure);

        // Check for trend alert
        if (checkTrendAlert(systolicQueue) || checkTrendAlert(diastolicQueue)) {
            triggerAlert(outputStrategy, patientId, "Trend Alert");
        }

        // Check for critical threshold alert
        if (systolicPressure > 180 || systolicPressure < 90 || diastolicPressure > 120 || diastolicPressure < 60) {
            triggerAlert(outputStrategy, patientId, "Critical Threshold Alert");
        }

    } catch (Exception e) {
        System.err.println("An error occurred while generating alert data for the patient: " + patientId);
        e.printStackTrace();
    }
}

    private double generateRandomValue(double min, double max) {

        return min + (max - min) * randomGenerator.nextDouble();
}

    private void updateQueues(double systolicPressure, double diastolicPressure) {

        systolicQueue.offer(systolicPressure);
        diastolicQueue.offer(diastolicPressure);

        if (systolicQueue.size() > trendWindowSize) {
            systolicQueue.poll();
            diastolicQueue.poll();
    }
}

    private boolean checkTrendAlert(Queue<Double> queue) {

        if (queue.size() == trendWindowSize) {
           double first = queue.peek();
           double last = queue.toArray(new Double[0])[trendWindowSize - 1];

        return Math.abs(first - last) > trendThreshold * (trendWindowSize - 1);
    }
    return false;
}

private void triggerAlert(OutputStrategy outputStrategy, int patientId, String alertType) {

    alertStates[patientId] = true;
    outputStrategy.output(patientId, System.currentTimeMillis(), alertType, "triggered");
}


    //Define variables for the Blood Saturation
    private final double saturationThreshold = 92.0;
    private final double rapidDropThreshold = 5.0;
    private Queue<Double> saturationQueue = new ArrayDeque<>();
    private Queue<Long> timestampQueue = new ArrayDeque<>();

    
    /**
     * Generates blood saturation alerts for the specified patient ID and outputs them using the provided output strategy.
     * 
     * @param patientId      The ID of the patient for whom alerts are generated.
     * @param outputStrategy The strategy for outputting the generated alerts.
     */

    @Override
    public void bloodSaturationAlert(int patientId, OutputStrategy outputStrategy) {
        try {
            // Generate simulated saturation data
            double saturationLevel = generateRandomValue(80, 100);

            // Update queues with latest readings
            updateSaturationQueue(saturationLevel);

            // Check for low saturation alert
            if (saturationLevel < saturationThreshold) {
                triggerAlert(outputStrategy, patientId, "Low Saturation Alert");
            }

            // Check for rapid drop alert
            if (checkRapidDrop()) {
                triggerAlert(outputStrategy, patientId, "Rapid Drop Alert");
            }

        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for the patient: " + patientId);
            e.printStackTrace();
        }
    }

    private void updateSaturationQueue(double saturationLevel) {
        saturationQueue.offer(saturationLevel);
        timestampQueue.offer(System.currentTimeMillis());

        // Remove old values if the queue size exceeds a certain threshold
        while (timestampQueue.size() > 0 && timestampQueue.peek() < System.currentTimeMillis() - 10 * 60 * 1000) {
            timestampQueue.poll();
            saturationQueue.poll();
        }
    }

    private boolean checkRapidDrop() {
        if (saturationQueue.size() > 1) {
            double initialSaturation = saturationQueue.peek();
            double currentSaturation = saturationQueue.toArray(new Double[0])[saturationQueue.size() - 1];
            return initialSaturation - currentSaturation >= rapidDropThreshold;
        }
        return false;
    }

     /**
     * Generates Hypotensive hypoxemia alerts for the specified patient ID and outputs them using the provided output strategy.
     * 
     * @param patientId      The ID of the patient for whom alerts are generated.
     * @param outputStrategy The strategy for outputting the generated alerts.
     */

    @Override
    public void hypotensiveHypoxemiaAlert(int patientId, OutputStrategy outputStrategy) {
        try {
            // Simulated blood pressure and saturation data
            double systolicPressure = generateRandomValue(80, 200);
            double saturationLevel = generateRandomValue(80, 100);

            // Check for hypotensive hypoxemia alert
            if (systolicPressure < 90 && saturationLevel < 92) {
                triggerAlert(outputStrategy, patientId, "Hypotensive Hypoxemia Alert");
            }

        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for the patient: " + patientId);
            e.printStackTrace();
        }
    }

     /**
     * Generates ECG alerts for the specified patient ID and outputs them using the provided output strategy.
     * 
     * @param patientId      The ID of the patient for whom alerts are generated.
     * @param outputStrategy The strategy for outputting the generated alerts.
     */

    @Override
    public void ECGalert(int patientId, OutputStrategy outputStrategy) {
        try {
            // Simulated heart rate data
            double heartRate = generateRandomValue(40, 120);

            // Check for abnormal heart rate alert
            if (heartRate < 50 || heartRate > 100) {
                triggerAlert(outputStrategy, patientId, "Abnormal Heart Rate Alert");
            }

            // Check for irregular beat alert
            if (checkIrregularBeat()) {
                triggerAlert(outputStrategy, patientId, "Irregular Beat Alert");
            }

        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }

    
    private boolean checkIrregularBeat(Queue<Double> rrIntervals) {
        if (rrIntervals.size() > 1) {
            // Calculate average RR interval
            double sum = 0.0;
            for (Double interval : rrIntervals) {
                sum += interval;
            }
            double average = sum / rrIntervals.size();
    
            // Calculate standard deviation of RR intervals
            double squaredDifferenceSum = 0.0;
            for (Double interval : rrIntervals) {
                squaredDifferenceSum += Math.pow(interval - average, 2);
            }
            double standardDeviation = Math.sqrt(squaredDifferenceSum / rrIntervals.size());
    
            // Define threshold for irregular beats (e.g., 30% deviation from average)
            double threshold = 0.3 * average;
    
            // Check if standard deviation exceeds threshold
            return standardDeviation > threshold;
        }
        return false;
    }

    /**
     * Generates triggered alerts for the specified patient ID and outputs them using the provided output strategy.
     * 
     * @param patientId      The ID of the patient for whom alerts are generated.
     * @param outputStrategy The strategy for outputting the generated alerts.
     */

    @Override
    public void triggerAlert(int patientId, OutputStrategy outputStrategy) {
        try {
            // Logic to trigger alerts based on external triggers 
            boolean triggered = checkExternalTrigger();

            if (triggered) {
                triggerAlert(outputStrategy, patientId, "Triggered Alert");
            }

        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }

    private boolean checkExternalTrigger() {
        // Logic to check if an external trigger has occurred
        return false; 
    }

    @Override
    public void evaluateData(Patient patient) {
        List<PatientRecord> records = dataStorage.getRecords(patient.getPatientId(), 0, System.currentTimeMillis());

        for (PatientRecord record : records) {
            String recordType = record.getRecordType();
            double measurementValue = record.getMeasurementValue();
            long timestamp = record.getTimestamp();

            switch (recordType) {
                case "HeartRate":
                    if (measurementValue < 50 || measurementValue > 100) {
                        triggerHeartRateAlert(patient.getPatientId(), timestamp);
                    }
                    break;
                case "BloodPressure":
                    if (measurementValue > 180 || measurementValue < 90) {
                        triggerBloodPressureAlert(patient.getPatientId(), timestamp);
                    }
                    break;
            }
        }
    }

    private void triggerBloodPressureAlert(int patientId, long timestamp) {
        try {
            // Generate simulated blood pressure data
            double systolicPressure = generateRandomValue(80, 200);
            double diastolicPressure = generateRandomValue(60, 120);
    
            // Update queues with latest readings
            updateQueues(systolicPressure, diastolicPressure);
    
            // Check for trend alert
            if (checkTrendAlert(systolicQueue) || checkTrendAlert(diastolicQueue)) {
                // Trigger the trend alert
                outputStrategy.output(patientId, timestamp, "Trend Alert", "triggered");
            }
    
            // Check for critical threshold alert
            if (systolicPressure > 180 || systolicPressure < 90 || diastolicPressure > 120 || diastolicPressure < 60) {
                // Trigger the critical threshold alert
                outputStrategy.output(patientId, timestamp, "Critical Blood Pressure Alert", "triggered");
            }
    
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for the patient: " + patientId);
            e.printStackTrace();
        }
    }
      
      
}

