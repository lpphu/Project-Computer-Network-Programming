package com.project.Views.ChatRoom;
import javax.swing.*;

public class Content extends JPanel {
    private JTextField inputSend;
    private JButton jButton2;
    private JButton jButton3;
    private JPanel Screen;
    private JPanel jPanel2;
    private JPanel jPanel3;

    public Content(String username) {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel3 = new JPanel();
        Screen = new JPanel();
        jButton3 = new JButton();
        jPanel2 = new JPanel();
        inputSend = new JTextField();
        jButton2 = new JButton();

        setBackground(new java.awt.Color(51, 153, 255));
        setDoubleBuffered(false);
        setEnabled(false);
        setFocusable(false);
        setOpaque(false);
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);
        setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(985, 400));

        Screen.setBackground(new java.awt.Color(255, 255, 255));
        Screen.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(51, 204, 255)));
        Screen.setLayout(new java.awt.GridBagLayout());

        jButton3.setIcon(new ImageIcon(getClass().getResource("option.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setDefaultCapable(false);
        jButton3.setEnabled(false);
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.setRequestFocusEnabled(false);
        jButton3.setRolloverEnabled(false);
        jButton3.setVerifyInputWhenFocusTarget(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });
        Screen.add(jButton3, new java.awt.GridBagConstraints());

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(inputSend, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(inputSend, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        Screen.add(jPanel2, new java.awt.GridBagConstraints());

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("Send");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jButton2.setDefaultCapable(false);
        jButton2.setFocusPainted(false);
        Screen.add(jButton2, new java.awt.GridBagConstraints());

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(Screen, GroupLayout.DEFAULT_SIZE, 1142, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(340, Short.MAX_VALUE)
                .addComponent(Screen, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }                    
    public JPanel getScreen() {
        return Screen;
    }

    public void setScreen(JPanel screen) {
        Screen = screen;
    }                       
               
}
