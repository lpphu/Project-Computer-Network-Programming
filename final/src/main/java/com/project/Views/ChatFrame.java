package com.project.Views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class ChatFrame extends JFrame {

    private JPanel chatPanel;

    public ChatFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chat Frame");


        // main rôm chat
        chatPanel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chat Panel");

        chatPanel = new JPanel();
        chatPanel.setLayout(null);
        chatPanel.setPreferredSize(new Dimension(400, 400));

        JScrollPane scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);

        addMessage("Hello", true); // add default message (on bottom right)

        int x = 10;
        int y = 10;

        for (int i = 1; i <= 9; i++) {
            addMessage("Message " + i, false, x, y); // add new message
            x += 100;
            y += 30;
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        add(chatPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addMessage(String message, boolean isDefault) {
        JLabel label = new JLabel(message);
        label.setOpaque(true);
        if (isDefault) {
            label.setBounds(chatPanel.getWidth() - 100, chatPanel.getHeight() - 30, 90, 20);
        } else {
            label.setBounds(10, 10, 90, 20);
        }
        chatPanel.add(label);
    }

    private void addMessage(String message, boolean isDefault, int x, int y) {
        JLabel label = new JLabel(message);
        label.setOpaque(true);
        if (isDefault) {
            label.setBounds(chatPanel.getWidth() - 100, chatPanel.getHeight() - 30, 90, 20);
        } else {
            label.setBounds(x, y, 90, 20);
        }
        chatPanel.add(label, 0);
    }

    public static void main(String[] args) {
        new ChatFrame();
    }
}


