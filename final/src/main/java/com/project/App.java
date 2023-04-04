package com.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.project.Controllers.*;
import com.project.Views.*;

public class App {
    public static void main( String[] args )
    {
        // LoginPreferences login = new LoginPreferences();
        // System.out.println(login.getUsername());
        // SignUp signUp = new SignUp();
        Home home = new Home();
        home.setLocationRelativeTo(null);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
        
        // JFrame app = new JFrame();
        // app.setSize(1000, 600);
        // app.setLayout(new GridBagLayout());
        // app.add(home.getScreen());
    }
}
