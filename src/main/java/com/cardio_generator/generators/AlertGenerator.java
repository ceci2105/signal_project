package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Generates alerts for patients based on randomized triggers.
 */
public class AlertGenerator implements PatientDataGenerator {

    // Variable name follows camelCase convention
    public static final Random randomGenerator = new Random();

    // Variable name follows camelCase convention
    private boolean[] alertStates; // false = resolved, true = pressed

    /**
     * Constructs an AlertGenerator with the specified number of patients.
     * Initializes the alert states for each patient.
     *
     * @param patientCount The number of patients for whom alerts will be generated.
     */
    public AlertGenerator(int patientCount) {
        // Variable name follows camelCase convention
        alertStates = new boolean[patientCount + 1];
    }

    /**
     * Generates alerts for the specified patient ID and outputs them using the provided output strategy.
     * 
     * @param patientId      The ID of the patient for whom alerts are generated.
     * @param outputStrategy The strategy for outputting the generated alerts.
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        
        try {
            if (alertStates[patientId]) {
                if (randomGenerator.nextDouble() < 0.9) {
                    alertStates[patientId] = false;
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                double lambda = 0.1;
                double p = -Math.expm1(-lambda);
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
