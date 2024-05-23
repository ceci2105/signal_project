package data_management;


import org.junit.Test;
import static org.junit.Assert.*;
import com.alerts.Alert;
import com.alerts.PriorityAlertDecorator;
import com.alerts.RepeatedAlertDecorator;

public class AlertDecoratorTest {

    @Test
    public void testBasicAlert() {
        Alert alert = new Alert("Patient123", "High Blood Pressure", 1626871200000L);
        String details = alert.getDetails();
        assertEquals("Patient ID: Patient123, Condition: High Blood Pressure, Timestamp: 1626871200000", details);
    }

    @Test
    public void testRepeatedAlertDecorator() {
        Alert alert = new Alert("Patient123", "High Blood Pressure", 1626871200000L);
        Alert repeatedAlert = new RepeatedAlertDecorator(alert, 3, 1000);
       
        repeatedAlert.triggerAlert();
    }

    @Test
    public void testPriorityAlertDecorator() {
        Alert alert = new Alert("Patient123", "High Blood Pressure", 1626871200000L);
        Alert priorityAlert = new PriorityAlertDecorator(alert, "HIGH");
        String details = priorityAlert.getDetails();
        assertEquals("Priority: HIGH, Patient ID: Patient123, Condition: High Blood Pressure, Timestamp: 1626871200000", details);
        priorityAlert.triggerAlert();
    }

    @Test
    public void testCombinedDecorator() {
        Alert alert = new Alert("Patient123", "High Blood Pressure", 1626871200000L);
        Alert combinedAlert = new PriorityAlertDecorator(new RepeatedAlertDecorator(alert, 2, 1000), "CRITICAL");
        String details = combinedAlert.getDetails();
        assertEquals("Priority: CRITICAL, Patient ID: Patient123, Condition: High Blood Pressure, Timestamp: 1626871200000", details);
        combinedAlert.triggerAlert();
    }
}
