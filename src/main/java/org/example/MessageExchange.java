package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;
import java.util.Scanner;

public class MessageExchange {
    private NameService nameService;
    private int port;
    private DatagramSocket socket;
    public MessageExchange(NameService nameService, int port) {
        this.nameService = nameService;
        this.port = port;
        this.socket = socket;
    }
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("Enter the destination nickname:");
                String destinationNickname = scanner.next();
                System.out.println("Enter the message:");
                String message = scanner.next();
                int destinationPin = nameService.getPin(destinationNickname);

                // Assume the Name Service is running on localhost at port 9000
                InetAddress nameServiceAddress = InetAddress.getByName("localhost");
                int nameServicePort = 9000;

                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, nameServiceAddress, destinationPin);
                socket.send(sendPacket);

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData());
                System.out.println("Received message: " + receivedMessage);
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToDistributionList(List<String> destinationNicknames, String message) throws IOException {
        for (String destinationNickname : destinationNicknames) {
            int destinationPin = nameService.getPin(destinationNickname);
            InetAddress nameServiceAddress = InetAddress.getByName("localhost");
            int nameServicePort = 9000;

            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, nameServiceAddress, destinationPin);
            socket.send(sendPacket);
        }
    }
}

