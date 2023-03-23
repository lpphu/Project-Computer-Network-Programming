// package src.test.java;

// import java.net.DatagramPacket;
// import java.net.DatagramSocket;
// import java.net.InetAddress;
// import java.net.SocketException;

// public class ThreadRecive extends Thread{
//     private DatagramSocket socket;
//     private boolean running;
//     private byte[] buf = new byte[256];
    
//     public ThreadRecive(){
//         socket = new DatagramSocket(3000);
//     }

//     public void run(){
//         running = true;

//         while(running){
//             DatagramPacket packet = new
//         }
//         DatagramSocket socket;
//         try {
//             socket = new DatagramSocket(3000, ip);
//             byte[] buff = new byte[1024*32];

//             DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length);
    
//             String str = new String(datagramPacket.getData(), 0, buff.length);
    
//             socket.close();
//         } catch (SocketException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }

        
//     }
// }
