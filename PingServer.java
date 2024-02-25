import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/**
 * PingServer.java
 */
class PingServer {
    public static void main(String args[]) throws Exception {
        try (DatagramSocket serverSocket = new DatagramSocket(2014)) {
            byte[] receiveData = new byte[1024]; 

            // Processing loop
            while (true) {
                // Create a random number generator for use in packet loss simulation
                Random random = new Random();

                // Create a datagram packet to hold incoming UDP packet
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Get the client message
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress IPAddress = receivePacket.getAddress(); // Get client's IP
                int port = receivePacket.getPort(); // Get client's port #

                // Print out the client's IP address, port number, and the message
                System.out.println("Client's port # = " + port);
                System.out.println("Client's IP = " + IPAddress);
                System.out.println("Client's message = " + sentence);

                // Capitalize the message from the client
                String capitalizedSentence = sentence.toUpperCase();

                // Simulate the packet loss
                int rand = random.nextInt(10); // A random number in the range of 0 to 10

                // If rand is less than 4, we consider the packet lost and do not reply
                if (rand < 4) {
                    System.out.println("Reply not sent");
                    continue;
                }

                // Otherwise, the server responds
                byte[] sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
                System.out.println("Reply to the client sent");
            } // While loop
        }
    } // Main method
} // PingServer class
