package com.data_management;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class DataReaderTest {

    @Test
    void testDataReading() throws IOException {
        // Prepare test data directory with sample data files
        // This test ensures that the ImplementationDataReader correctly reads valid data from multiple files into DataStorage.
        Path testDataDirectory = Files.createTempDirectory("testData");
        Path testFile1 = Paths.get(testDataDirectory.toString(), "testFile1.txt");
        Path testFile2 = Paths.get(testDataDirectory.toString(), "testFile2.txt");

        // Write test data to files
        Files.write(testFile1, "1,100.0,WhiteBloodCells,1714376789050\n2,200.0,RedBloodCells,1714376789051\n".getBytes());
        Files.write(testFile2, "3,300.0,WhiteBloodCells,1714376789052\n4,400.0,RedBloodCells,1714376789053\n".getBytes());

        DataStorage dataStorage = new DataStorage();
        ImplementationDataReader reader = new ImplementationDataReader(testDataDirectory);

        // Read data from files into DataStorage
        reader.readData(dataStorage);

        // Validate the data stored in DataStorage
        List<PatientRecord> records1 = dataStorage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(1, records1.size());
        assertEquals(100.0, records1.get(0).getMeasurementValue());

        List<PatientRecord> records2 = dataStorage.getRecords(2, 1714376789050L, 1714376789051L);
        assertEquals(1, records2.size());
        assertEquals(200.0, records2.get(0).getMeasurementValue());

        List<PatientRecord> records3 = dataStorage.getRecords(3, 1714376789052L, 1714376789053L);
        assertEquals(1, records3.size());
        assertEquals(300.0, records3.get(0).getMeasurementValue());

        List<PatientRecord> records4 = dataStorage.getRecords(4, 1714376789052L, 1714376789053L);
        assertEquals(1, records4.size());
        assertEquals(400.0, records4.get(0).getMeasurementValue());
    }

    @Test
    void testEmptyDirectory() throws IOException {
        // Prepare an empty test data directory
        // This test ensures that ImplementationDataReader handles an empty directory correctly and does not add any data to DataStorage.
        Path emptyDirectory = Files.createTempDirectory("emptyDir");

        DataStorage dataStorage = new DataStorage();
        ImplementationDataReader reader = new ImplementationDataReader(emptyDirectory);

        // Read data from the empty directory
        reader.readData(dataStorage);

        // Ensure no data is stored in DataStorage
        assertTrue(dataStorage.getAllRecords().isEmpty());
    }

    @Test
    void testInvalidData() throws IOException {
        // Prepare test data directory with invalid data
        // This test ensures that ImplementationDataReader can handle and skip invalid data entries.
        Path testDataDirectory = Files.createTempDirectory("invalidData");
        Path testFile = Paths.get(testDataDirectory.toString(), "testFile.txt");

        // Write invalid test data to file
        Files.write(testFile, "1,abc,WhiteBloodCells,1714376789050\n2,200.0,RedBloodCells,1714376789051\n".getBytes());

        DataStorage dataStorage = new DataStorage();
        ImplementationDataReader reader = new ImplementationDataReader(testDataDirectory);

        // Read data from the file containing invalid entries
        reader.readData(dataStorage);

        // Ensure only valid data is stored in DataStorage
        List<PatientRecord> records = dataStorage.getAllRecords();
        assertEquals(1, records.size());
        assertEquals(2, records.get(0).getPatientId());
        assertEquals(200.0, records.get(0).getMeasurementValue());
    }

    @Test
    void testMultipleFiles() throws IOException {
        // Prepare test data directory with multiple files
        // This test ensures that ImplementationDataReader correctly reads data from multiple files and stores it in DataStorage.
        Path testDataDirectory = Files.createTempDirectory("multipleFiles");
        Path testFile1 = Paths.get(testDataDirectory.toString(), "testFile1.txt");
        Path testFile2 = Paths.get(testDataDirectory.toString(), "testFile2.txt");

        // Write test data to files
        Files.write(testFile1, "1,100.0,WhiteBloodCells,1714376789050\n2,200.0,RedBloodCells,1714376789051\n".getBytes());
        Files.write(testFile2, "3,300.0,WhiteBloodCells,1714376789052\n4,400.0,RedBloodCells,1714376789053\n".getBytes());

        DataStorage dataStorage = new DataStorage();
        ImplementationDataReader reader = new ImplementationDataReader(testDataDirectory);

        // Read data from files into DataStorage
        reader.readData(dataStorage);

        // Validate the data stored in DataStorage
        List<PatientRecord> records1 = dataStorage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(1, records1.size());
        assertEquals(100.0, records1.get(0).getMeasurementValue());

        List<PatientRecord> records2 = dataStorage.getRecords(2, 1714376789050L, 1714376789051L);
        assertEquals(1, records2.size());
        assertEquals(200.0, records2.get(0).getMeasurementValue());

        List<PatientRecord> records3 = dataStorage.getRecords(3, 1714376789052L, 1714376789053L);
        assertEquals(1, records3.size());
        assertEquals(300.0, records3.get(0).getMeasurementValue());

        List<PatientRecord> records4 = dataStorage.getRecords(4, 1714376789052L, 1714376789053L);
        assertEquals(1, records4.size());
        assertEquals(400.0, records4.get(0).getMeasurementValue());
    }

    @Test
    void testEmptyFile() throws IOException {
        // Prepare test data directory with an empty file
        // This test ensures that ImplementationDataReader handles an empty file correctly and does not add any data to DataStorage.
        Path testDataDirectory = Files.createTempDirectory("emptyFile");
        Path testFile = Paths.get(testDataDirectory.toString(), "testFile.txt");

        // Write empty test data to file
        Files.write(testFile, "".getBytes());

        DataStorage dataStorage = new DataStorage();
        ImplementationDataReader reader = new ImplementationDataReader(testDataDirectory);

        // Read data from the empty file
        reader.readData(dataStorage);

        // Ensure no data is stored in DataStorage
        assertTrue(dataStorage.getAllRecords().isEmpty());
    }
}
