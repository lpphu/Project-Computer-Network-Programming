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
        // Home home = new Home();
        SignIn home = new SignIn();
        home.setLocationRelativeTo(null);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
        
    }
}
