import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * PingServer.java
 */

public class PingClient {
    public static void main(String[] args) throws Exception {
        // Check command line arguments
        if (args.length != 2) {
            System.out.println("Required arguments: host address and port");
            return;
        }
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);

        // Create a datagram socket for sending and receiving UDP packets
        // through the port specified on the command line.
        DatagramSocket clientSocket = new DatagramSocket();

        // Set timeout to 1000 milliseconds (1 second).
        clientSocket.setSoTimeout(1000);

        InetAddress IPAddress = InetAddress.getByName(serverName);

        for (int i = 0; i < 10; i++) {
            // Get the current time
            long currentTime = System.currentTimeMillis();

            // Format current time for display
            Date date = new Date(currentTime);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
            String formattedDate = sdf.format(date);

            // Create data and packet to send
            String sentence = "PING " + i + " " + currentTime;
            byte[] sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

            // Print the attempt with current time
            System.out.println("[" + formattedDate + "] Attempt #" + i + " Sent: " + sentence);

            // Send ping
            clientSocket.send(sendPacket);

         

            // Create a DatagramPacket for receiving packets.
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            try {
                // Try to receive the packet
                clientSocket.receive(receivePacket);

                // Calculate RTT
                long receivedTime = System.currentTimeMillis();
                String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                long sentTime = Long.parseLong(modifiedSentence.split(" ")[2]);
                long rtt = receivedTime - sentTime;

                // Print details including RTT
                System.out.println(
                        "[" + sdf.format(new Date(receivedTime)) + "] Received from server: " + modifiedSentence);
                System.out.println("RTT: " + rtt + " microseconds");
            } catch (SocketTimeoutException e) {
                // Print timeout message if we don't get a response
                System.out.println("Request timed out.");
            }
        }

        // Close the socket
        clientSocket.close();
    }
}
