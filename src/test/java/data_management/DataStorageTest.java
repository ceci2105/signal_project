package data_management;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.data_management.DataReader;
import com.data_management.DataStorage;
import com.data_management.PatientRecord;

import java.util.List;

public class DataStorageTest {

    @Test
    public void testAddAndGetRecords() {
        // Mock the data reader
        DataReader reader = Mockito.mock(DataReader.class);

        // Initialize DataStorage with the mock reader
        DataStorage storage = new DataStorage(reader);

        // Add sample patient data
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        // Retrieve records
        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);

        // Assertions
        assertEquals(2, records.size()); // Check if two records are retrieved
        assertEquals(100.0, records.get(0).getMeasurementValue(), 0.001); // Validate first record
    }
}

