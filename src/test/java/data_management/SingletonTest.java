package data_management;

import org.junit.Test;

import com.cardio_generator.HealthDataSimulator;

import static org.junit.Assert.*;

public class SingletonTest {

    @Test
    public void testHealthDataSimulatorSingleton() {
        HealthDataSimulator instance1 = HealthDataSimulator.getInstance();
        HealthDataSimulator instance2 = HealthDataSimulator.getInstance();

        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame(instance1, instance2); 
    }
}
