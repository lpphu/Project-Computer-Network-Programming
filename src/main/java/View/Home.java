package src.main.java.View;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Home {
    public Home() {
        // Create views swing UI components
        JFrame mainFrame = new JFrame("Project-Final"); // Tạo frame

        /* Tạo Menu Bar */
        /* Tạo Menu */
        JMenuBar menuBar = new JMenuBar();
        JMenu exchangeMenu = new JMenu("Information Exchange");
        JMenu algorithMenu = new JMenu("Algorithm Rail Fence");
        JMenu accountMenu = new JMenu("Account");

        /* Tạo các item */
        JMenuItem logInItem = new JMenuItem("Log in");
        
        JMenuItem signInItem = new JMenuItem("Sign in");
        

        





        // Create model
        logInItem.setMnemonic(KeyEvent.VK_N);
        logInItem.setActionCommand("Log in");
        signInItem.setMnemonic(KeyEvent.VK_N);
        signInItem.setActionCommand("Sign in");

        // Create controller

        // Set view layout
        mainFrame.setSize(500,200);
        mainFrame.setLayout(null);
        mainFrame.setMinimumSize(new Dimension(500,200));

        // Display it all in scrolling window and make the window appear
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        accountMenu.add(logInItem);
        accountMenu.add(signInItem);
        accountMenu.addSeparator();
        menuBar.add(exchangeMenu);
        menuBar.add(algorithMenu);
        menuBar.add(accountMenu);
        mainFrame.setJMenuBar(menuBar);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
