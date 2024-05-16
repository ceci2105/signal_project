package com.cardio_generator.outputs;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.data_management.DataReader;
import com.data_management.DataStorage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * WebSocketDataReader is responsible for connecting to a WebSocket server
 * to receive real-time patient data and store it in the DataStorage.
 */
public class WebSocketDataReader implements DataReader {
    private WebSocketClient client;
    private DataStorage dataStorage;

    /**
     * Constructs a new WebSocketDataReader.
     * 
     * @param serverUri the URI of the WebSocket server to connect to
     * @throws URISyntaxException if the provided URI syntax is invalid
     */
    public WebSocketDataReader(String serverUri) throws URISyntaxException {
        client = new WebSocketClient(new URI(serverUri)) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                System.out.println("Connected to WebSocket server");
            }

            @Override
            public void onMessage(String message) {
                // Parse the incoming message and store it in the data storage
                // Assuming message format: "patientId,measurementValue,recordType,timestamp"
                String[] parts = message.split(",");
                if (parts.length == 4) {
                    try {
                        int patientId = Integer.parseInt(parts[0]);
                        double measurementValue = Double.parseDouble(parts[1]);
                        String recordType = parts[2];
                        long timestamp = Long.parseLong(parts[3]);

                        dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
                    } catch (NumberFormatException e) {
                        System.err.println("Failed to parse message: " + message);
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("Invalid message format: " + message);
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("Disconnected from WebSocket server: " + reason);
                // Attempt to reconnect after a short delay
                try {
                    Thread.sleep(5000);
                    client.reconnect();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception ex) {
                System.err.println("WebSocket error: " + ex.getMessage());
                ex.printStackTrace();
            }
        };
    }

    /**
     * Reads data from the WebSocket server and stores it in the data storage.
     * 
     * @param dataStorage the storage where data will be stored
     * @throws IOException if there is an error reading the data
     */
    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        this.dataStorage = dataStorage;
        client.connect();
    }

    /**
     * Stops reading data from the WebSocket server.
     * 
     * @throws IOException if there is an error stopping the data reading
     */
    @Override
    public void stopReading() throws IOException {
        client.close();
    }
}
