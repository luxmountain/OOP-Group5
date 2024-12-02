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
        MyFrame frame = new MyFrame("Form Đăng Ký");

        // Main panel for the form
        MyPanel registerPanel = new MyPanel();
        registerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Khoảng cách giữa các phần tử
        gbc.fill = GridBagConstraints.HORIZONTAL; // Giãn đều JTextField
        gbc.anchor = GridBagConstraints.WEST; // Canh trái

        // Phông chữ và kích thước
        Font labelFontBold = new Font("Arial", Font.BOLD, 24);
        Font labelFontRegular = new Font("Arial", Font.PLAIN, 24);  // Regular font for non-bold labels
        Font fieldFont = new Font("Arial", Font.PLAIN, 20);

        // Kích thước các trường
        Dimension fieldDimension = new Dimension(300, 40);

        // Các trường thông tin
        JLabel nameLabel = new JLabel("Họ và tên:");
        nameLabel.setFont(labelFontRegular); // Set non-bold for nameLabel

        JTextField nameField = new JTextField();
        nameField.setFont(fieldFont);
        nameField.setPreferredSize(fieldDimension);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFontRegular);  // Set bold font for emailLabel

        JTextField emailField = new JTextField();
        emailField.setFont(fieldFont);
        emailField.setPreferredSize(fieldDimension);

        JLabel phoneLabel = new JLabel("Số điện thoại:");
        phoneLabel.setFont(labelFontRegular);  // Set non-bold for phoneLabel

        JTextField phoneField = new JTextField();
        phoneField.setFont(fieldFont);
        phoneField.setPreferredSize(fieldDimension);

        JLabel birthDateLabel = new JLabel("Ngày sinh (dd/MM/yyyy):");
        birthDateLabel.setFont(labelFontRegular);  // Set non-bold for birthDateLabel

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

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setFont(labelFontRegular);  // Set non-bold for passwordLabel

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);
        passwordField.setPreferredSize(fieldDimension);

        JLabel confirmPasswordLabel = new JLabel("Xác nhận mật khẩu:");
        confirmPasswordLabel.setFont(labelFontRegular);  // Set non-bold for confirmPasswordLabel

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(fieldFont);
        confirmPasswordField.setPreferredSize(fieldDimension);

        JButton registerButton = new JButton("Đăng Ký");
        registerButton.setFont(new Font("Arial", Font.BOLD, 20));
        registerButton.setBackground(Color.BLUE);
        registerButton.setForeground(Color.WHITE);
        registerButton.setPreferredSize(new Dimension(200, 50));

        JLabel messageLabel = new JLabel("");
        messageLabel.setFont(labelFontRegular);
        messageLabel.setForeground(Color.RED);

        // Add Back button to the top-left corner
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            frame.dispose();
            new LoginForm();  // Navigate to LoginForm
        });

        // Create a top panel for Back button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align left
        topPanel.setBackground(Color.PINK);  // Set background color
        topPanel.add(backButton);  // Add back button to this panel

        // Add the top panel to the frame
        frame.add(topPanel, BorderLayout.NORTH);

        // Add the rest of the form components
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
        gbc.anchor = GridBagConstraints.CENTER; // Canh giữa nút
        registerPanel.add(registerButton, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        registerPanel.add(messageLabel, gbc);

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
                messageLabel.setText("Vui lòng điền đầy đủ thông tin!");
            } else {
                try {
                    String birthDateStr = day + "/" + month + "/" + year;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.setLenient(false);
                    Date birthDate = sdf.parse(birthDateStr);
                    String currentId = String.format("%d", ++Main.IDX);

                    Admin newAdmin = new Admin(name, phone, email, currentId, birthDate);
                    newAdmin.getAccount().setPassword(password);
                    Main.adminList.add(newAdmin);
                    
                    Database dtb = new Database();
                    dtb.insertAdmin(currentId, name, phone, email, birthDate, password);
                    JOptionPane.showMessageDialog(frame, "Đăng ký thành công!\nChào " + name, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new LoginForm();
                } catch (ParseException ex) {
                    messageLabel.setText("Ngày sinh không hợp lệ!");
                }
            }
        });

        frame.add(registerPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
