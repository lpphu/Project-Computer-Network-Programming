package src.test.java;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    boolean running;
    public static void main(String[] args) throws IOException {

        InetAddress ip = InetAddress.getByName("localhost");
        
        DatagramSocket socket = new DatagramSocket(3000, ip);

        byte[] buf = new byte[1024];

        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        socket.receive(packet);

        String str = new String(packet.getData(), 0, buf.length);

        System.out.println(str);

        socket.close();
    }
}
