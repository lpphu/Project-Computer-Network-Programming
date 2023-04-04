package com.project.Core;

import java.net.*;

public class Server {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        int lastPacketReceived = 0;

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData());
            int packetNumber = Integer.parseInt(sentence.substring(0, 1));

            if (packetNumber == lastPacketReceived + 1) {
                lastPacketReceived = packetNumber;
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            } else {
                System.out.println("Packet loss detected! Packet " + (lastPacketReceived + 1) + " was not received.");
            }
        }
    }
}
