# MULTITHREADED-CHAT-APPLICATION
COMPANY NAME: CODE TECH IT SOLUTIONS

NAME: S.SRILAKSHMI

INTERN ID: CITS0D75

DOMAIN: JAVA PROGRAMMING

DURATION: 6 WEEKS

MENTOR : NEELA SANTHOSH KUMAR

DESCRIPTION OF MY TASK: Multithreaded Client-Server Chat Application
The objective of this task is to design and implement a Multithreaded Client-Server Chat Application using Java, which enables real-time communication between multiple clients through a centralized server. This application demonstrates the use of Java networking through sockets and multithreading to handle concurrent client connections. The purpose is to simulate a basic chat environment where users can communicate simultaneously in a terminal-based interface. This project is a foundational example of how real-time messaging apps like WhatsApp, Slack, or Discord operate at a basic level.

In this project, two major components are implemented: the Server and the Client. The Server is responsible for listening to incoming client connections, handling each client through a dedicated thread, and broadcasting messages received from any one client to all other connected clients. The Client program connects to the server, takes user input from the console, and displays messages received from the server. To ensure real-time communication, each client spawns a new thread dedicated to listening for server messages while the main thread waits for user input to send.

The server uses a ServerSocket to accept connections and maintains a list of connected client sockets. For each new client, the server creates a new ClientHandler thread, which keeps listening to the messages from that specific client. The server then uses a broadcast mechanism to send incoming messages to all other clients except the sender. The use of threads ensures that the server does not block while waiting for input from any one client.

The client uses a regular Socket to connect to the server on a specific IP address and port (in this case, localhost and port 12345). Once connected, the client starts two parallel operations: one for reading input from the user and sending it to the server, and another for constantly listening to messages from the server and displaying them. This non-blocking structure is achieved through multithreading.
