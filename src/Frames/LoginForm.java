package Frames;

import java.awt.*;
import javax.swing.*;

public class LoginForm {

    public LoginForm() {
        // Frame dimensions
        int frameWidth = 1080;
        int frameHeight = 608;

        // Create the JFrame
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setResizable(false); // Prevent resizing
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        
        // Create a panel for the login form
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null); // Use null layout for absolute positioning
        loginPanel.setBackground(Color.pink);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Tên tài khoản:");
        usernameLabel.setBounds(290, 150, 200, 30); // Adjust position for 16:9 ratio
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(480, 150, 300, 30);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setBounds(290, 250, 200, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(480, 250, 300, 30);

        // Login button
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(490, 350, 150, 40);
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components to the panel
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        // Add the panel to the frame
        frame.add(loginPanel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
