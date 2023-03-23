package src.test.java;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws IOException{
        DatagramSocket socket = new DatagramSocket();

        InetAddress ip = InetAddress.getByName("localhost");

        String str = "xin chao";

        DatagramPacket packet = new DatagramPacket(str.getBytes(), str.length(), ip, 3000);

        socket.send(packet);

        socket.close();
    }
}
