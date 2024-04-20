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
            // Variable name follows camelCase convention
            if (alertStates[patientId]) {
                // Variable name follows camelCase convention
                if (randomGenerator.nextDouble() < 0.9) { // 90% chance to resolve
                    // Variable name follows camelCase convention
                    alertStates[patientId] = false;
                    // Output the resolved alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                // Variable name follows camelCase convention
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                // Variable name follows camelCase convention
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                // Variable name follows camelCase convention
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    // Variable name follows camelCase convention
                    alertStates[patientId] = true;
                    // Output the triggered alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
