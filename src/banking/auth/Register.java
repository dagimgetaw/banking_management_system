package banking.auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import banking.Main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Register extends JPanel {
    private static final Color PRIMARY_COLOR = new Color(39, 174, 96);
    private static final Color SECONDARY_COLOR = new Color(240, 240, 240);
    private static final Color ACCENT_COLOR = new Color(30, 130, 72);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    private static final Font INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font LINK_FONT = new Font("Segoe UI", Font.PLAIN, 12);

    public Register() {
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

        // Bank name and tagline
        JLabel bankName = new JLabel("GLOBAL BANK");
        bankName.setFont(new Font("Segoe UI", Font.BOLD, 24));
        bankName.setForeground(Color.WHITE);
        bankName.setBounds(0, 340, 400, 40);
        bankName.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(bankName);

        JLabel tagline = new JLabel("Secure. Reliable. Global.");
        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tagline.setForeground(new Color(200, 220, 255));
        tagline.setBounds(0, 380, 400, 30);
        tagline.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(tagline);

        // Right panel with registration form
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(400, 0, getWidth() - 400, getHeight());
        rightPanel.setBackground(Color.WHITE);
        add(rightPanel);

        JLabel title = new JLabel("Create Your Account");
        title.setFont(TITLE_FONT);
        title.setForeground(PRIMARY_COLOR);
        title.setBounds(100, 80, 300, 40);
        rightPanel.add(title);

        JLabel subtitle = new JLabel("Join our banking community today");
        subtitle.setFont(SUBTITLE_FONT);
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(100, 120, 300, 30);
        rightPanel.add(subtitle);

        int startY = 180;
        int gap = 60;

        JLabel firstNameLabel = new JLabel("FIRST NAME");
        firstNameLabel.setFont(LINK_FONT);
        firstNameLabel.setForeground(Color.DARK_GRAY);
        firstNameLabel.setBounds(100, startY, 300, 20);
        rightPanel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setFont(INPUT_FONT);
        firstNameField.setBounds(100, startY + 25, 300, 35);
        firstNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        rightPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("LAST NAME");
        lastNameLabel.setFont(LINK_FONT);
        lastNameLabel.setForeground(Color.DARK_GRAY);
        lastNameLabel.setBounds(100, startY + gap, 300, 20);
        rightPanel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setFont(INPUT_FONT);
        lastNameField.setBounds(100, startY + gap + 25, 300, 35);
        lastNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        rightPanel.add(lastNameField);

        JLabel emailLabel = new JLabel("EMAIL ADDRESS");
        emailLabel.setFont(LINK_FONT);
        emailLabel.setForeground(Color.DARK_GRAY);
        emailLabel.setBounds(100, startY + (gap * 2), 300, 20);
        rightPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setFont(INPUT_FONT);
        emailField.setBounds(100, startY + (gap * 2) + 25, 300, 35);
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        rightPanel.add(emailField);

        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setFont(LINK_FONT);
        passwordLabel.setForeground(Color.DARK_GRAY);
        passwordLabel.setBounds(100, startY + (gap * 3), 300, 20);
        rightPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(INPUT_FONT);
        passwordField.setBounds(100, startY + (gap * 3) + 25, 300, 35);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        rightPanel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("CONFIRM PASSWORD");
        confirmPasswordLabel.setFont(LINK_FONT);
        confirmPasswordLabel.setForeground(Color.DARK_GRAY);
        confirmPasswordLabel.setBounds(100, startY + (gap * 4), 300, 20);
        rightPanel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(INPUT_FONT);
        confirmPasswordField.setBounds(100, startY + (gap * 4) + 25, 300, 35);
        confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        rightPanel.add(confirmPasswordField);

        JCheckBox termsCheckbox = new JCheckBox("I agree to the Terms and Conditions");
        termsCheckbox.setFont(LINK_FONT);
        termsCheckbox.setForeground(Color.DARK_GRAY);
        termsCheckbox.setBackground(Color.WHITE);
        termsCheckbox.setBounds(100, startY + (gap * 5) + 20, 300, 20);
        rightPanel.add(termsCheckbox);

        JButton registerBtn = new JButton("CREATE ACCOUNT");
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerBtn.setBounds(100, startY + (gap * 6) + 20, 300, 45);
        registerBtn.setBackground(PRIMARY_COLOR);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setBorderPainted(false);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        registerBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                registerBtn.setBackground(ACCENT_COLOR);
            }

            public void mouseExited(MouseEvent evt) {
                registerBtn.setBackground(PRIMARY_COLOR);
            }
        });

        registerBtn.addActionListener(e -> handleRegister(
                firstNameField, lastNameField, emailField,
                passwordField, confirmPasswordField, termsCheckbox
        ));

        JLabel loginLink = new JLabel("Already have an account? Sign in");
        loginLink.setFont(LINK_FONT);
        loginLink.setForeground(PRIMARY_COLOR);
        loginLink.setBounds(100, startY + (gap * 7) + 20, 300, 20);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Main.mainFrame.setContentPane(new Login());
                Main.mainFrame.revalidate();
            }

            public void mouseEntered(MouseEvent e) {
                loginLink.setForeground(ACCENT_COLOR);
            }

            public void mouseExited(MouseEvent e) {
                loginLink.setForeground(PRIMARY_COLOR);
            }
        });

        JLabel securityMessage = new JLabel("<html><center>Your information is protected with bank-grade security measures.</center></html>");
        securityMessage.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        securityMessage.setForeground(Color.GRAY);
        securityMessage.setBounds(100, getHeight() - 100, 300, 40);
        rightPanel.add(securityMessage);

        rightPanel.add(registerBtn);
        rightPanel.add(loginLink);
    }

    private void handleRegister(JTextField firstNameField, JTextField lastNameField, JTextField emailField,
                                JPasswordField passwordField, JPasswordField confirmPasswordField, JCheckBox termsCheckbox) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Password Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!termsCheckbox.isSelected()) {
            JOptionPane.showMessageDialog(this, "You must agree to the Terms and Conditions.", "Agreement Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Try to register the user
        if (banking.db.Database.registerUser(firstName, lastName, email, password)) {
            JOptionPane.showMessageDialog(this,
                    "Account created successfully for " + firstName + " " + lastName + "!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            Main.mainFrame.setContentPane(new Login());
            Main.mainFrame.revalidate();
        } else {
            JOptionPane.showMessageDialog(this,
                    "An account with this email already exists.",
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
