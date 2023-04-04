package com.project.Core.ClientSide;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
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

    public void sendDataString(String message) throws IOException {
        try {
            this.sendData = message.getBytes();
            this.sendPacket = new DatagramPacket(this.sendData, this.sendData.length, IPAddress, port);
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

    public void receiveDataString() {
        try {
            this.receivePacket = new DatagramPacket(receiveData, receiveData.length);
            this.clientSocket.receive(this.receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("Received from server: " + modifiedSentence.trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getSendData() {
        return sendData;
    }
    public void setSendData(byte[] sendData) {
        this.sendData = sendData;
    }
    public static void main(String[] args) {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String sentence = "";
        while(true) {
            try {
                Client client = new Client();
                sentence = inFromUser.readLine();
                client.sendDataString(sentence);
                client.receiveDataString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
