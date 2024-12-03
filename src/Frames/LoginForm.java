package Frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Frames.my.MyButton;
import Frames.my.MyFont;
import Frames.my.MyFrame;
import Frames.my.MyLabel;
import Frames.my.MyPanel;
import application.Database;

public class LoginForm {
    public LoginForm() {
        MyFrame frame = new MyFrame("Login Form");
        frame.setSize(1080, 720); // Set frame size to 1080x720

        MyPanel loginPanel = new MyPanel();
        loginPanel.setLayout(null);

        // Update font size and label positions for more space
        Color colorLabel = Color.BLACK;
        MyFont fontLabel = new MyFont(Font.BOLD, 22); // Increased font size

        MyLabel usernameLabel = new MyLabel("Username:", fontLabel, colorLabel);
        usernameLabel.setBounds(290, 180, 250, 40); // Increased Y position

        JTextField usernameField = new JTextField();
        usernameField.setBounds(480, 180, 350, 40); // Increased field width and height

        MyLabel passwordLabel = new MyLabel("Password:", fontLabel, colorLabel);
        passwordLabel.setBounds(290, 260, 250, 40); // Increased Y position

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(480, 260, 350, 40); // Increased field width and height

        JButton togglePasswordButton = new JButton("👁");
        togglePasswordButton.setBounds(840, 260, 60, 40); // Adjusted position for larger button
        togglePasswordButton.setFocusPainted(false);
        togglePasswordButton.setBorder(null);
        togglePasswordButton.setBackground(null);

        togglePasswordButton.addActionListener(new ActionListener() {
            private boolean showPassword = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                showPassword = !showPassword;
                if (showPassword) {
                    passwordField.setEchoChar((char) 0);
                    togglePasswordButton.setText("🙈");
                } else {
                    passwordField.setEchoChar('•');
                    togglePasswordButton.setText("👁");
                }
            }
        });

        MyButton loginButton = new MyButton("Login", new MyFont(Font.BOLD, 20), Color.WHITE, Color.BLUE);
        loginButton.setBounds(490, 360, 180, 50); // Increased button size

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().substring(5); // Get substring starting from the 5th character
                String password = new String(passwordField.getPassword());
                Database dtb = new Database();

                String adminId = dtb.checkLogin(username, password); // Get admin ID

                if (adminId != null) {
                    JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new AdminForm(adminId); // Pass admin ID to AdminForm
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        MyButton registerButton = new MyButton("Register", new MyFont(Font.BOLD, 20), Color.WHITE, Color.BLUE);
        registerButton.setBounds(490, 440, 180, 50); // Increased button size

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new RegisterForm();
            }
        });

        // Add components to the panel
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(togglePasswordButton);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        frame.add(loginPanel);
        frame.setLocationRelativeTo(null); // Center the frame on screen
        frame.setVisible(true);
    }
}
