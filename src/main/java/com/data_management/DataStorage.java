package com.data_management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.alerts.AlertGenerator;
import com.cardio_generator.outputs.WebSocketDataReader;

/**
 * Manages storage and retrieval of patient data within a healthcare monitoring system.
 * This class serves as a repository for all patient records, organized by patient IDs.
 */
public class DataStorage {
    private static DataStorage instance; // Single instance of DataStorage
    private static final Lock instanceLock = new ReentrantLock(); // Lock for thread-safe singleton initialization

    private Map<Integer, Patient> patientMap; // Stores patient objects indexed by their unique patient ID.
    private final Lock dataLock = new ReentrantLock(); // Lock for thread-safe data operations

    /**
     * Private constructor to prevent instantiation from outside.
     * @param reader 
     */
    public DataStorage(DataReader reader) {
        this.patientMap = new HashMap<>();
    }

    /**
     * Provides access to the single instance of DataStorage.
     *
     * @return the single instance of DataStorage
     */
    public static DataStorage getInstance() {
        if (instance == null) {
            instanceLock.lock();
            try {
                if (instance == null) {
                    instance = new DataStorage(null);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    /**
     * Adds or updates patient data in the storage.
     * If the patient does not exist, a new Patient object is created and added to the storage.
     * Otherwise, the new data is added to the existing patient's records.
     *
     * @param patientId        the unique identifier of the patient
     * @param measurementValue the value of the health metric being recorded
     * @param recordType       the type of record, e.g., "HeartRate", "BloodPressure"
     * @param timestamp        the time at which the measurement was taken, in milliseconds since the Unix epoch
     */
    public void addPatientData(int patientId, double measurementValue, String recordType, long timestamp) {
        dataLock.lock();
        try {
            Patient patient = patientMap.get(patientId);
            if (patient == null) {
                patient = new Patient(patientId);
                patientMap.put(patientId, patient);
            }
            // Check if the record already exists and update it if needed
            patient.addOrUpdateRecord(measurementValue, recordType, timestamp);
        } finally {
            dataLock.unlock();
        }
    }

    /**
     * Retrieves a list of PatientRecord objects for a specific patient, filtered by a time range.
     *
     * @param patientId the unique identifier of the patient whose records are to be retrieved
     * @param startTime the start of the time range, in milliseconds since the Unix epoch
     * @param endTime   the end of the time range, in milliseconds since the Unix epoch
     * @return a list of PatientRecord objects that fall within the specified time range
     */
    public List<PatientRecord> getRecords(int patientId, long startTime, long endTime) {
        Patient patient = patientMap.get(patientId);
        if (patient != null) {
            return patient.getRecords(startTime, endTime);
        }
        return new ArrayList<>(); // return an empty list if no patient is found
    }

    /**
     * Retrieves a collection of all patients stored in the data storage.
     *
     * @return a list of all patients
     */
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientMap.values());
    }

    /**
     * The main method for the DataStorage class.
     * Initializes the system, reads data into storage, and continuously monitors and evaluates patient data.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            DataStorage storage = DataStorage.getInstance();
            WebSocketDataReader dataReader = new WebSocketDataReader("ws://localhost:8080/data");

            dataReader.readData(storage);

            // Add shutdown hook to gracefully close the WebSocket connection
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    dataReader.stopReading();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));

            // Simulate application running
            Thread.sleep(60000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

