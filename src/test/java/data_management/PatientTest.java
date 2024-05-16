import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.List;

public class PatientTest {

    @Test
    public void testGetRecordsWithinRange() {
        // This test ensures that the getRecords method correctly retrieves patient records
        // within a specified time range.

        Patient patient = new Patient(1);

        // Add records for the patient with specific timestamps
        patient.addRecord(70.5, "HeartRate", 1641427200000L); // 2022-01-06
        patient.addRecord(120.0, "BloodPressure", 1641513600000L); // 2022-01-07
        patient.addRecord(80.0, "HeartRate", 1641600000000L); // 2022-01-08

        // Retrieve records between 2022-01-06 and 2022-01-07
        List<PatientRecord> recordsInRange = patient.getRecords(1641427200000L, 1641513600000L);

        // Check if records are retrieved
        assertEquals(2, recordsInRange.size()); // Expecting two records within the range
    }

    @Test
    public void testGetRecordsOutsideRange() {
        // This test ensures that the getRecords method correctly handles cases where
        // no records fall within the specified time range.

        Patient patient = new Patient(1);

        // Add records for the patient with specific timestamps
        patient.addRecord(70.5, "HeartRate", 1641427200000L); // 2022-01-06
        patient.addRecord(120.0, "BloodPressure", 1641513600000L); // 2022-01-07
        patient.addRecord(80.0, "HeartRate", 1641600000000L); // 2022-01-08

        // Retrieve records between 2022-01-08 and 2022-01-09
        List<PatientRecord> recordsInRange = patient.getRecords(1641600000000L, 1641686400000L);

        // Check if no records are retrieved
        assertTrue(recordsInRange.isEmpty()); // Expecting no records within the range
    }
}

