package com.project.Core.ServerSide;

import java.io.IOException;
import java.net.*;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;

public class Server implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
    private DatagramSocket serverSocket;
    private Map<String, InetAddress> clients = new HashMap<>();
    private int port;

    public Server() throws SocketException {
        this(5000);
    }

    public Server(int port) throws SocketException {
        this.port = port;
    }

    public void run() {
        try  {
            this.serverSocket = new DatagramSocket(port)
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            while (true) {
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData());

                // Lấy địa chỉ IP và cổng của client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                String clientIdentity = clientAddress.getHostAddress() + ":" + clientPort;

                // Kiểm tra xem client đã kết nối chưa
                if (!clients.containsKey(clientIdentity)) {
                    clients.put(clientIdentity, clientAddress);
                    LOGGER.info("New client connected: " + clientIdentity);
                }

                // In thông tin về client lên console
                LOGGER.info("Received message from " + clientIdentity + ": " + message);

                byte[] sendData = ("Server: " + message).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            LOGGER.severe("Error while running server: " + e.getMessage());
        }
    }

    public void stopServer() {
        if (serverSocket != null ) {
            serverSocket.close();
            LOGGER.info("Server socket stopped successfully.");
        }
    }
}
