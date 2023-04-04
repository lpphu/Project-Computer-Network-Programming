package com.project.Views.ChatRoom;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.*;


public class ChatRoom extends JFrame implements WindowStateListener{
    private JPanel Screen;
    private Content content;
    private MenuLeft menuLeft;
   
    public ChatRoom(String username) {
        initComponents(username);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents(String username) {
        Screen = new JPanel();
        Dimension parentSize = Screen.getPreferredSize();
        System.out.println(parentSize);

        menuLeft = new MenuLeft(username);
        menuLeft.setMinimumSize(new Dimension(1000, 200));
        content = new Content(username);
        content.setMinimumSize(new Dimension(1000, 600));
        

        setSizeItem(1000, 600);
        Screen.add(menuLeft);
        Screen.add(content);
        this.add(Screen);
        // this.setPreferredSize(new Dimension(1000, 600));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowStateListener(null);
        this.addWindowStateListener(this);
        this.setResizable(false);

        this.setLayout(new GridLayout());
        this.setBackground(Color.white);
        handleResize();
        pack();
    }       
    public JPanel getScreen() {
        return this.Screen;
    }

    public void setScreen(JPanel screen) {
        Screen = screen;
    }       
    
    public void handleResize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Add component listener to handle window resizing
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                setSizeItem(getFrame().getSize().width, getFrame().getSize().height);
            }
        });
    
    }
    public void setSizeItem(int width, int height) {
        this.menuLeft.setPreferredSize(new Dimension( (int) (width * 0.3), (int) (height*1)));
        this.content.setPreferredSize(new Dimension( (int) (width * 0.7), (int) (height*1))) ;
    }
    public JFrame getFrame() {
        return this;
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        int newState = e.getNewState();
        if ((newState & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
            handleMaximized();
        }
    }
    private void handleMaximized() {
        setSizeItem(this.getSize().width, this.getSize().height);
        if (this.getExtendedState() == Frame.NORMAL) {
            // Khi JFrame bị thu nhỏ đi
            System.out.println("JFrame đã bị thu nhỏ đi.");
        } else if (this.getExtendedState() == Frame.MAXIMIZED_BOTH) {
            // Khi JFrame được phóng to
            System.out.println("JFrame đã được phóng to.");
            setSizeItem(this.getSize().width, this.getSize().height);

        }
    }
}
