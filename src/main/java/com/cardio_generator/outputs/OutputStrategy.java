package com.cardio_generator.outputs;

/**
 * Interface for defining output strategies for patient data.
 * Implementing classes are in control for defining how patient data is outputted.
 */
public interface OutputStrategy {
    
    /**
     * Outputs patient data with the specified parameters.
     * 
     * @param patientId The ID of the patient associated with the data.
     * @param timestamp The timestamp indicating when the data was generated.
     * @param label The label or type of data that we can find in the output.
     * @param data The actual data that we can find in the output.
     */
    void output(int patientId, long timestamp, String label, String data);
}
