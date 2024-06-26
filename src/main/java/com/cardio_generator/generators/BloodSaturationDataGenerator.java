package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;
import com.data_management.Patient;

/**
 * Generates simulated blood saturation data for patients.
 */
public class BloodSaturationDataGenerator implements PatientDataGenerator {

    private static final Random random = new Random();
    private int[] lastSaturationValues;

    /**
     * Constructs a BloodSaturationDataGenerator with the specified number of patients.
     * Initializes with base saturation values for each patient.
     *
     * @param patientCount The number of patients for whom data will be generated.
     */
    public BloodSaturationDataGenerator(int patientCount) {
        lastSaturationValues = new int[patientCount + 1];

        
        for (int i = 1; i <= patientCount; i++) {
            lastSaturationValues[i] = 95 + random.nextInt(6); 
        }
    }

    /**
     * Generates blood saturation data for the specified patient ID and outputs it using the provided output strategy.
     *
     * @param patientId      The ID of the patient for whom data is generated.
     * @param outputStrategy The strategy for outputting the generated data.
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            // Simulate blood saturation values
            int variation = random.nextInt(3) - 1; // -1, 0, or 1 to simulate small fluctuations
            int newSaturationValue = lastSaturationValues[patientId] + variation;

            // Ensure the saturation stays within a realistic and healthy range
            newSaturationValue = Math.min(Math.max(newSaturationValue, 90), 100);
            lastSaturationValues[patientId] = newSaturationValue;
            outputStrategy.output(patientId, System.currentTimeMillis(), "Saturation",
                    Double.toString(newSaturationValue) + "%");
        } catch (Exception e) {
            System.err.println("An error occurred while generating blood saturation data for patient " + patientId);
            e.printStackTrace(); 
        }
    }

    @Override
    public void bloodPressureAlert(int patientId, OutputStrategy outputStrategy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bloodPressureAlert'");
    }

    @Override
    public void bloodSaturationAlert(int patientId, OutputStrategy outputStrategy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bloodSaturationAlert'");
    }

    @Override
    public void hypotensiveHypoxemiaAlert(int patientId, OutputStrategy outputStrategy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hypotensiveHypoxemiaAlert'");
    }

    @Override
    public void ECGalert(int patientId, OutputStrategy outputStrategy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ECGalert'");
    }

    @Override
    public void triggerAlert(int patientId, OutputStrategy outputStrategy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'triggerAlert'");
    }

    @Override
    public void evaluateData(Patient patient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'evaluateData'");
    }
}
