package com.project.Core.ClientSide;

import java.io.*;
import java.net.*;
import java.util.Scanner;


import com.project.Component.*;

public class Client {
    private static final int BLOCK_SIZE = 1024;

    private int port = 5000;
    private DatagramSocket clientSocket = new DatagramSocket();
    private InetAddress IPAddress =  InetAddress.getByName("localhost");
    private byte[] sendData;
    private byte[] receiveData;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;

    public Client() throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        this.receiveData = new byte[1024];
        this.port = 5000;

    }
    public Client(int port) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        this.receiveData = new byte[1024];
        this.port = port;
        
        this.receivePacket = new DatagramPacket(receiveData, receiveData.length);

    }
    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
    

    public void sendMessage(Message message) throws IOException {
        try {
            this.sendData = serialize(message); 
            this.sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            this.clientSocket.send(this.sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDataByte(byte[] message) throws IOException {
        try {
            this.sendPacket = new DatagramPacket(message, message.length, IPAddress, port);
            this.clientSocket.send(this.sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message receiveDataString() throws ClassNotFoundException {
        Message mes = null;
        try {
           
            this.receivePacket = new DatagramPacket(receiveData, receiveData.length);
            this.clientSocket.receive(this.receivePacket);
            byte[] data = receivePacket.getData(); 
            mes = (Message) deserialize(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mes;
    }

    public byte[] getSendData() {
        return sendData;
    }
    public void setSendData(byte[] sendData) {
        this.sendData = sendData;
    }
    public void sendFile(String fileName, DatagramSocket socket, InetAddress address, int port) throws IOException { // Đọc dữ liệu của file vào một mảng byte
        File file = new File(fileName);
        byte[] buffer = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(buffer);
        fis.close();   
        int size = buffer.length;
        int blocks = (int) Math.ceil(size / (double) BLOCK_SIZE);
    
        for (int i = 0; i < blocks; i++) {
            int offset = i * BLOCK_SIZE;
            int length = Math.min(BLOCK_SIZE, size - offset);
            byte[] packetData = new byte[length];
            System.arraycopy(buffer, offset, packetData, 0, length);
    
            DatagramPacket packet = new DatagramPacket(packetData, length, address, port);
            socket.send(packet);
        }
    }
    
}
