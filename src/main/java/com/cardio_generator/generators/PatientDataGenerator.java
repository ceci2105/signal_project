package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;
import com.data_management.Patient;

public interface PatientDataGenerator {
    void generate(int patientId, OutputStrategy outputStrategy);
    void bloodPressureAlert(int patientId, OutputStrategy outputStrategy);
    void bloodSaturationAlert(int patientId, OutputStrategy outputStrategy);
    void hypotensiveHypoxemiaAlert(int patientId, OutputStrategy outputStrategy);
    void ECGalert(int patientId, OutputStrategy outputStrategy);
    void triggerAlert(int patientId, OutputStrategy outputStrategy);
    void evaluateData(Patient patient);
}
