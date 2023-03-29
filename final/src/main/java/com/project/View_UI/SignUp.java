package com.project.View_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.GraphicAttribute;


public class SignUp extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = this.screenSize.width / 3;
    private int height = this.screenSize.height / 3 * 2;

    public SignUp() {
        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel title = new javax.swing.JLabel();
        title.setLocation(0, 100);
        title.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        title.setText("Sign Up");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setForeground(new java.awt.Color(204, 204, 255));
        content.setBounds(width, height - 100, 0, 100);
        JLabel userName = new JLabel("Username");
        JLabel passWord = new JLabel("Password");
        JLabel haveAccount = new JLabel("Have Account ?");
        JLabel confirmPassword = new JLabel("Confirm Password");
        JTextField userNameInput = new JTextField();
        JPasswordField passwordInput = new JPasswordField();
        JPasswordField confirmPassInput= new JPasswordField();
        userNameInput.setPreferredSize(new Dimension(width- 50, 40));
        passwordInput.setPreferredSize(new Dimension(width- 50, 40));
        passwordInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPerformed(evt);
            }
        });
        confirmPassInput.setPreferredSize(new Dimension(width- 50, 40));
        confirmPassInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPerformed(evt);
            }
        });
        JButton signIn = new JButton("Sign In");
        signIn.setBorder(null);
        signIn.setBackground(new Color(255, 255, 255));
        signIn.setFont(new Font("Segoe UI", 0, 18));
        signIn.setPreferredSize(new Dimension(80, 40));
        signIn.setForeground(new Color(224, 67, 28));

        signIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignIn formSignIn = new SignIn();
                formSignIn.setVisible(true);
                setVisible(false);
            }
        });

        JButton btnSignUp = new JButton();
        btnSignUp.setBackground(new Color(51, 153, 255));
        btnSignUp.setForeground(new Color(255, 255, 255));
        btnSignUp.setText("SIGN UP");
        btnSignUp.setBorder(null);
        btnSignUp.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        btnSignUp.setPreferredSize(new Dimension(200 , 40));
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
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
        content.add(confirmPassword, gbc);
        gbc.gridy ++;
        content.add(confirmPassInput, gbc);
        gbc.gridy ++;
        content.add(empty, gbc);
        gbc.weighty = 0;
        gbc.gridy ++;
        gbc.anchor = GridBagConstraints.CENTER;
        content.add(btnSignUp, gbc);
        gbc.gridy ++;
        JPanel changeOption = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.LINE_END;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        changeOption.setBackground(new Color(255, 255, 255));
        changeOption.add(haveAccount, gbc2);
        gbc2.gridx = 1;
        changeOption.add(signIn, gbc2);
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

