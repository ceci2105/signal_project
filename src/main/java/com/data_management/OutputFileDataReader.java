import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Test;

public class OutputFileDataReader {

    @Test
    public void testReadData_NormalOperation() throws IOException {
        DataStorage dataStorage = mock(DataStorage.class);
        OutputFileDataReader reader = new OutputFileDataReader("test_data.txt");
        
        reader.readData(dataStorage);
        
        // Verify that dataStorage.storeData() was called for each line in the file
        verify(dataStorage).storeData("Test data line 1");
        verify(dataStorage).storeData("Test data line 2");
        // Add more verification for other lines if needed
    }

    @Test(expected = IOException.class)
    public void testReadData_ExceptionThrown() throws IOException {
        DataStorage dataStorage = mock(DataStorage.class);
        OutputFileDataReader reader = new OutputFileDataReader("non_existing_file.txt");
        
        reader.readData(dataStorage);
        // The test will pass if an IOException is thrown
    }
}
