package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Frames.my.MyFrame;
import Frames.my.MyPanel;
import Models.Admin;
import application.Database;
import application.Main;

public class RegisterForm {
    public RegisterForm() {
        // Sử dụng MyFrame cho frame chính
        MyFrame frame = new MyFrame("Register Form");

        // Main panel for the form
        MyPanel registerPanel = new MyPanel();
        registerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Khoảng cách giữa các phần tử
        gbc.fill = GridBagConstraints.HORIZONTAL; // Giãn đều JTextField
        gbc.anchor = GridBagConstraints.WEST; // Canh trái

        // Phông chữ và kích thước
        Font labelFontRegular = new Font("Arial", Font.PLAIN, 24); // Regular font
        Font fieldFont = new Font("Arial", Font.PLAIN, 20);

        // Kích thước các trường
        Dimension fieldDimension = new Dimension(300, 40);

        // Các trường thông tin
        JLabel nameLabel = new JLabel("Fullname:");
        nameLabel.setFont(labelFontRegular);

        JTextField nameField = new JTextField();
        nameField.setFont(fieldFont);
        nameField.setPreferredSize(fieldDimension);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFontRegular);

        JTextField emailField = new JTextField();
        emailField.setFont(fieldFont);
        emailField.setPreferredSize(fieldDimension);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(labelFontRegular);

        JTextField phoneField = new JTextField();
        phoneField.setFont(fieldFont);
        phoneField.setPreferredSize(fieldDimension);

        JLabel birthDateLabel = new JLabel("Date of birth (dd/MM/yyyy)");
        birthDateLabel.setFont(labelFontRegular);

        JTextField dayField = new JTextField();
        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();

        dayField.setFont(fieldFont);
        monthField.setFont(fieldFont);
        yearField.setFont(fieldFont);

        Dimension smallFieldDimension = new Dimension(100, 40);
        dayField.setPreferredSize(smallFieldDimension);
        monthField.setPreferredSize(smallFieldDimension);
        yearField.setPreferredSize(smallFieldDimension);

        JPanel birthDatePanel = new JPanel(new FlowLayout());
        birthDatePanel.add(dayField);
        birthDatePanel.add(new JLabel("/"));
        birthDatePanel.add(monthField);
        birthDatePanel.add(new JLabel("/"));
        birthDatePanel.add(yearField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFontRegular);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);
        passwordField.setPreferredSize(fieldDimension);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(labelFontRegular);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(fieldFont);
        confirmPasswordField.setPreferredSize(fieldDimension);

        JButton registerButton = new JButton("Sign Up");
        registerButton.setFont(new Font("Arial", Font.BOLD, 20));
        registerButton.setBackground(Color.BLUE);
        registerButton.setForeground(Color.WHITE);
        registerButton.setPreferredSize(new Dimension(200, 50));

        JLabel messageLabel = new JLabel("");
        messageLabel.setFont(labelFontRegular);
        messageLabel.setForeground(Color.RED);

        // Add Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            frame.dispose();
            new LoginForm();
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.PINK);
        topPanel.add(backButton);

        frame.add(topPanel, BorderLayout.NORTH);

        gbc.gridx = 0; gbc.gridy = 0;
        registerPanel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        registerPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        registerPanel.add(emailLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        registerPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        registerPanel.add(phoneLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        registerPanel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        registerPanel.add(birthDateLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        registerPanel.add(birthDatePanel, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        registerPanel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        registerPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        registerPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        registerPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        registerPanel.add(registerButton, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        registerPanel.add(messageLabel, gbc);

        frame.add(registerPanel, BorderLayout.CENTER);

        registerButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String day = dayField.getText().trim();
            String month = monthField.getText().trim();
            String year = yearField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || day.isEmpty() || month.isEmpty() || year.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                messageLabel.setText("Please fill in all the information!");
            } else {
                try {
                    String birthDateStr = day + "/" + month + "/" + year;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.setLenient(false);
                    Date birthDate = sdf.parse(birthDateStr);

                    if (!password.equals(confirmPassword)) {
                        messageLabel.setText("Passwords do not match!");
                        return;
                    }

                    Database dtb = new Database();
                    int newIdNumber = dtb.countAdmins() + 1;
                    String newId = String.format("%d", newIdNumber);
                    Admin newAdmin = new Admin(name, phone, email, newId, birthDate);
                    newAdmin.getAccount().setPassword(password);
                    Main.adminList.add(newAdmin);

                    dtb.insertAdmin(name, phone, email, birthDate, password);
                    JOptionPane.showMessageDialog(frame, "Registration successful!\nYour account ID is: " + newAdmin.getAccount().getUserID(), "Notification", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new LoginForm();
                } catch (ParseException ex) {
                    messageLabel.setText("Invalid date of birth!");
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
