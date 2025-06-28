import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost"; // use IP if on different device
        final int PORT = 12345;

        try (
            Socket socket = new Socket(SERVER_IP, PORT);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected to the chat server.");

            // Thread to read incoming messages
            Thread readerThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = input.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException ex) {
                    System.out.println("Disconnected from server.");
                }
            });

            readerThread.start();

            // Main thread to send messages
            while (true) {
                String message = scanner.nextLine();
                output.println(message);
            }

        } catch (IOException ex) {
            System.out.println("Unable to connect to server.");
            ex.printStackTrace();
        }
    }
}

