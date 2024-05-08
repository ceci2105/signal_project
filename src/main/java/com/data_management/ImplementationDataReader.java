package com.data_management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImplementationDataReader implements DataReader {
    private final Path directorypath;

    public ImplementationDataReader(Path directorypath) {
        this.directorypath = directorypath;
    }

    @Override
    public void readData(DataStorage datastorage) throws IOException {
        // Iterate through files in the directory
        Files.walk(directorypath)
             .filter(Files::isRegularFile)
             .forEach(file -> {
                 try {
                     parseFile(file, datastorage);
                 } catch (IOException e) {
                     e.printStackTrace(); // Or handle the exception 
                 }
             });
    }

    private void parseFile(Path file, DataStorage datastorage) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming data format in each line is: patientId, measurementValue, measurementType, timestamp
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try {
                        int patientId = Integer.parseInt(parts[0]);
                        double measurementValue = Double.parseDouble(parts[1]);
                        String measurementType = parts[2];
                        long timestamp = Long.parseLong(parts[3]);
                        // Add data to DataStorage
                        datastorage.addPatientData(patientId, measurementValue, measurementType, timestamp);
                    } catch (NumberFormatException e) {
                        // Handle parsing errors if any
        
                    }
                } else {
                    
                }
            }
        }
    }
}