package badas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Login {
    private JFrame frame;
    private JTextField userField;
    private JPasswordField passField;
    private Map<String, String> users = new HashMap<>();
    private Map<String, String> roles = new HashMap<>();
    private final String FILE_PATH = "user_accounts.csv";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().createAndShowGUI());
    }

    public void createAndShowGUI() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(new JLabel("Username:"));
        userField = new JTextField();
        frame.add(userField);

        frame.add(new JLabel("Password:"));
        passField = new JPasswordField();
        frame.add(passField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::login);
        frame.add(loginButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        frame.add(exitButton);

        loadUserAccounts();

        frame.setVisible(true);
    }

    // Load user accounts from CSV file
    private void loadUserAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            br.readLine(); // Skip the header row
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    users.put(data[0], data[1]); // Username and Password
                    roles.put(data[0], data[2]); // Username and Role
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading user accounts!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Login method
    private void login(ActionEvent e) {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            frame.dispose();
            String role = roles.get(username);

            switch (role.toLowerCase()) {
                case "admin":
                    AdminPage.main(new String[]{});
                    break;
                case "dispatcher":
                    DispatcherPage.main(new String[]{});
                    break;
                case "doctor":
                    DoctorPage.main(new String[]{});
                    break;
                case "nurse":
                    NursePage.main(new String[]{});
                    break;
                case "receptionist":
                    ReceptionistPage.main(new String[]{});
                    break;
                case "emergency coordinator":
                    EmergencyCoordinatorPage.main(new String[]{});
                    break;
                case "it support":
                    ITSupportPage.main(new String[]{});
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Role not recognized!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}

