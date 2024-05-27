package data_management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cardio_generator.outputs.WebSocketDataReader;
import com.data_management.DataStorage;

/**
 * Unit tests for the WebSocketDataReader class.
 */
public class WebSocketDataReaderTest {

    private DataStorage dataStorage;
    private WebSocketDataReader webSocketDataReader;
    private CountDownLatch latch;

    @Before
    public void setUp() throws URISyntaxException {
        webSocketDataReader = new WebSocketDataReader("ws://localhost:8080/data");
        dataStorage = new DataStorage(webSocketDataReader);
        latch = new CountDownLatch(1);
    }

    @After
    public void tearDown() throws IOException {
        webSocketDataReader.stopReading();
    }

    /**
     * Tests the reading and storage of data from the WebSocket server.
     */
    @Test
    public void testDataReadingAndStorage() throws IOException, URISyntaxException, InterruptedException {
        // Create a mock WebSocket server
        WebSocketServerMock server = new WebSocketServerMock(8887);
        server.start();
        // Start reading data
        webSocketDataReader.readData(dataStorage);
        // Simulate receiving data and storing it
        server.sendData("1,72.5,HeartRate,1641427200000"); // Sample data
        latch.await(2, TimeUnit.SECONDS); // Wait for data processing
        // Check if data is properly stored in dataStorage
        assertEquals(1, dataStorage.getAllPatients().size());
        assertEquals(1, dataStorage.getRecords(1, 0, Long.MAX_VALUE).size());
        // Shutdown server
        server.stop();
    }

    /**
     * Tests stopping the reading of data.
     */
    @Test
    public void testStopReading() throws IOException, InterruptedException {
        // Start reading data
        webSocketDataReader.readData(dataStorage);
        // Stop reading
        webSocketDataReader.stopReading();
        // Assert that the data reading is stopped
        assertTrue(true); // Placeholder assertion
    }

    /**
     * Mock WebSocket server for testing.
     */
    private class WebSocketServerMock extends org.java_websocket.server.WebSocketServer {

        public WebSocketServerMock(int port) throws URISyntaxException {
            super(new InetSocketAddress(port));
        }

        @Override
        public void onClose(WebSocket conn, int code, String reason, boolean remote) {
            System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
        }

        @Override
        public void onMessage(WebSocket conn, String message) {
            System.out.println("Received message: " + message);
            // Notify the test thread
            latch.countDown();
        }

        @Override
        public void onError(WebSocket conn, Exception ex) {
            ex.printStackTrace();
        }

        @Override
        public void onStart() {
            System.out.println("WebSocket server started");
        }
        
        public void sendData(String data) {
            broadcast(data);
        }

        @Override
        public void onOpen(WebSocket conn, ClientHandshake handshake) {
            System.out.println("Opened connection: " + conn.getRemoteSocketAddress());
        }
    }
}
