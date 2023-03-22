package src.test.java;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ThreadSend extends Thread{
    protected String data;
    protected InetAddress ip;
    
    public ThreadSend(String data, InetAddress ip){
        this.data = data;
        this.ip = ip;
    }

    @Override
    public void run(){
        try {
            
            DatagramSocket datagramSocket = new DatagramSocket();

            DatagramPacket datagramPacket = new DatagramPacket(data.getBytes(), data.length(), ip, 3000);

            datagramSocket.send(datagramPacket);

            datagramSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
