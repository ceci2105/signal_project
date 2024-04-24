package main.java.com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

public class AlertGenerator implements PatientDataGenerator {

    // Variable name follows camelCase convention
    public static final Random RANDOM_GENERATOR = new Random();

    // Variable name follows camelCase convention
    private boolean[] alertStates;  // false = resolved, true = pressed

    public AlertGenerator(int patientCount) {
        // Variable name follows camelCase convention
        alertStates = new boolean[patientCount + 1];
    }

    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            // Variable name follows camelCase convention
            if (alertStates[patientId]) { 
                // Variable name follows camelCase convention
                if (RANDOM_GENERATOR.nextDouble() < 0.9) {  // 90% chance to resolve
                    // Variable name follows camelCase convention
                    alertStates[patientId] = false; 
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                // Variable name follows camelCase convention 
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                // Variable name follows camelCase convention
                double p = -Math.expm1(-lambda);  // Probability of at least one alert in the period
                 // Variable name follows camelCase convention
                boolean alertTriggered = RANDOM_GENERATOR.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true; // Variable name follows camelCase convention
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
