/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.project.Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 *
 * @author huuta
 */
public class Home extends JFrame {

    private JPanel Screen;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel4;
    private JPanel jPanel1;

    public Home() {
        this.setLayout(new BorderLayout());
        initComponents();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        Screen = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Screen.setMaximumSize(new java.awt.Dimension(1000, 500));
        Screen.setPreferredSize(getMaximumSize());
        Screen.setLayout(new java.awt.GridLayout());

        jPanel1.setLayout(null);

        jButton2.setBackground(new java.awt.Color(204, 0, 204));
        jButton2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 255, 255));
        jButton2.setText("Get Start?");
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setDefaultCapable(false);
        jButton2.setFocusable(false);
        jButton2.setOpaque(false);
        jButton2.setRequestFocusEnabled(false);
        jButton2.setVerifyInputWhenFocusTarget(false);
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(getScreen());
                SignIn formSignIn = new SignIn();
                frame.remove(getScreen());
                frame.add(formSignIn.getScreen());
                setScreen(formSignIn.getScreen());
                frame.revalidate();
                frame.repaint();
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(420, 270, 250, 40);

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 255, 204));
        jLabel1.setText("                          Welcome to chat app ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1100, 170);

        jLabel4.setBackground(new java.awt.Color(86, 44, 180));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("bg.png"))); // NOI18N
        jLabel4.setMaximumSize(new java.awt.Dimension(1000, 600));
        jLabel4.setPreferredSize(new java.awt.Dimension(1147, 637));
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 0, 1100, 620);

        Screen.add(jPanel1);

        getContentPane().add(Screen, java.awt.BorderLayout.CENTER);
        this.setMinimumSize(jLabel4.getSize());
        this.setMaximumSize(jLabel4.getSize());
        this.setPreferredSize(jLabel4.getSize());
        this.setResizable(false);
        this.setVisible(true);
        this.add(Screen);
        pack();
    }       
    public JFrame getMainFrame() {
        return this;
    }
    public JPanel getScreen() {
        return this.Screen;
    }

    public void setScreen(JPanel screen) {
        Screen = screen;
    }

    
             
}
