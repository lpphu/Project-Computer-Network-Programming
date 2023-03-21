package src.test.java;


import javax.swing.SwingUtilities;

// import src.main.java.View.Main;

/**
 * @author ashraf_sarhan
 * 
 */

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    public static void createAndShowGUI() throws Exception {
        new Main();
    }
}
