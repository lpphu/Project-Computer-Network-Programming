package com.project.Views;
import javax.swing.*;

import com.project.Component.Message;
import com.project.Controllers.MesController;
import com.project.Core.ClientSide.*;
import com.project.Core.ServerSide.Server;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

public class OnlineUsersFrame extends JFrame {
    private Client client;
    private JPanel gridPanel;
    private JLabel notification;
    private ArrayList<Integer> userIDs;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Integer> getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(ArrayList<Integer> userIDs) {
        this.userIDs = userIDs;
    }

    public JLabel getNotification() {
        return notification;
    }
    
    public void setNotification(JLabel notification) {
        this.notification = notification;
    }
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }

    public void setGridPanel(JPanel gridPanel) {
        this.gridPanel = gridPanel;
    }

    public OnlineUsersFrame( String username , int userId) throws IOException, ClassNotFoundException {
        this.userId = userId;
        this.userIDs = new ArrayList<Integer>();
        this.client = new Client();
        client.connectSuccessToServer(userId);
        ReceivedMessageThread rcMessageThread = new ReceivedMessageThread(client.getClientSocket(), this, userId);
        Thread receiverThread = new Thread(rcMessageThread);
        receiverThread.start();
        // Set title and size for the frame
        setTitle(username + "   -   Online Users");
        setSize(600, 600);
        
        // Create a JPanel for the title and add it to the top of the frame
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("User Online");
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        // Create a JPanel for the grid layout and add it to the center of the frame
        int gapSize = 14; // customize gap size here
        this.gridPanel = new JPanel(new GridLayout(6, 6, gapSize, gapSize));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(gapSize, gapSize, gapSize, gapSize));

        add(gridPanel, BorderLayout.CENTER);
        JPanel gridPanel3 = new JPanel(new GridBagLayout());
        notification = new JLabel("");
        notification.setPreferredSize(new Dimension(100, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        
        gridPanel3.add(notification, gbc);
        add(gridPanel3, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screenSize.getWidth() * 0.35);
        int height = (int) (screenSize.getHeight() * 0.25);
        this.setLocation( width , height);
        // Set the frame to be visible
    }

    public void addUserOnline(String username, int idUser) {
        JButton button = new JButton(username + " : " +idUser);
        gridPanel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int groupId = 0;
                if( MesController.getGroupId() != 0 ) {
                    groupId = MesController.getGroupId() + 1;
                }else {
                    groupId = 1;
                }
                try {
                    handleConnectWithClient(idUser, groupId);
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    client.sendMessage(new Message(idUser, "ConnectTo", userId, groupId));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
         });
        this.revalidate();
        this.repaint();   
    }

    public void handleConnectWithClient(int userId, int groupId) throws ClassNotFoundException, IOException {
        RoomChatClientToClient rom = new RoomChatClientToClient(userId, this.userId, groupId, client);
        rom.setVisible(true);
    }

    
    public void addNotification(String message ) {
        this.notification.setText(message);
        this.revalidate();
        this.repaint();   
    }
    public void addUserId(Integer userId) {
        this.userIDs.add(userId);
    } 
    public boolean containsUserId(Integer userId) {
        return this.userIDs.contains(userId);
    }

    // public void addMes(String string) {
    //     System.out.println("Frame online ....");
    // }
}
