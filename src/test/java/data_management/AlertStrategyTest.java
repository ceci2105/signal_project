package data_management;



import org.junit.Test;
import static org.junit.Assert.*;
import com.alerts.AlertStrategy;
import com.alerts.BloodPressureStrategy;
import com.alerts.HealthMonitor;
import com.alerts.HeartRateStrategy;
import com.alerts.OxygenSaturationStrategy;

public class AlertStrategyTest {

    @Test
    public void testBloodPressureStrategy() {
        AlertStrategy strategy = new BloodPressureStrategy();
        assertTrue(strategy.checkAlert("Patient123", 150.0)); // above threshold
        assertFalse(strategy.checkAlert("Patient123", 120.0)); // below threshold
    }

    @Test
    public void testHeartRateStrategy() {
        AlertStrategy strategy = new HeartRateStrategy();
        assertTrue(strategy.checkAlert("Patient123", 55.0)); // below min heart rate
        assertTrue(strategy.checkAlert("Patient123", 105.0)); // above max heart rate
        assertFalse(strategy.checkAlert("Patient123", 75.0)); // normal heart rate
    }

    @Test
    public void testOxygenSaturationStrategy() {
        AlertStrategy strategy = new OxygenSaturationStrategy();
        assertTrue(strategy.checkAlert("Patient123", 88.0)); // below threshold
        assertFalse(strategy.checkAlert("Patient123", 95.0)); // above threshold
    }

    @Test
    public void testHealthMonitorWithDifferentStrategies() {
        String patientId = "Patient123";
        double bpReading = 150.0;
        double hrReading = 55.0;
        double oxygenReading = 88.0;

        HealthMonitor monitor = new HealthMonitor(new BloodPressureStrategy());
        assertTrue(monitor.checkAlert(patientId, bpReading));
        assertFalse(monitor.checkAlert(patientId, 120.0));

        monitor.setAlertStrategy(new HeartRateStrategy());
        assertTrue(monitor.checkAlert(patientId, hrReading));
        assertFalse(monitor.checkAlert(patientId, 75.0));

        monitor.setAlertStrategy(new OxygenSaturationStrategy());
        assertTrue(monitor.checkAlert(patientId, oxygenReading));
        assertFalse(monitor.checkAlert(patientId, 95.0));
    }
}

