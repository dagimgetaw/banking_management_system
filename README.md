# Banking Management System

A modern, secure, and user-friendly banking management system built with Java Swing. This application provides a complete banking experience with features like account management, fund transfers, deposits, and withdrawals.

## Features

### User Authentication
- Secure login system
- User registration with validation
- Password protection
- Email-based authentication

### Account Management
- View account information
- Check current balance
- Update personal information
- Secure logout functionality

### Banking Operations
- Deposit funds
- Withdraw funds
- Transfer money to other accounts
- Real-time balance updates
- Transaction validation

### Security Features
- Password encryption
- Input validation
- Error handling
- Database persistence
- Secure session management

## Technical Details

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, NetBeans)

### Project Structure
```
banking_management_system/
├── src/
│   ├── banking/
│   │   ├── auth/
│   │   │   ├── Login.java
│   │   │   └── Register.java
│   │   ├── db/
│   │   │   ├── Database.java
│   │   │   └── users.txt
│   │   ├── banking.profile/
│   │   │   ├── User.java
│   │   └── Main.java
│   └── resource/
│       └── logo.png
└── README.md
```

### Database
- Simple file-based database system
- User data stored in `users.txt`
- Data format: `firstName|lastName|email|password|balance`

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/dagimgetaw/banking_management_system.git
```

2. Open the project in your preferred Java IDE

3. Compile and run the `Main.java` file

4. The application will start with the login screen

## Usage

### Registration
1. Click "Register here" on the login screen
2. Fill in your personal information
3. Create a password
4. Accept the terms and conditions
5. Click "CREATE ACCOUNT"

### Login
1. Enter your email address
2. Enter your password
3. Click "SIGN IN"

### Banking Operations
- **Deposit**: Click "Deposit" and enter the amount
- **Withdraw**: Click "Withdraw" and enter the amount
- **Transfer**: Click "Transfer", enter recipient's email and amount
- **Check Balance**: Click "Check Balance" to view current balance

## Security Considerations

- All passwords are stored securely
- Input validation prevents malicious entries
- Database operations are atomic
- Session management ensures secure access

