package com.project.Core.ServerSide;
import com.project.Controllers.*;
import com.project.Core.ConnectDB;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;
import java.net.DatagramPacket;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.project.Component.*;

public class Server  {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
    private DatagramSocket serverSocket;
    private ArrayList<Integer> clients = new ArrayList<>();
    private int port;
    private static Server instance;
    private ArrayList<ClientEntity> clientAddressList = new ArrayList<>();
    private static int BLOCK_SIZE = 1024;

    public static Server getInstance() throws SocketException {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }
    

    public Server() throws SocketException {
        this(3000);
    }

    public Server(int port) throws SocketException {
        this.port = port;
    }

    public void runServer() throws ClassNotFoundException {
        try {
            System.out.println("Server start successfully at port " + port);
            this.serverSocket = new DatagramSocket(port);
            this.clientAddressList = new ArrayList<ClientEntity>();
            while(true) {
                System.out.println("server received running ....");
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                receiveData = new byte[1024];
                serverSocket.receive(receivePacket);
                
                // receive object in client
                Message mes ; 
                byte[] data = receivePacket.getData(); 
                mes = (Message) deserialize(data);
                if( mes.getType().trim().equals("FILE")) {
                    receiveFile();
                }else {
                    receiveAndLogMessage(receivePacket, mes);
                }
            }
            
        } catch (IOException e) {
            LOGGER.severe("Error while running server: " + e.getMessage());
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

    public void showClients() {
        System.out.println("Clients: ");
        for (ClientEntity cl : clientAddressList) {
            System.out.println("Client : " + cl.toString());
        }
    }      

   
    public static void main(String[] args) throws SocketException, ClassNotFoundException {
        Server sv = Server.getInstance();
        sv.runServer();
    }
    public boolean checkConnect() throws IOException, InterruptedException {
        // create UDP socket
        DatagramSocket socket = new DatagramSocket();

        // set IP address and port number for server
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int serverPort = 5000;

        // set time interval for client check (in milliseconds)
        int checkInterval = 10000;

        // loop indefinitely
        while (true) {
            // send a test message to the server
            byte[] message = "test message".getBytes();
            DatagramPacket packet = new DatagramPacket(message, message.length, serverAddress, serverPort);
            socket.send(packet);

            // wait for response from server
            byte[] buffer = new byte[4096];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.setSoTimeout(checkInterval);
            try {
                socket.receive(packet);
            } catch (SocketTimeoutException e) {
                // if no response received, assume client is disconnected
                System.out.println("Client disconnected");
                // perform any necessary clean-up or handling here
            } catch (IOException e) {
                e.printStackTrace();
            }

            // if response received, client is still connected
            if (packet.getLength() > 0) {
                System.out.println("Client connected");
            }

            // wait for the next check interval
            Thread.sleep(checkInterval);
        }
    }

    public void removeClientItem(int userId) {
        for (int i = 0; i < clientAddressList.size(); i++) {
            if (clientAddressList.get(i).getUserId() == userId) {
                clientAddressList.remove(i);
                break;
            }
        }
    }

    public ClientEntity findClientItem(int userId) {
        for (int i = 0; i < clientAddressList.size(); i++) {
            if (clientAddressList.get(i).getUserId() == userId) {
                return clientAddressList.get(i);
            }
        }
        return null;
    }
    private void receiveAndLogMessage(DatagramPacket receivePacket, Message mes) {
        // Lấy địa chỉ IP và cổng của client
        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();
        String clientIdentity = clientAddress.getHostAddress() + ":" + clientPort;
   
        // In thông tin về client lên console
        System.out.println("\n\nBegin Log ----------------------------------\n");
        if(mes.getMessage().equals("accept") == true) {
            LOGGER.info("Received message from " + clientIdentity + ": " + mes.toString());
            System.out.println("New client connected to server");
            // Kiểm tra xem client đã kết nối chưa
            if( !clients.contains(mes.getUserId()
            )) {
                clients.add(mes.getUserId());
                clientAddressList.add( new ClientEntity(clientAddress, clientPort, mes.getUserId(), 0));
            }
            clientAddressList.add( new ClientEntity(clientAddress, clientPort, mes.getUserId(), 0));
            showClients();
            for( ClientEntity client : clientAddressList ) {
                for( int id : clients) {
                    sendMessageToClient(client.getClientAddress()
                    , client.getPort(), new Message(id, "newClient", 1));
                }
            }
        } else if(mes.getMessage().equals("ConnectTo") ){
            ClientEntity cl = findClientItem(mes.getUserId());
            System.out.println(cl.toString());
            if(cl.getGroupIid().size() > 0 && mes.getGroupID() != 0) {
                cl.getGroupIid().add(mes.getGroupID());
            }else {
                removeClientItem(mes.getUserId());
            }
            sendMessageToClient(cl.getClientAddress()
            , cl.getPort(), new Message(mes.getKey(), "ConnectToClient", mes.getUserId(), mes.getGroupID()));
        }else if(mes.getGroupID() == 0){
            LOGGER.info("Received message from " + clientIdentity + ": " + mes.toString());
            System.out.println("Received message after decrypt from " + RailFence.decrypt(mes.getMessage(), mes.getKey()));
            sendMessageToClient(clientAddress, clientPort, mes);
        }else{
            LOGGER.info("Received message from " + clientIdentity + ": " + mes.toString());
            System.out.println("Received message after decrypt from " + RailFence.decrypt(mes.getMessage(), mes.getKey()));
            ClientEntity cl =  findClientItem(mes.getUserId());
            sendMessageToClient(cl.getClientAddress(), cl.getPort(), mes);
            sendMessageToClient(clientAddress, clientPort, mes);
        }
        System.out.println("End Log ----------------------------------\n\n");
    }
    private void sendMessageToClient(InetAddress clientAddress, int clientPort, Message mes) {
        try {
            byte[] sendData = serialize(mes);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        } catch (IOException e) {
            LOGGER.severe("Error while sending message to client: " + e.getMessage());
        }
    }
    public void receiveFile() throws IOException {
        byte[] buffer = new byte[1024]; // tăng kích thước buffer
        System.out.println("\n\nBegin Log ----------------------------------\n");
        System.out.println("Nhan file from client ne");
        // Nhận tên file từ client
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        serverSocket.receive(packet);
        String filename = new String(packet.getData(), 0, packet.getLength());
    
        // Gửi ACK để thông báo đã nhận được tên file
        byte[] ackBuffer = "ACK".getBytes();
        DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length, packet.getAddress(), packet.getPort());
        serverSocket.send(ackPacket);
    
        // Nhận nội dung file từ client
        String folderPath = "";
        System.out.println("Received file from client  : " + filename );
        String filePath = folderPath + filename;
        FileOutputStream fos = new FileOutputStream(filePath);
    
    
        while (true) {
            buffer = new byte[1024];
            DatagramPacket newPacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(newPacket);
            // Gửi ACK để thông báo đã nhận được gói tin dữ liệu
            ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length, newPacket.getAddress(), packet.getPort());
            serverSocket.send(ackPacket);

            if (new String(newPacket.getData()).trim().equals("END") || new String(newPacket.getData()).equals("END")) {
                System.out.println("File received successfully");
                System.out.println("End Log ----------------------------------\n\n");
                fos.close();
                return;
            }
    
            fos.write(newPacket.getData(), 0, newPacket.getLength());
        }
    }
    
        


