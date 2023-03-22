package src.test.java;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ThreadRecive extends Thread{
    protected String data;
    protected InetAddress ip;
    
    public ThreadRecive(String data, InetAddress ip){
        this.data = data;
        this.ip = ip;
    }

    @Override
    public void run(){
        DatagramSocket socket;
        try {
            socket = new DatagramSocket(3000, ip);
            byte[] buff = new byte[1024*32];

            DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length);
    
            String str = new String(datagramPacket.getData(), 0, buff.length);
    
            socket.close();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
}
