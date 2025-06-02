package banking.db;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static final String DB_FILE = "src/banking/db/users.txt";
    private static Map<String, UserData> users = new HashMap<>();

    public static class UserData {
        public String firstName;
        public String lastName;
        public String email;
        public String password;
        public double balance;

        public UserData(String firstName, String lastName, String email, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.balance = 0.0; // Default balance
        }

        @Override
        public String toString() {
            return firstName + "|" + lastName + "|" + email + "|" + password + "|" + balance;
        }

        public static UserData fromString(String data) {
            String[] parts = data.split("\\|");
            if (parts.length == 5) {
                UserData userData = new UserData(parts[0], parts[1], parts[2], parts[3]);
                userData.balance = Double.parseDouble(parts[4]);
                return userData;
            }
            return null;
        }
    }

    static {
        loadUsers();
    }

    private static void loadUsers() {
        File file = new File(DB_FILE);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                UserData userData = UserData.fromString(line);
                if (userData != null) {
                    users.put(userData.email, userData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DB_FILE))) {
            for (UserData userData : users.values()) {
                writer.println(userData.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean registerUser(String firstName, String lastName, String email, String password) {
        if (users.containsKey(email)) {
            return false;
        }

        UserData userData = new UserData(firstName, lastName, email, password);
        users.put(email, userData);
        saveUsers();
        return true;
    }

    public static UserData loginUser(String email, String password) {
        UserData userData = users.get(email);
        if (userData != null && userData.password.equals(password)) {
            return userData;
        }
        return null;
    }

    public static boolean updateBalance(String email, double newBalance) {
        UserData userData = users.get(email);
        if (userData != null) {
            userData.balance = newBalance;
            saveUsers();
            return true;
        }
        return false;
    }

    public static UserData getUserByEmail(String email) {
        return users.get(email);
    }
} 