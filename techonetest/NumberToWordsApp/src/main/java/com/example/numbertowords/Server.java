package com.example.numbertowords;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Simple HTTP server for the Number to Words web application.
 * Serves an HTML page and converts numbers to words using NumberConverter.
 */
public class Server {
    private int port;
    private HttpServer server; // make server a class field

    /**
     * Constructor to create server with a specific port.
     * @param port Port number on which the server will listen.
     */
    public Server(int port) {
        this.port = port;
    }

    /**
     * Starts the HTTP server, sets up endpoints, and handles requests.
     */
    public void start() {
        try {
            // Create HTTP server on the specified port
            server = HttpServer.create(new InetSocketAddress(port), 0);

            // Context for serving the main HTML page
            server.createContext("/", (exchange -> {
                if ("GET".equals(exchange.getRequestMethod())) {
                    String path = "src/main/resources/index.html";
                    byte[] bytes = Files.readAllBytes(Paths.get(path));
                    exchange.sendResponseHeaders(200, bytes.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(bytes);
                    os.close();
                }
            }));

            // Context for handling number conversion requests
            server.createContext("/convert", (exchange -> {
                if ("POST".equals(exchange.getRequestMethod())) {
                    InputStream is = exchange.getRequestBody();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    // Extract input number from form data
                    String input = reader.readLine().split("=")[1];
                    input = input.replaceAll("%2E", "."); // decode URL-encoded decimal

                    // Convert number to words
                    String result = NumberConverter.convertToWords(input);

                    // Prepare HTML response
                    String response = "<html><body>"
                            + "<h2>Result:</h2>"
                            + "<p>" + result + "</p>"
                            + "<a href='/'>Back</a>"
                            + "</body></html>";

                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            }));

            // Start the server
            server.start();
            System.out.println("Server started at http://localhost:" + port);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the HTTP server.
     */
    public void stop() {
        if (server != null) {
            server.stop(0);
            System.out.println("Server stopped.");
        }
    }
}
