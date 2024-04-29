package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Outputs patient data to files.
 * Each label corresponds to a separate file where data is add.
 */
public class FileOutputStrategy implements OutputStrategy {

    private String baseDirectory;
    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();

    /**
     * Constructs a FileOutputStrategy with the specified base directory.
     *
     * @param baseDirectory The base directory where output files will be stored.
     */
    public FileOutputStrategy(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    /**
     * Outputs patient data to files based on the label.
     * Each label corresponds to a separate file where data is appended.
     *
     * @param patientId The ID of the patient associated with the data.
     * @param timestamp The timestamp indicating when the data was generated.
     * @param label     The label or type of data being outputted.
     * @param data      The actual data to be outputted.
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }

        String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());

        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (IOException e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}
