package Frames;

import Frames.my.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RegisterForm {
    public RegisterForm() {
        // Use MyFrame for the main frame
        MyFrame frame = new MyFrame("Form Đăng Ký");
        frame.setSize(1080, 720);
        frame.setLayout(new BorderLayout());

        MyPanel registerPanel = new MyPanel();
        registerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont  = new MyFont(Font.BOLD, 18);
        Font fieldFont  = new MyFont(Font.PLAIN, 16);
        Font buttonFont = new MyFont(Font.BOLD, 20);
        
        Color labelColor = Color.BLACK;

        Dimension fieldDimension = new Dimension(300, 40);

        MyLabel nameLabel = new MyLabel("Họ và tên:", labelFont, labelColor);
        MyTextField nameField = new MyTextField(fieldFont, fieldDimension);

        MyLabel emailLabel = new MyLabel("Email:", labelFont, labelColor);
        MyTextField emailField = new MyTextField(fieldFont, fieldDimension);

        MyLabel passwordLabel = new MyLabel("Mật khẩu:", labelFont, labelColor);
        MyPasswordField passwordField = new MyPasswordField(fieldFont, fieldDimension);

        MyLabel confirmPasswordLabel = new MyLabel("Xác nhận mật khẩu:", labelFont, labelColor);
        MyPasswordField confirmPasswordField = new MyPasswordField(fieldFont, fieldDimension);

        MyButton registerButton = new MyButton("Đăng Ký", buttonFont, Color.WHITE, Color.BLUE);
        registerButton.setPreferredSize(new Dimension(200, 50));

        MyLabel messageLabel = new MyLabel("", labelFont, Color.RED);

        JCheckBox teacherCheckBox = new JCheckBox("Giáo viên");
        teacherCheckBox.setFont(labelFont);
        teacherCheckBox.setBackground(Color.PINK);

        JCheckBox studentCheckBox = new JCheckBox("Học sinh");
        studentCheckBox.setFont(labelFont);
        studentCheckBox.setBackground(Color.PINK);

        ButtonGroup userTypeGroup = new ButtonGroup();
        userTypeGroup.add(teacherCheckBox);
        userTypeGroup.add(studentCheckBox);

        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        checkboxPanel.add(teacherCheckBox);
        checkboxPanel.add(studentCheckBox);
        checkboxPanel.setBackground(Color.PINK);

        gbc.gridx = 0; gbc.gridy = 0;
        registerPanel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        registerPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        registerPanel.add(emailLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        registerPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        registerPanel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        registerPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        registerPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        registerPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        registerPanel.add(new MyLabel("Bạn là:", labelFont, labelColor), gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        registerPanel.add(checkboxPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        registerPanel.add(registerButton, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        registerPanel.add(messageLabel, gbc);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String userType = teacherCheckBox.isSelected() ? "Giáo viên" : (studentCheckBox.isSelected() ? "Học sinh" : "");
        
                String nameRegex = "^[a-zA-Z\\s]+$";
                String emailRegex = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,}$";
        
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || userType.isEmpty()) {
                    messageLabel.setText("Vui lòng điền đầy đủ thông tin và chọn vai trò!");
                    messageLabel.setForeground(Color.RED);
                } else if (!name.matches(nameRegex)) {
                    messageLabel.setText("Tên chỉ được chứa chữ và khoảng trắng!");
                    messageLabel.setForeground(Color.RED);
                } else if (!email.matches(emailRegex)) {
                    messageLabel.setText("Email không đúng định dạng!");
                    messageLabel.setForeground(Color.RED);
                } else if (!password.equals(confirmPassword)) {
                    messageLabel.setText("Mật khẩu xác nhận không khớp!");
                    messageLabel.setForeground(Color.RED);
                } else {
                    JOptionPane.showMessageDialog(frame, "Đăng ký thành công!\nVai trò: " + userType, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
