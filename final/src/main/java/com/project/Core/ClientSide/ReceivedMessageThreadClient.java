package com.project.Core.ClientSide;

import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.project.Component.Message;
import com.project.Component.RailFence;
import com.project.Controllers.UserController;
import com.project.Views.OnlineUsersFrame;
import com.project.Views.RoomChatClientToClient;


public class ReceivedMessageThreadClient implements Runnable {
    private DatagramSocket socket;
    private JFrame main = new JFrame();
    private int userId;

    public ReceivedMessageThreadClient(DatagramSocket socket, JFrame main, int userId) {
        this.main = main;
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {
        Message mes;
        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                System.out.println("Client Nhan message received");
                socket.receive(packet);
                // String message = new String(, 0, packet.getLength());
                byte[] data = packet.getData(); 
                mes = (Message) deserialize(data);
                System.out.println(mes.toString());
                if( mes.getMessage().equals("newClient") != true) {
                    System.out.println("Client Start nhan du lieu");
                    System.out.println(mes.toString());
                    String messageFromClient = RailFence.decrypt(mes.getMessage(), mes.getKey());
                    ((RoomChatClientToClient) this.main).addMes(UserController.getNameUser(mes.getGroupID()) + " : " + messageFromClient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void handleConnectWithClient(int userId, int groupID) throws ClassNotFoundException, IOException {
        RoomChatClientToClient rom = new RoomChatClientToClient( userId, this.userId ,groupID , ((OnlineUsersFrame) this.main).getClient());
        rom.setVisible(true);
    }

    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
}