    // nhanm file 2
    public void receiveFile2() throws IOException {
        // Create a UDP socket
        byte[] buffer = new byte[1024];
        System.out.println("Nhan file from client ");
        // Nhận tên file từ client
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        serverSocket.receive(packet);
        String filename = new String(packet.getData(), 0, packet.getLength());
    
        // Keep track of the packets received and their order
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int expectedSeqNum = 0;
    
        // Receive packets until we receive the end-of-file packet
        while (true) {
            buffer = new byte[BLOCK_SIZE + 4];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            System.out.println("check " + expectedSeqNum);
            try {
                // Set a timeout for the server socket
                serverSocket.setSoTimeout(1000);
                // Wait for a packet
                serverSocket.receive(datagramPacket);
                // If we get here, we have received a packet, so reset the timeout
                serverSocket.setSoTimeout(0);
            } catch (SocketTimeoutException e) {
                // If we time out waiting for a packet, check if we have received any packets
                // If not, continue waiting for packets
                if (expectedSeqNum == 0) {
                    continue;
                } else {
                    // If we have received packets before, but none in the last timeout period,
                    // assume that the transfer is complete and exit the loop
                    System.out.println("File received successfully");
                    break;
                }
            }
    
            // Extract the sequence number from the packet
            int seqNum = ByteBuffer.wrap(buffer, 0, 4).getInt();
            byte[] data = new byte[buffer.length - 4];
            System.arraycopy(buffer, 4, data, 0, data.length);
    
            // Check if we've already received this packet
            if (seqNum < expectedSeqNum) {
                byte[] ack = ByteBuffer.allocate(4).putInt(seqNum).array();
                DatagramPacket ackPacket = new DatagramPacket(ack, ack.length, datagramPacket.getAddress(), datagramPacket.getPort());
                serverSocket.send(ackPacket);
                continue;
            }
    
            // If we've received an out-of-order packet, store it in a buffer
            if (seqNum > expectedSeqNum) {
                outputStream.write(data);
                continue;
            }
    
            // Write the packet to the output stream
            outputStream.write(data);
            expectedSeqNum++;
    
            // Send an acknowledgement for the received packet
            byte[] ack = ByteBuffer.allocate(4).putInt(seqNum).array();
            DatagramPacket ackPacket = new DatagramPacket(ack, ack.length, datagramPacket.getAddress(), datagramPacket.getPort());
            serverSocket.send(ackPacket);
    
            // Check if this was the end-of-file packet
            if (seqNum == -1) {
                System.out.println("File received successfully");
                break;
            }
        }
    
        // Write the received data to the file
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        outputStream.writeTo(fileOutputStream);
        fileOutputStream.close();
    }
    
}



