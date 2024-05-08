import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;

public class PatientTest {
    @Test
    public void testGetRecordsWithinRange() {
        
        Patient patient = new Patient(1);

        // Add records for the patient
        patient.addRecord(70.5, "HeartRate", 1641427200000L); 
        patient.addRecord(120.0, "BloodPressure", 1641513600000L); 
        patient.addRecord(80.0, "HeartRate", 1641600000000L); 

        // Retrieve records between 2022-01-06 and 2022-01-07
        List<PatientRecord> recordsInRange = patient.getRecords(1641427200000L, 1641513600000L);

        // Check if records are retrieved
        assertEquals(2, recordsInRange.size()); 
    }

    @Test
    public void testGetRecordsOutsideRange() {
       
        Patient patient = new Patient(1);

        // Add records for the patient
        patient.addRecord(70.5, "HeartRate", 1641427200000L); 
        patient.addRecord(120.0, "BloodPressure", 1641513600000L); 
        patient.addRecord(80.0, "HeartRate", 1641600000000L);

        // Retrieve records between 2022-01-08 and 2022-01-09
        List<PatientRecord> recordsInRange = patient.getRecords(1641600000000L, 1641686400000L);

        // Check if no records are retrieved
        assertTrue(recordsInRange.isEmpty()); 
    }
}

