import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.alerts.AlertGenerator;
import com.cardio_generator.outputs.OutputStrategy;
import com.data_management.DataStorage;

import java.util.Random;
import java.util.List;

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
        // Test for critical threshold alert
        alertGenerator.bloodPressureAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Critical Threshold Alert", "triggered");
    }

    @Test
    public void testBloodSaturationAlert() {
        // Test for low saturation alert
        alertGenerator.bloodSaturationAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Low Saturation Alert", "triggered");
    }

    @Test
    public void testHypotensiveHypoxemiaAlert() {
        // Test for hypotensive hypoxemia alert
        alertGenerator.hypotensiveHypoxemiaAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Hypotensive Hypoxemia Alert", "triggered");
    }

    @Test
    public void testECGAlert() {
        // Test for abnormal heart rate alert
        alertGenerator.ECGalert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Abnormal Heart Rate Alert", "triggered");
    }

    @Test
    public void testTriggerAlert() {
        // Test for triggered alert
        alertGenerator.triggerAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Triggered Alert", "triggered");
    }

    @Test
    public void testEvaluateData() {
        // Mock DataStorage
        DataStorage dataStorage = Mockito.mock(DataStorage.class);
       
        PatientRecord heartRateRecord = new PatientRecord("HeartRate", 55.0, System.currentTimeMillis());
        PatientRecord bloodPressureRecord = new PatientRecord("BloodPressure", 200.0, System.currentTimeMillis());

        // Mock data storage to return relevant records
        List<PatientRecord> records = Arrays.asList(heartRateRecord, bloodPressureRecord);
        Mockito.when(dataStorage.getRecords(Mockito.anyInt(), Mockito.anyLong(), Mockito.anyLong())).thenReturn(records);

        OutputStrategy outputStrategy = Mockito.mock(OutputStrategy.class);
        AlertGenerator alertGenerator = new AlertGenerator(1, dataStorage);

        alertGenerator.evaluateData(new Patient(1)); 

        // Verify if the outputStrategy is called with the expected parameters
        Mockito.verify(outputStrategy).output(1, heartRateRecord.getTimestamp(), "Abnormal Heart Rate Alert", "triggered");
        Mockito.verify(outputStrategy).output(1, bloodPressureRecord.getTimestamp(), "Critical Threshold Alert", "triggered");

    }
}
