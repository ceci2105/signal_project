package data_management;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cardio_generator.generators.AlertGenerator;
import com.cardio_generator.outputs.OutputStrategy;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.Arrays;
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
        alertGenerator.bloodPressureAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Critical Threshold Alert", "triggered");
    }

    @Test
    public void testBloodSaturationAlert() {
        alertGenerator.bloodSaturationAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Low Saturation Alert", "triggered");
    }

    @Test
    public void testHypotensiveHypoxemiaAlert() {
        alertGenerator.hypotensiveHypoxemiaAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Hypotensive Hypoxemia Alert", "triggered");
    }

    @Test
    public void testECGAlert() {
        alertGenerator.ECGalert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Abnormal Heart Rate Alert", "triggered");
    }

    @Test
    public void testTriggerAlert() {
        alertGenerator.triggerAlert(1, outputStrategy);
        Mockito.verify(outputStrategy).output(1, Mockito.anyLong(), "Triggered Alert", "triggered");
    }

    @Test
    public void testEvaluateData() {
        PatientRecord heartRateRecord = new PatientRecord(1, "HeartRate", 55.0, System.currentTimeMillis());
        PatientRecord bloodPressureRecord = new PatientRecord(1, "BloodPressure", 200.0, System.currentTimeMillis());
        List<PatientRecord> records = Arrays.asList(heartRateRecord, bloodPressureRecord);

        Mockito.when(dataStorage.getRecords(Mockito.anyInt(), Mockito.anyLong(), Mockito.anyLong())).thenReturn(records);

        AlertGenerator alertGenerator = new AlertGenerator(1, dataStorage);

        alertGenerator.evaluateData(new Patient(1));

        Mockito.verify(outputStrategy).output(1, heartRateRecord.getTimestamp(), "Abnormal Heart Rate Alert", "triggered");
        Mockito.verify(outputStrategy).output(1, bloodPressureRecord.getTimestamp(), "Critical Threshold Alert", "triggered");
    }
}


