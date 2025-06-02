package banking.profile;

import javax.swing.*;
import java.awt.*;
import banking.Main;
import banking.db.Database.UserData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User extends JPanel {
    private static final Color PRIMARY_COLOR = new Color(39, 174, 96); // #27AE60
    private static final Color DARK_GREEN = new Color(30, 130, 72); // #1E8248
    private static final Color WHITE = Color.WHITE;
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    private static final Font TEXT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);

    private UserData userData;
    private JLabel balanceLabel;

    public User(UserData userData) {
        this.userData = userData;
        setLayout(null);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setBackground(WHITE);
        initializeComponents();
    }

    private void initializeComponents() {
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, getWidth(), 80);
        headerPanel.setBackground(PRIMARY_COLOR);
        add(headerPanel);

        JLabel welcomeLabel = new JLabel("Welcome, " + userData.firstName + "!");
        welcomeLabel.setFont(TITLE_FONT);
        welcomeLabel.setForeground(WHITE);
        welcomeLabel.setBounds(50, 20, 400, 40);
        headerPanel.add(welcomeLabel);

        // Main Content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(50, 100, getWidth() - 100, getHeight() - 180);
        contentPanel.setBackground(WHITE);
        add(contentPanel);

        // Profile Information Panel
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(null);
        profilePanel.setBounds(0, 0, 400, 300);
        profilePanel.setBackground(new Color(240, 240, 240));
        profilePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
        contentPanel.add(profilePanel);

        // Profile Title
        JLabel profileTitle = new JLabel("Profile Information");
        profileTitle.setFont(SUBTITLE_FONT);
        profileTitle.setForeground(DARK_GREEN);
        profileTitle.setBounds(20, 20, 300, 30);
        profilePanel.add(profileTitle);

        // User Information
        int startY = 80;
        int gap = 40;

        addInfoLabel(profilePanel, "First Name:", userData.firstName, startY);
        addInfoLabel(profilePanel, "Last Name:", userData.lastName, startY + gap);
        addInfoLabel(profilePanel, "Email:", userData.email, startY + (gap * 2));

        // Balance Panel
        JPanel balancePanel = new JPanel();
        balancePanel.setLayout(null);
        balancePanel.setBounds(420, 0, 400, 150);
        balancePanel.setBackground(DARK_GREEN);
        balancePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(balancePanel);

        JLabel balanceTitle = new JLabel("Current Balance");
        balanceTitle.setFont(SUBTITLE_FONT);
        balanceTitle.setForeground(WHITE);
        balanceTitle.setBounds(20, 20, 200, 30);
        balancePanel.add(balanceTitle);

        balanceLabel = new JLabel(String.format("$%.2f", userData.balance));
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        balanceLabel.setForeground(WHITE);
        balanceLabel.setBounds(20, 60, 360, 40);
        balancePanel.add(balanceLabel);

        // Banking Operations Panel
        JPanel operationsPanel = new JPanel();
        operationsPanel.setLayout(null);
        operationsPanel.setBounds(0, 320, 820, 200);
        operationsPanel.setBackground(WHITE);
        operationsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                "Banking Operations",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                SUBTITLE_FONT,
                DARK_GREEN));
        contentPanel.add(operationsPanel);

        // Deposit Button
        JButton depositBtn = createOperationButton("Deposit", 20, 40);
        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = JOptionPane.showInputDialog(User.this, "Enter deposit amount:", "Deposit", JOptionPane.PLAIN_MESSAGE);
                try {
                    double depositAmount = Double.parseDouble(amount);
                    if (depositAmount > 0) {
                        userData.balance += depositAmount;
                        updateBalance();
                        if (!banking.db.Database.updateBalance(userData.email, userData.balance)) {
                            JOptionPane.showMessageDialog(User.this,
                                "Failed to update balance in database",
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                            userData.balance -= depositAmount;  // Revert the change
                            updateBalance();
                            return;
                        }
                        JOptionPane.showMessageDialog(User.this,
                                String.format("Successfully deposited $%.2f", depositAmount),
                                "Deposit Successful",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(User.this,
                                "Please enter a positive amount",
                                "Invalid Amount",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(User.this,
                            "Please enter a valid amount",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        operationsPanel.add(depositBtn);

        // Withdraw Button
        JButton withdrawBtn = createOperationButton("Withdraw", 170, 40);
        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = JOptionPane.showInputDialog(User.this, "Enter withdrawal amount:", "Withdraw", JOptionPane.PLAIN_MESSAGE);
                try {
                    double withdrawAmount = Double.parseDouble(amount);
                    if (withdrawAmount > 0) {
                        if (withdrawAmount <= userData.balance) {
                            userData.balance -= withdrawAmount;
                            updateBalance();
                            if (!banking.db.Database.updateBalance(userData.email, userData.balance)) {
                                JOptionPane.showMessageDialog(User.this,
                                    "Failed to update balance in database",
                                    "Database Error",
                                    JOptionPane.ERROR_MESSAGE);
                                userData.balance += withdrawAmount;  // Revert the change
                                updateBalance();
                                return;
                            }
                            JOptionPane.showMessageDialog(User.this,
                                    String.format("Successfully withdrew $%.2f", withdrawAmount),
                                    "Withdrawal Successful",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(User.this,
                                    "Insufficient funds for this withdrawal",
                                    "Insufficient Balance",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(User.this,
                                "Please enter a positive amount",
                                "Invalid Amount",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(User.this,
                            "Please enter a valid amount",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        operationsPanel.add(withdrawBtn);

        // Check Balance Button
        JButton checkBalanceBtn = createOperationButton("Check Balance", 320, 40);
        checkBalanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(User.this,
                        String.format("Your current balance is $%.2f", userData.balance),
                        "Account Balance",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        operationsPanel.add(checkBalanceBtn);

        // Transfer Button
        JButton transferBtn = createOperationButton("Transfer", 470, 40);
        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipientEmail = JOptionPane.showInputDialog(User.this, 
                    "Enter recipient's email:", "Transfer", JOptionPane.PLAIN_MESSAGE);
                
                if (recipientEmail == null || recipientEmail.trim().isEmpty()) {
                    return;
                }

                String amountStr = JOptionPane.showInputDialog(User.this, 
                    "Enter transfer amount:", "Transfer", JOptionPane.PLAIN_MESSAGE);
                
                try {
                    double transferAmount = Double.parseDouble(amountStr);
                    if (transferAmount <= 0) {
                        JOptionPane.showMessageDialog(User.this,
                            "Please enter a positive amount",
                            "Invalid Amount",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (transferAmount > userData.balance) {
                        JOptionPane.showMessageDialog(User.this,
                            "Insufficient funds for this transfer",
                            "Insufficient Balance",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Get recipient data
                    banking.db.Database.UserData recipientData = banking.db.Database.getUserByEmail(recipientEmail);
                    if (recipientData == null) {
                        JOptionPane.showMessageDialog(User.this,
                            "Recipient account not found",
                            "Transfer Error",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Perform transfer
                    userData.balance -= transferAmount;
                    recipientData.balance += transferAmount;

                    // Update both balances in database
                    if (!banking.db.Database.updateBalance(userData.email, userData.balance) ||
                        !banking.db.Database.updateBalance(recipientData.email, recipientData.balance)) {
                        // Revert changes if database update fails
                        userData.balance += transferAmount;
                        recipientData.balance -= transferAmount;
                        JOptionPane.showMessageDialog(User.this,
                            "Failed to complete transfer",
                            "Transfer Error",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    updateBalance();
                    JOptionPane.showMessageDialog(User.this,
                        String.format("Successfully transferred $%.2f to %s", 
                            transferAmount, recipientData.firstName),
                        "Transfer Successful",
                        JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(User.this,
                        "Please enter a valid amount",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        operationsPanel.add(transferBtn);

        // Logout Button
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(BUTTON_FONT);
        logoutBtn.setBounds(50, contentPanel.getHeight() - 60, 120, 40);  // Position relative to contentPanel
        logoutBtn.setBackground(DARK_GREEN);
        logoutBtn.setForeground(WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        logoutBtn.addActionListener(e -> {
            Main.mainFrame.setContentPane(new banking.auth.Login());
            Main.mainFrame.revalidate();
        });

        contentPanel.add(logoutBtn);  // Add to contentPanel instead of main panel
    }

    private JButton createOperationButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setBounds(x, y, 140, 40);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void addInfoLabel(JPanel panel, String label, String value, int y) {
        JLabel infoLabel = new JLabel(label);
        infoLabel.setFont(TEXT_FONT);
        infoLabel.setForeground(Color.DARK_GRAY);
        infoLabel.setBounds(20, y, 150, 30);
        panel.add(infoLabel);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(TEXT_FONT);
        valueLabel.setForeground(Color.BLACK);
        valueLabel.setBounds(180, y, 200, 30);
        panel.add(valueLabel);
    }

    private void updateBalance() {
        balanceLabel.setText(String.format("$%.2f", userData.balance));
    }
}