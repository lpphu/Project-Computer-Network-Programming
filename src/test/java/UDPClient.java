package src.test.java;

import java.net.InetAddress;

public class UDPClient {
    private String data;
    private InetAddress inetAddress;
    
    public UDPClient(String data, InetAddress inetAddress){
        this.data = data;
        this.inetAddress = inetAddress;
    }

    public void sendMail(){
        ThreadSend t1 = new ThreadSend(data, inetAddress);
        t1.run();
    }


}
