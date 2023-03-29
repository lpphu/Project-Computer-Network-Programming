package com.project.View_UI;

import javax.swing.*;

import com.project.ConnectDB.ConnectDB;
import com.project.Model.Account;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.GraphicAttribute;
import java.sql.ResultSet;


public class SignIn extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = this.screenSize.width / 3;
    private int height = this.screenSize.height / 3 * 2;

    public SignIn() {
        // Lưu thông tin account

        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel title = new javax.swing.JLabel();

        title.setLocation(0, 100);
        title.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        title.setText("Sign In");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setForeground(new java.awt.Color(204, 204, 255));
        content.setBounds(width, height - 100, 0, 100);

        JLabel userName = new JLabel("Username");
        JLabel passWord = new JLabel("Password");
        JLabel NoAccount = new JLabel("No Account?");

        JButton SignUp = new JButton("Sign Up");
        SignUp.setBorder(null);
        SignUp.setBackground(new Color(255, 255, 255));
        SignUp.setFont(new Font("Segoe UI", 0, 18));
        SignUp.setPreferredSize(new Dimension(80, 40));
        SignUp.setForeground(new Color(224, 67, 28));
        SignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignUp formSignUp = new SignUp();
                formSignUp.setVisible(true);
                setVisible(false);
            }
        });

        JTextField userNameInput = new JTextField();
        JPasswordField passwordInput = new JPasswordField();
        userNameInput.setPreferredSize(new Dimension(width- 50, 40));
        passwordInput.setPreferredSize(new Dimension(width- 50, 40));
        
        JButton btnSignIn = new JButton();
        btnSignIn.setBackground(new Color(51, 153, 255));
        btnSignIn.setForeground(new Color(255, 255, 255));
        btnSignIn.setText("SIGN IN");
        btnSignIn.setBorder(null);
        btnSignIn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSignIn.setPreferredSize(new Dimension(200 , 40));
        btnSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println(passWord.getText());
                try {
                    Boolean flag = false;
                    ConnectDB conn = new ConnectDB();
                    // System.out.println(String(passwordInput.getPassword()));
                    ResultSet rs = conn.executeQuery("select * from account");
                    while(rs.next()){
                        if (rs.getString(2).equals(userNameInput.getText()) && rs.getString(3).equals(new String(passwordInput.getPassword()))){
                            flag = true;
                            break;
                        }
                    }
                    if (flag){
                        ChatRoom room = new ChatRoom(userNameInput.getText());
                        room.setVisible(true);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        JPanel empty = new JPanel();
        empty.setBackground(new Color(255, 255, 255));
        JPanel empty2 = new JPanel();
        empty2.setBackground(new Color(255, 255, 255));
        gbc.anchor = GridBagConstraints.CENTER;
        content.add(title, gbc);
        gbc.gridy++;
        content.add(empty2, gbc);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridy++;
        content.add(userName, gbc);
        gbc.gridy++;
        content.add(userNameInput, gbc);
        gbc.gridy++;
        content.add(passWord, gbc);
        gbc.gridy++;
        content.add(passwordInput, gbc);
        gbc.gridy ++;
        content.add(empty, gbc);
        gbc.weighty = 0;
        gbc.gridy ++;
        gbc.anchor = GridBagConstraints.CENTER;
        content.add(btnSignIn, gbc);
        gbc.gridy ++;
        gbc.anchor = GridBagConstraints.LINE_END;
        JPanel changeOption = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        changeOption.setBackground(new Color(255, 255, 255));
        changeOption.add(NoAccount, gbc2);
        gbc2.gridx = 1;
        changeOption.add(SignUp, gbc2);
        content.add(changeOption, gbc);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(content, BorderLayout.CENTER);
        
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(mainPanel);
    }        
}

