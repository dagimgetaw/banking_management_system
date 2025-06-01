package banking.auth;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import banking.Main;

public class Login extends JPanel {
    private static final Color PRIMARY_COLOR = new Color(39, 174, 96); // Darker blue for professionalism
    private static final Color SECONDARY_COLOR = new Color(240, 240, 240); // Light gray
    private static final Color ACCENT_COLOR = new Color(30, 130, 72); // Brighter blue for accents
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    private static final Font INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font LINK_FONT = new Font("Segoe UI", Font.PLAIN, 12);

    public Login() {
        setLayout(null);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setBackground(Color.WHITE);

        // Left panel with bank branding
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(0, 0, 400, getHeight());
        leftPanel.setBackground(PRIMARY_COLOR);
        add(leftPanel);

        // Bank logo in left panel
        try {
            BufferedImage img = ImageIO.read(new File("src/resource/logo.png"));
            Image scaledImage = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(scaledImage));
            logo.setBounds(100, 120, 200, 200);
            leftPanel.add(logo);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }

        // Bank name in left panel
        JLabel bankName = new JLabel("GLOBAL BANK");
        bankName.setFont(new Font("Segoe UI", Font.BOLD, 24));
        bankName.setForeground(Color.WHITE);
        bankName.setBounds(0, 340, 400, 40);
        bankName.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(bankName);

        // Tagline in left panel
        JLabel tagline = new JLabel("Secure. Reliable. Global.");
        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tagline.setForeground(new Color(200, 220, 255));
        tagline.setBounds(0, 380, 400, 30);
        tagline.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(tagline);

        // Right panel with login form
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(400, 0, getWidth() - 400, getHeight());
        rightPanel.setBackground(Color.WHITE);
        add(rightPanel);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome Back");
        welcomeLabel.setFont(TITLE_FONT);
        welcomeLabel.setForeground(PRIMARY_COLOR);
        welcomeLabel.setBounds(100, 100, 300, 40);
        rightPanel.add(welcomeLabel);

        JLabel subtitle = new JLabel("Please enter your credentials");
        subtitle.setFont(SUBTITLE_FONT);
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(100, 140, 300, 30);
        rightPanel.add(subtitle);

        // Email field with modern styling
        JLabel emailLabel = new JLabel("EMAIL ADDRESS");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        emailLabel.setForeground(Color.DARK_GRAY);
        emailLabel.setBounds(100, 200, 300, 20);
        rightPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setFont(INPUT_FONT);
        emailField.setBounds(100, 225, 300, 35);
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        rightPanel.add(emailField);

        // Password field with modern styling
        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        passwordLabel.setForeground(Color.DARK_GRAY);
        passwordLabel.setBounds(100, 280, 300, 20);
        rightPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(INPUT_FONT);
        passwordField.setBounds(100, 305, 300, 35);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        rightPanel.add(passwordField);


        // Login button with modern styling
        JButton loginBtn = new JButton("SIGN IN");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginBtn.setBounds(100, 380, 300, 45);
        loginBtn.setBackground(PRIMARY_COLOR);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(ACCENT_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(PRIMARY_COLOR);
            }
        });


        // Registration link
        JLabel registerLabel = new JLabel("Don't have an account? Register here");
        registerLabel.setFont(LINK_FONT);
        registerLabel.setForeground(PRIMARY_COLOR);
        registerLabel.setBounds(100, 480, 300, 20);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect for registration link
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Main.mainFrame.setContentPane(new Register());
                Main.mainFrame.revalidate();
            }

            public void mouseEntered(MouseEvent e) {
                registerLabel.setForeground(ACCENT_COLOR);
            }

            public void mouseExited(MouseEvent e) {
                registerLabel.setForeground(PRIMARY_COLOR);
            }
        });

        rightPanel.add(loginBtn);
        rightPanel.add(registerLabel);

        // Security message at bottom
        JLabel securityMessage = new JLabel("<html><center>Your security is our priority. This site uses 256-bit SSL encryption.</center></html>");
        securityMessage.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        securityMessage.setForeground(Color.GRAY);
        securityMessage.setBounds(100, getHeight() - 100, 300, 40);
        rightPanel.add(securityMessage);
    }
}