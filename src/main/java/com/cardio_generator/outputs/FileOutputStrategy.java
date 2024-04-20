package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

// Renamed the class to follow CamelCase style convention and renamed to FileOutputStrategy
public class FileOutputStrategy implements OutputStrategy {

    // Renamed the variable to follow camelCase style convention and renamed to baseDirectory
    private String baseDirectory;

    // Used final modifier to declare the constant fileMap
    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();

    // Added a default constructor for the class
    public FileOutputStrategy() {}

    // Renamed the parameter to follow camelCase style convention and renamed to baseDirectory
    public FileOutputStrategy(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    // Added an @Override annotation
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Used the resolve method to build the base path
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            // Used System.err to print the error to the standard error console
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }

        // Used the computeIfAbsent method to get the file path
        String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());

        try (PrintWriter out = new PrintWriter(
                // Used the newBufferedWriter method to get a buffered writer
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            // Used the printf method to format the output string
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (IOException e) {
            // Used System.err to print the error to the standard error console
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}
