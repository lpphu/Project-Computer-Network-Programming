package com.project.View_UI;

import javax.print.DocFlavor.URL;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.GraphicAttribute;

public class ChatRoom extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = this.screenSize.width / 3;
    private int height = this.screenSize.height / 3 * 2;

    public ChatRoom(String name) {
        JPanel main = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        // gbc.insets = new Insets(0, 0, 100, 0);
        main.add(cerateHeader(name), gbc);
        gbc.gridy++;
        main.add(createMainContent(), gbc);
        gbc.gridy++;
        main.add(createFooter(), gbc);
        this.setIconImage(Toolkit.getDefaultToolkit().createImage("Img/user.png"));
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(main);
    }

    public JPanel cerateHeader(String name) {
        JPanel header = new JPanel(new GridBagLayout());
        header.setPreferredSize(new Dimension(580, 60)); // Đặt kích thước cho header
        header.setBackground(new Color(243, 240, 242));
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;
        gbcHeader.anchor = GridBagConstraints.LINE_START;

        Image image = Toolkit.getDefaultToolkit().createImage("Img/user.png");
        JPanel img = new ImagePanel(image);
        img.setPreferredSize(new Dimension(50, 40));
        header.add(img, gbcHeader);

        JLabel nameUser = new JLabel(name);
        nameUser.setPreferredSize(new Dimension(400, 40));
        gbcHeader.gridx++;
        header.add(nameUser, gbcHeader);

        JButton signOut = new JButton("Sign Out");
        signOut.setPreferredSize(new Dimension(100, 40));
        signOut.setBorder(null);
        signOut.setBackground(new Color(243, 240, 242));
        signOut.setFont(new Font("Arial", Font.BOLD, 18));
        signOut.setForeground(new Color(243, 0, 0));
        gbcHeader.gridx++;
        header.add(signOut, gbcHeader);
        return header;
    }

    public JPanel createFooter() {
        JPanel Footer = new JPanel(new GridBagLayout());
        GridBagConstraints gbcFooter = new GridBagConstraints();
        gbcFooter.gridx = 0;
        gbcFooter.gridy = 0;
        Footer.setPreferredSize(new Dimension(580, 120));
        Footer.setBackground(new Color(243, 240, 242));

        gbcFooter.anchor = GridBagConstraints.LINE_END;

        // enter key input
        JPanel enterKey = new JPanel(new GridLayout(1, 2));
        // JLabel keyLabel = new JLabel("Enter key");
        // keyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        // keyLabel.setPreferredSize(new Dimension(40, 40));
        // enterKey.add(keyLabel);

        JTextField sendKeyInput = new JTextField();
        Border borderInput = BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(139, 119, 168));
        sendKeyInput.setBorder(borderInput);
        sendKeyInput.setBackground(new Color(139, 119, 168));
        sendKeyInput.setPreferredSize(new Dimension(100, 40));
        sendKeyInput.setFont(new Font("Arial", Font.BOLD, 18));
        sendKeyInput.setForeground(new Color(255, 255, 255));
        sendKeyInput.setMargin(new Insets(10, 10, 10, 10));
        sendKeyInput.setText("0");
        enterKey.add(sendKeyInput);
        Footer.add(enterKey, gbcFooter);

        gbcFooter.gridy++;
        gbcFooter.gridx = 0;

        // enter option type data send
        JPanel optionTypeDataSend = new JPanel(new GridLayout(1, 6));
        optionTypeDataSend.setPreferredSize(new Dimension(100, 40));
        JPanel sendImg = new ImagePanel(Toolkit.getDefaultToolkit().createImage("Img/img.png"));
        optionTypeDataSend.add(sendImg);
        JPanel sendAudio = new ImagePanel(Toolkit.getDefaultToolkit().createImage("Img/audio.png"));
        optionTypeDataSend.add(sendAudio);
        JPanel sendText = new ImagePanel(Toolkit.getDefaultToolkit().createImage("Img/text.png"));
        optionTypeDataSend.add(sendText);
        optionTypeDataSend.setBorder(null);
        Footer.add(optionTypeDataSend, gbcFooter);
        gbcFooter.gridx++;

        // enter text send

        JTextField sendInput = new JTextField();
        sendInput.setBorder(borderInput);
        sendInput.setBackground(new Color(139, 119, 168));
        sendInput.setPreferredSize(new Dimension(380, 40));
        sendInput.setFont(new Font("Arial", Font.BOLD, 18));
        sendInput.setForeground(new Color(255, 255, 255));
        sendInput.setMargin(new Insets(10, 10, 10, 10));
        Footer.add(sendInput, gbcFooter);
        gbcFooter.gridx++;

        JPanel empty = new JPanel();
        Footer.add(empty, gbcFooter);
        gbcFooter.gridx++;

        JButton sendBtn = new JButton("Send");
        sendBtn.setFont(new Font("Arial", Font.BOLD, 18));
        sendBtn.setForeground(new Color(255, 255, 255));
        sendBtn.setBackground(new Color(45, 109, 255));
        sendBtn.setPreferredSize(new Dimension(80, 40));
        Footer.add(sendBtn, gbcFooter);
        return Footer;
    }

    public JPanel createMainContent() {
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(580, 400));
        content.setBorder(null);
        Border border = BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(178, 171, 176));
        content.setBorder(border);
        return content;
    }

}
