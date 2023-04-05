package com.project.Core.ServerSide;
import com.project.Controllers.*;
import com.project.Core.ConnectDB;
import com.project.Views.LogMessageFromClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;

import com.project.Component.*;

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
        // MesController controllerMes = new MesController();
        try  {
            this.serverSocket = new DatagramSocket(port);
            System.out.println("Server start successfully at port " + port);
            
            while (true) {
                byte[] receiveData = new byte[1024];;
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                receiveData = new byte[1024];
                serverSocket.receive(receivePacket);
                // receive object in client
                Message mes ;
                byte[] data = receivePacket.getData(); 
                mes = (Message) deserialize(data);

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
                LOGGER.info("Received message from " + clientIdentity + ": " + mes.toString());
                System.out.println("Received message after decrypt from " + RailFence.decrypt(mes.getMessage(), mes.getKey()));
                MesController.saveMessageIntoDB(mes);

                byte[] sendData = serialize(mes);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            LOGGER.severe("Error while running server: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    public void stopServer() {
        if (serverSocket != null ) {
            serverSocket.close();
            LOGGER.info("Server socket stopped successfully.");
        }
    }

    
}
