package com.example.numbertowords;

import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;

import static org.junit.jupiter.api.Assertions.*;

class ServerIntegrationTest {
    private static Server server;
    private static final int PORT = 8081; // use a different port for tests

    @BeforeAll
    static void setUp() {
        server = new Server(PORT);
        new Thread(server::start).start(); // run server in a separate thread
        try {
            Thread.sleep(1000); // wait for server to boot
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    void testConvertValidNumber() throws IOException {
        String response = sendPostRequest("123.45");
        assertTrue(response.contains("one hundred and twenty-three dollars and forty-five cents"));
    }

    @Test
    void testConvertSingleDollar() throws IOException {
        String response = sendPostRequest("1");
        assertTrue(response.contains("one dollar"));
    }

    @Test
    void testConvertInvalidInput() throws IOException {
        String response = sendPostRequest("abc");
        assertTrue(response.toLowerCase().contains("invalid input"));
    }

    @AfterAll
    static void tearDown() {
        server.stop(); // stop server gracefully
    }

    private static String sendPostRequest(String number) throws IOException {
        URL url = new URL("http://localhost:" + PORT + "/convert");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(("number=" + URLEncoder.encode(number, "UTF-8")).getBytes());
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
}
