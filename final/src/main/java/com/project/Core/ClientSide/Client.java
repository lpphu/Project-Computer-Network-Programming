package com.project.Core.ClientSide;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

import com.project.Component.*;

public class Client {
    private static final int BLOCK_SIZE = 1024;
    private int port = 3000;
    private DatagramSocket clientSocket = new DatagramSocket();
    private InetAddress IPAddress =  InetAddress.getByName("localhost");
    private byte[] sendData;
    private byte[] receiveData;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private static final long serialVersionUID = 1L;
    private static final int BUFFER_SIZE = 1024;

    public Client() throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        this.receiveData = new byte[1024];
        this.port = 3000;
    }
    public Client(int port) throws IOException, ClassNotFoundException {
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

    public void connectSuccessToServer(int UserId ) {
        try {
            this.sendData = serialize(new Message(UserId, "accept", 5)); 
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
    public void sendFile(String fileName, File file) throws IOException {
        System.out.println(file);
        byte[] buffer = new byte[1024];
        buffer = fileName.getBytes(); // tăng kích thước buffer
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.IPAddress, this.port);
        clientSocket.send(packet);
        FileInputStream fis = new FileInputStream(file);
        byte[] fileBytes = new byte[BUFFER_SIZE];
        int bytesRead = 0;
        while ((bytesRead = fis.read(fileBytes)) != -1) {
            buffer = new byte[bytesRead];
            System.arraycopy(fileBytes, 0, buffer, 0, bytesRead);
            packet = new DatagramPacket(buffer, buffer.length, this.IPAddress, port);
            clientSocket.send(packet);
            // đợi phản hồi từ bên nhận
            byte[] ackBuffer = new byte[1024];
            DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);
            clientSocket.receive(ackPacket);
            String ackMsg = new String(ackPacket.getData()).trim();
            if (!ackMsg.equals("ACK")) {
                throw new IOException("Error sending file data.");
            }
        }
        fis.close();
        // Gửi gói tin END để đánh dấu việc gửi file kết thúc
        DatagramPacket endPacket = new DatagramPacket("END".getBytes(), "END".length(), this.IPAddress, this.port);
        clientSocket.send(endPacket);
        System.out.println("File sent successfully!");
        clientSocket.close();
        this.setClientSocket(new DatagramSocket());
        return ;
    }
    
    
    public DatagramSocket getClientSocket() {
        return clientSocket;
    }
    public void setClientSocket(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void sendFile2(String filename,File file, int targetPort) throws IOException {
        // Create a UDP socket
        clientSocket.setSoTimeout(1000);
        
        // Open the file and read its contents
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[fileInputStream.available()];
        fileInputStream.read(data);
        fileInputStream.close();
        
        // Calculate the total number of packets to send
        int numPackets = (int)Math.ceil((double)data.length / BLOCK_SIZE);
        
        // Send each packet with a sequence number
        for (int i = 0; i < numPackets; i++) {
            int start = i * BLOCK_SIZE;
            int end = Math.min(start + BLOCK_SIZE, data.length);
            byte[] packet = new byte[4 + end - start];
            System.arraycopy(data, start, packet, 4, end - start);
            byte[] seqNum = ByteBuffer.allocate(4).putInt(i).array();
            System.arraycopy(seqNum, 0, packet, 0, 4);
            DatagramPacket datagramPacket = new DatagramPacket(packet, packet.length, this.IPAddress, targetPort);
            clientSocket.send(datagramPacket);
        }
        
        // Send a final packet with sequence number -1 to indicate end of file
        byte[] finalPacket = new byte[4];
        ByteBuffer.wrap(finalPacket).putInt(-1);
        DatagramPacket datagramPacket = new DatagramPacket(finalPacket, finalPacket.length, this.IPAddress, targetPort);
        clientSocket.send(datagramPacket);
        System.out.println("File send successfully!!");
    }

    
    
    
}
