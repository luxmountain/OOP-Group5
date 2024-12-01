package Frames;

import Frames.my.*;
import Models.Admin;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class RegisterForm {
    public RegisterForm() {
        // Sử dụng MyFrame cho frame chính
        MyFrame frame = new MyFrame("Form Đăng Ký");
        frame.setSize(1080, 720);
        frame.setLayout(new BorderLayout());

        MyPanel registerPanel = new MyPanel();
        registerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new MyFont(Font.BOLD, 18);
        Font fieldFont = new MyFont(Font.PLAIN, 16);
        Font buttonFont = new MyFont(Font.BOLD, 20);

        Color labelColor = Color.BLACK;

        Dimension fieldDimension = new Dimension(300, 40);

        // Các trường thông tin
        MyLabel nameLabel = new MyLabel("Họ và tên:", labelFont, labelColor);
        MyTextField nameField = new MyTextField(fieldFont, fieldDimension);

        MyLabel emailLabel = new MyLabel("Email:", labelFont, labelColor);
        MyTextField emailField = new MyTextField(fieldFont, fieldDimension);

        MyLabel phoneLabel = new MyLabel("Số điện thoại:", labelFont, labelColor);
        MyTextField phoneField = new MyTextField(fieldFont, fieldDimension);

        MyLabel roleLabel = new MyLabel("Vai trò:", labelFont, labelColor);
        String[] roles = {"Học sinh", "Giáo viên", "Quản trị viên"}; // Thêm vai trò nếu cần
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(fieldFont);
        roleComboBox.setPreferredSize(fieldDimension);

        MyLabel passwordLabel = new MyLabel("Mật khẩu:", labelFont, labelColor);
        MyPasswordField passwordField = new MyPasswordField(fieldFont, fieldDimension);

        MyLabel confirmPasswordLabel = new MyLabel("Xác nhận mật khẩu:", labelFont, labelColor);
        MyPasswordField confirmPasswordField = new MyPasswordField(fieldFont, fieldDimension);

        MyButton registerButton = new MyButton("Đăng Ký", buttonFont, Color.WHITE, Color.BLUE);
        registerButton.setPreferredSize(new Dimension(200, 50));

        MyLabel messageLabel = new MyLabel("", labelFont, Color.RED);

        // Thêm các trường vào panel
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
        registerPanel.add(roleLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        registerPanel.add(roleComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        registerPanel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        registerPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        registerPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        registerPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        registerPanel.add(registerButton, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        registerPanel.add(messageLabel, gbc);

        // Xử lý sự kiện nút Đăng Ký
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String role = (String) roleComboBox.getSelectedItem();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Kiểm tra tính hợp lệ
                String nameRegex = "^[a-zA-Z\\s]+$";
                String emailRegex = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,}$";
                String phoneRegex = "^\\d{10,11}$"; // Số điện thoại 10-11 chữ số

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    messageLabel.setText("Vui lòng điền đầy đủ thông tin!");
                    messageLabel.setForeground(Color.RED);
                } else if (!name.matches(nameRegex)) {
                    messageLabel.setText("Tên chỉ được chứa chữ và khoảng trắng!");
                    messageLabel.setForeground(Color.RED);
                } else if (!email.matches(emailRegex)) {
                    messageLabel.setText("Email không đúng định dạng!");
                    messageLabel.setForeground(Color.RED);
                } else if (!phone.matches(phoneRegex)) {
                    messageLabel.setText("Số điện thoại không hợp lệ!");
                    messageLabel.setForeground(Color.RED);
                } else if (!password.equals(confirmPassword)) {
                    messageLabel.setText("Mật khẩu xác nhận không khớp!");
                    messageLabel.setForeground(Color.RED);
                } else {
                    JOptionPane.showMessageDialog(frame, "Đăng ký thành công!\nVai trò: " + role, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new LoginForm();
                }
            }
        });

        frame.add(registerPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
