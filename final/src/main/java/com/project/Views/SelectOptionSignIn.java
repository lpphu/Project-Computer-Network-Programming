/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.project.Views;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.project.Component.Account;
import com.project.Views.ChatRoom.ChatRoom;


public class SelectOptionSignIn extends javax.swing.JFrame {
    private Account account; 
    private boolean isAdmin;
    private JFrame frame;
    private JPanel screen;

    public JPanel getScreen() {
        return screen;
    }

    public void setScreen(JPanel screen) {
        this.screen = screen;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public SelectOptionSignIn() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        userBtn = new javax.swing.JButton();
        adminBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        userBtn.setText("User");
        userBtn.setBorderPainted(false);
        userBtn.setDefaultCapable(false);
        userBtn.setFocusPainted(false);
        userBtn.setFocusable(false);
        userBtn.setRolloverEnabled(false);
        userBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    handleUser(evt);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        adminBtn.setText("Admin");
        adminBtn.setBorderPainted(false);
        adminBtn.setDefaultCapable(false);
        adminBtn.setFocusPainted(false);
        adminBtn.setFocusable(false);
        adminBtn.setRequestFocusEnabled(false);
        adminBtn.setRolloverEnabled(false);
        adminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleAdmin(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("You want to sign in as ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(adminBtn)
                        .addGap(50, 50, 50)
                        .addComponent(userBtn)))
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminBtn)
                    .addComponent(userBtn))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = -17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel1, gridBagConstraints);

        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }                    

    private void handleUser(java.awt.event.ActionEvent evt) throws Exception {                                        
        if(account.getIsAdmin() == false)   {
            frame.setVisible(false);
            ChatRoom room = new ChatRoom(account.getUsername(), account.getUserId());
            System.out.println(account.toString());
            room.setVisible(true);
        }else {
            System.out.println("user not found");
            JOptionPane.showMessageDialog(null, "User not found", "title", JOptionPane.ERROR_MESSAGE);
        }
        this.setVisible(false);
    }                                       

    private void handleAdmin(java.awt.event.ActionEvent evt) {                                         
        if( account.getIsAdmin() == true ) {
            JPanel mainContent = new JPanel(new GridBagLayout());
            mainContent.setBackground(new Color(99,104,108));
            AdminPage adminPage = new AdminPage();
            mainContent.add(adminPage.getScreen());
            frame.remove(this.getScreen());
            frame.add(mainContent);
            frame.revalidate();
            frame.repaint();
        }else {
            System.out.println("Account not admin");
            JOptionPane.showMessageDialog(null, "Account not admin, please re enter your account");
        }
        this.setVisible(false);
    }                                 

    // Variables declaration - do not modify                     
    private javax.swing.JButton adminBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton userBtn;
    // End of variables declaration     
    
    
}
