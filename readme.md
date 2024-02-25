# UDP Ping Client/Server Program

## Overview

This project implements a basic ping program using the User Datagram Protocol (UDP), an alternative to the standard ICMP (Internet Control Message Protocol) ping utility. Unlike ICMP, UDP is used by applications that require a fast, connectionless transport mechanism, making it suitable for simulating the ping utility in a simplified manner. This program consists of two main components: a Ping Server and a Ping Client. The Ping Server responds to incoming UDP packets by sending them back to the client, while the Ping Client sends packets to the server, measures round-trip times (RTTs), and detects packet loss.

## Features

- **UDP-Based Communication**: Utilizes UDP for sending and receiving ping messages, demonstrating the protocol's connectionless nature.
- **Packet Loss Simulation**: The server randomly simulates packet loss to mimic real-world UDP behavior, where packets can be lost in transit.
- **RTT Measurement**: The client measures and displays the RTT for each ping message that successfully returns from the server.
- **Timeout Handling**: The client implements a timeout mechanism, marking a request as lost if no response is received within a specified time frame.

## Requirements

- Java Development Kit (JDK) 8 or later.

## Setup

1. **Clone the repository or download the source code**:
   Ensure you have the `PingClient.java` and `PingServer.java` files on your local machine.

2. **Compile the Programs**:
   Open a terminal in the directory containing the source code and compile both programs using the Java compiler (`javac`).

   Compile the server:
   ```
   javac PingServer.java
   ```

   Compile the client:
   ```
   javac PingClient.java
   ```

3. **Run the Ping Server**:
   Start the Ping Server on a specified port. For example, to run the server on port 2014, use the following command:
   ```
   java PingServer
   ```
   The server will now listen for incoming UDP ping requests.

4. **Run the Ping Client**:
   In a separate terminal window, run the Ping Client, specifying the host address (or `localhost` if running on the same machine) and the port number. For example:
   ```
   java PingClient localhost 2014
   ```
   The client will send a series of ping requests to the server and display the RTT or timeout for each.

## How It Works

- **Ping Client**: Sends 10 UDP packets to the server, each containing a message with a sequence number and timestamp. After sending each packet, the client waits up to one second for a reply. If a reply is received, the client calculates and displays the RTT. If no reply is received within the timeout period, the client marks the request as lost.
- **Ping Server**: Listens for incoming UDP packets. Upon receiving a packet, the server decides (based on a random number generator) whether to simulate packet loss. If simulating packet loss, the server does not respond. Otherwise, it sends the received packet back to the client.

## Notes

- This program is a simplified simulation of the ping utility and is intended for educational purposes. It does not measure the ping between different machines over a network but rather demonstrates the principles of UDP communication, packet loss simulation, and RTT calculation.
- The packet loss rate and response behavior are artificially introduced for demonstration purposes and do not reflect the reliability of your actual network.

## License

This project is open-source and available under the [MIT License](LICENSE).