package badas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

/**
 * Login Page for BADAS System.
 * Authenticates users and directs them to different modules based on roles.
 */
public class Login {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BADAS System Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.add(new LoginPanel(frame));
            frame.setVisible(true);
        });
    }
}

class LoginPanel extends JPanel {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JLabel statusLabel;
    private final JFrame parentFrame;

    // Hardcoded user credentials and roles
    private final HashMap<String, String> userCredentials = new HashMap<>();
    private final HashMap<String, String> userRoles = new HashMap<>();

    public LoginPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.setLayout(new BorderLayout());

        // Initialize credentials (username, password) and roles
        userCredentials.put("admin", "admin123");
        userRoles.put("admin", "Admin");

        userCredentials.put("staff", "staff123");
        userRoles.put("staff", "Hospital Staff");

        userCredentials.put("doctor", "doctor123");
        userRoles.put("doctor", "Doctor");

        userCredentials.put("nurse", "nurse123");
        userRoles.put("nurse", "Nurse");

        userCredentials.put("dispatcher", "dispatcher123");
        userRoles.put("dispatcher", "Dispatcher");

        userCredentials.put("receptionist", "reception123");
        userRoles.put("receptionist", "Receptionist");

        userCredentials.put("coordinator", "coord123");
        userRoles.put("coordinator", "Emergency Coordinator");

        userCredentials.put("it", "it123");
        userRoles.put("it", "IT Support");

        // Header Label
        JLabel headerLabel = new JLabel("Login to BADAS System", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(headerLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::handleLogin);
        formPanel.add(loginButton);

        this.add(formPanel, BorderLayout.CENTER);

        // Status Label
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        this.add(statusLabel, BorderLayout.SOUTH);
    }

    private void handleLogin(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            String role = userRoles.get(username);
            statusLabel.setForeground(Color.GREEN);
            statusLabel.setText("Login successful! Role: " + role);

            // Proceed to the appropriate module based on role
            parentFrame.dispose();
            redirectToRolePage(role);
        } else {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Invalid username or password.");
        }
    }

    private void redirectToRolePage(String role) {
        switch (role) {
            case "Admin":
                // Placeholder for Admin page
                JOptionPane.showMessageDialog(null, "Redirecting to Admin Page");
                break;
            case "Hospital Staff":
                PatientInfo.main(new String[]{});
                break;
            case "Doctor":
                JOptionPane.showMessageDialog(null, "Redirecting to Doctor Page");
                break;
            case "Nurse":
                JOptionPane.showMessageDialog(null, "Redirecting to Nurse Page");
                break;
            case "Dispatcher":
                JOptionPane.showMessageDialog(null, "Redirecting to Dispatcher Page");
                break;
            case "Receptionist":
                JOptionPane.showMessageDialog(null, "Redirecting to Receptionist Page");
                break;
            case "Emergency Coordinator":
                JOptionPane.showMessageDialog(null, "Redirecting to Emergency Coordinator Page");
                break;
            case "IT Support":
                JOptionPane.showMessageDialog(null, "Redirecting to IT Support Page");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Unknown role");
                break;
        }
    }
}
