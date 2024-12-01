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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Frames.my.MyFrame;
import Frames.my.MyPanel;
import Models.Admin;
import application.Main;


public class RegisterForm {
    public RegisterForm() {
        // Sử dụng MyFrame cho frame chính
        MyFrame frame = new MyFrame("Form Đăng Ký");

        MyPanel registerPanel = new MyPanel();
        registerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Khoảng cách giữa các phần tử
        gbc.fill = GridBagConstraints.HORIZONTAL; // Giãn đều JTextField
        gbc.anchor = GridBagConstraints.WEST; // Canh trái

        // Phông chữ và kích thước
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        // Kích thước các trường
        Dimension fieldDimension = new Dimension(300, 40);

        // Các trường thông tin
        JLabel nameLabel = new JLabel("Họ và tên:");
        JTextField nameField = new JTextField();
        nameField.setFont(fieldFont);
        nameField.setPreferredSize(fieldDimension);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        emailField.setFont(fieldFont);
        emailField.setPreferredSize(fieldDimension);

        JLabel phoneLabel = new JLabel("Số điện thoại:");
        JTextField phoneField = new JTextField();
        phoneField.setFont(fieldFont);
        phoneField.setPreferredSize(fieldDimension);

        JLabel birthDateLabel = new JLabel("Ngày sinh (dd/MM/yyyy):");
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
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);
        passwordField.setPreferredSize(fieldDimension);

        JLabel confirmPasswordLabel = new JLabel("Xác nhận mật khẩu:");
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(fieldFont);
        confirmPasswordField.setPreferredSize(fieldDimension);

        JButton registerButton = new JButton("Đăng Ký");
        registerButton.setFont(new Font("Arial", Font.BOLD, 20));
        registerButton.setBackground(Color.BLUE);
        registerButton.setForeground(Color.WHITE);
        registerButton.setPreferredSize(new Dimension(200, 50));

        JLabel messageLabel = new JLabel("");
        messageLabel.setFont(labelFont);
        messageLabel.setForeground(Color.RED);

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
        
            // String nameRegex = "^[a-zA-Z\\s]+$";
            // String emailRegex = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,}$";
            // String phoneRegex = "^\\d{10,11}$";
        
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || day.isEmpty() || month.isEmpty() || year.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                messageLabel.setText("Vui lòng điền đầy đủ thông tin!");
            } 
            // else if (!name.matches(nameRegex)) {
            //     messageLabel.setText("Tên chỉ được chứa chữ và khoảng trắng!");
            // } else if (!email.matches(emailRegex)) {
            //     messageLabel.setText("Email không đúng định dạng!");
            // } else if (!phone.matches(phoneRegex)) {
            //     messageLabel.setText("Số điện thoại không hợp lệ!");
            // } else if (!password.equals(confirmPassword)) {
            //     messageLabel.setText("Mật khẩu xác nhận không khớp!");
            // } 
            else {
                try {
                    String birthDateStr = day + "/" + month + "/" + year;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.setLenient(false); // Strict validation
                    Date birthDate = sdf.parse(birthDateStr);
        
                    Admin newAdmin = new Admin(name, phone, email, String.format("%d", ++Main.IDX), birthDate);
                    newAdmin.getAccount().setPassword(password);
                    Main.adminList.add(newAdmin);
                    System.out.println(Main.adminList.get(1).getAccount().getUserID());
                    System.out.println(Main.adminList.get(1).getAccount().getPassword());
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
