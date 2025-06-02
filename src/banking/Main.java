package banking;

import banking.auth.Login;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame mainFrame;

    public static void main(String[] args) {
        // Create main window
        mainFrame = new JFrame("Banking Management System");

        // Set full screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screenSize.width, screenSize.height);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null); // or you can use layout managers later
        mainFrame.setVisible(true);

        // Load login screen
        mainFrame.setContentPane(new Login());
        mainFrame.revalidate();
    }
}
