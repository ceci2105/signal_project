import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.alerts.AlertGenerator;
import com.cardio_generator.outputs.OutputStrategy;
import com.data_management.DataStorage;

import java.util.Random;
import java.util.List;
import java.util.Arrays;

public class AlertGeneratorTest {
    
    private AlertGenerator alertGenerator;
    private OutputStrategy outputStrategy;
    private DataStorage dataStorage;

    @Before
    public void setUp() {
        dataStorage = Mockito.mock(DataStorage.class);
        alertGenerator = new AlertGenerator(1, dataStorage);
        outputStrategy = Mockito.mock(OutputStrategy.class);
    }

    @Test
    public void testBloodPressureAlert() {
        // Test for critical threshold alert in blood pressure
        // This test ensures that the alert is triggered when blood pressure exceeds a critical threshold.
        alertGenerator.bloodPressureAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Critical Threshold Alert", "triggered");
    }

    @Test
    public void testBloodSaturationAlert() {
        // Test for low saturation alert
        // This test ensures that the alert is triggered when blood saturation falls below a certain level.
        alertGenerator.bloodSaturationAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Low Saturation Alert", "triggered");
    }

    @Test
    public void testHypotensiveHypoxemiaAlert() {
        // Test for hypotensive hypoxemia alert
        // This test ensures that the alert is triggered for hypotensive hypoxemia, a combination of low blood pressure and low oxygen levels.
        alertGenerator.hypotensiveHypoxemiaAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Hypotensive Hypoxemia Alert", "triggered");
    }

    @Test
    public void testECGAlert() {
        // Test for abnormal heart rate alert
        // This test ensures that the alert is triggered when an abnormal heart rate is detected.
        alertGenerator.ECGalert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Abnormal Heart Rate Alert", "triggered");
    }

    @Test
    public void testTriggerAlert() {
        // Test for generic triggered alert
        // This test ensures that a generic alert can be triggered and correctly outputted.
        alertGenerator.triggerAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Triggered Alert", "triggered");
    }

    @Test
    public void testEvaluateData() {
        // Test evaluation of patient data
        // This test ensures that the AlertGenerator evaluates patient data correctly and triggers the appropriate alerts.
        
        // Mock data storage to return specific patient records
        DataStorage dataStorage = Mockito.mock(DataStorage.class);
        PatientRecord heartRateRecord = new PatientRecord("HeartRate", 55.0, System.currentTimeMillis());
        PatientRecord bloodPressureRecord = new PatientRecord("BloodPressure", 200.0, System.currentTimeMillis());
        List<PatientRecord> records = Arrays.asList(heartRateRecord, bloodPressureRecord);
        
        Mockito.when(dataStorage.getRecords(Mockito.anyInt(), Mockito.anyLong(), Mockito.anyLong())).thenReturn(records);

        OutputStrategy outputStrategy = Mockito.mock(OutputStrategy.class);
        AlertGenerator alertGenerator = new AlertGenerator(1, dataStorage);

        // Evaluate data for a specific patient
        alertGenerator.evaluateData(new Patient(1));

        // Verify if the outputStrategy is called with the expected parameters for each alert
        Mockito.verify(outputStrategy).output(1, heartRateRecord.getTimestamp(), "Abnormal Heart Rate Alert", "triggered");
        Mockito.verify(outputStrategy).output(1, bloodPressureRecord.getTimestamp(), "Critical Threshold Alert", "triggered");
    }
}
