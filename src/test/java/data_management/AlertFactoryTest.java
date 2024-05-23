package data_management;



import org.junit.Test;
import static org.junit.Assert.*;

import com.alerts.Alert;
import com.alerts.AlertFactory;
import com.alerts.BloodOxygenAlert;
import com.alerts.BloodOxygenAlertFactory;
import com.alerts.BloodPressureAlert;
import com.alerts.BloodPressureAlertFactory;
import com.alerts.ECGAlert;
import com.alerts.ECGAlertFactory;

public class AlertFactoryTest {

    @Test
    public void testBloodPressureAlertFactory() {
        AlertFactory factory = new BloodPressureAlertFactory();
        String patientId = "Patient123";
        String condition = "High Blood Pressure";
        long timestamp = System.currentTimeMillis();

        Alert alert = factory.createAlert(patientId, condition, timestamp);

        assertTrue(alert instanceof BloodPressureAlert);
        assertEquals(patientId, alert.getPatientId());
        assertEquals(condition, alert.getCondition());
        assertEquals(timestamp, alert.getTimestamp());
    }

    @Test
    public void testBloodOxygenAlertFactory() {
        AlertFactory factory = new BloodOxygenAlertFactory();
        String patientId = "Patient456";
        String condition = "Low Blood Oxygen";
        long timestamp = System.currentTimeMillis();

        Alert alert = factory.createAlert(patientId, condition, timestamp);

        assertTrue(alert instanceof BloodOxygenAlert);
        assertEquals(patientId, alert.getPatientId());
        assertEquals(condition, alert.getCondition());
        assertEquals(timestamp, alert.getTimestamp());
    }

    @Test
    public void testECGAlertFactory() {
        AlertFactory factory = new ECGAlertFactory();
        String patientId = "Patient789";
        String condition = "Irregular Heartbeat";
        long timestamp = System.currentTimeMillis();

        Alert alert = factory.createAlert(patientId, condition, timestamp);

        assertTrue(alert instanceof ECGAlert);
        assertEquals(patientId, alert.getPatientId());
        assertEquals(condition, alert.getCondition());
        assertEquals(timestamp, alert.getTimestamp());
    }
}

