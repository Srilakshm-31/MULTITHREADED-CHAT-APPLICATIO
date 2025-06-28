import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static Set<Socket> clientSockets = new HashSet<>();

    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                clientSockets.add(socket);
                System.out.println("New client connected: " + socket);

                new ClientHandler(socket).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader input;
        private PrintWriter output;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);

                String message;
                while ((message = input.readLine()) != null) {
                    System.out.println("Received: " + message);
                    broadcast(message, socket);
                }
            } catch (IOException ex) {
                System.out.println("Client disconnected: " + socket);
            } finally {
                try {
                    socket.close();
                    clientSockets.remove(socket);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void broadcast(String message, Socket senderSocket) {
            for (Socket clientSocket : clientSockets) {
                if (!clientSocket.equals(senderSocket)) {
                    try {
                        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                        writer.println(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
