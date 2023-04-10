import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.*;

public class ChatApp extends JFrame {

    private JTextField userText;
    private JTextArea chatWindow;
    private String serverIP;
    private int serverPort;
    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int clientPort;
    private Map<String, InetAddress> users;

    public ChatApp(String serverIP, int serverPort, int clientPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientPort = clientPort;
        this.users = new HashMap<String, InetAddress>();

        setTitle("ChatApp");
        userText = new JTextField();
        userText.setEditable(false);
        userText.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    sendMessage(event.getActionCommand());
                    userText.setText("");
                }
            }
        );
        add(userText, BorderLayout.SOUTH);

        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow));
        setSize(300, 150);
        setVisible(true);
    }

    public void start() {
        try {
            socket = new DatagramSocket(clientPort);
            serverAddress = InetAddress.getByName(serverIP);

            String joinMessage = "join " + clientPort;
            byte[] joinMessageBytes = joinMessage.getBytes();
            DatagramPacket joinPacket = new DatagramPacket(joinMessageBytes, joinMessageBytes.length, serverAddress, serverPort);
            socket.send(joinPacket);

            while (true) {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String message = new String(packet.getData()).trim();

                if (message.startsWith("users")) {
                    String[] tokens = message.split(" ");
                    for (int i = 1; i < tokens.length; i++) {
                        String[] user = tokens[i].split(":");
                        users.put(user[0], InetAddress.getByName(user[1]));
                    }
                } else {
                    chatWindow.append(message + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        try {
            String sendMessage = "message " + message;
            byte[] sendMessageBytes = sendMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendMessageBytes, sendMessageBytes.length, serverAddress, serverPort);
            socket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: ChatApp <serverIP> <serverPort> <clientPort>");
            System.exit(1);
        }

        String serverIP = args[0];
        int serverPort = Integer.parseInt(args[1]);
        int clientPort = Integer.parseInt(args[2]);

        ChatApp chatApp = new ChatApp(serverIP, serverPort, clientPort);
        chatApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatApp.start();
    }
}
