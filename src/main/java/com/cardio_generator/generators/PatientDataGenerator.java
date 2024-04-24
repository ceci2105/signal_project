package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Interface for generating patient data.
 * Implementing classes are in control for generating specific types of patient data.
 */
public interface PatientDataGenerator {
    
    /**
     * Generates patient data for the specified patient ID and outputs it using the provided output strategy.
     * 
     * @param patientId The ID of the patient for whom data is generated.
     * @param outputStrategy The strategy for outputting the generated data.
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
