package Frames;

import Frames.my.MyButton;
import Frames.my.MyFont;
import Frames.my.MyFrame;
import Frames.my.MyLabel;
import Frames.my.MyPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginForm {
    public LoginForm() {
        MyFrame frame = new MyFrame("Login Form");

        MyPanel loginPanel = new MyPanel();
        loginPanel.setLayout(null); 

        Color colorLabel = Color.BLACK;
        MyFont fontLabel = new MyFont(Font.BOLD, 18);

        MyLabel usernameLabel = new MyLabel("Tên tài khoản:", fontLabel, colorLabel);
        usernameLabel.setBounds(290, 150, 200, 30);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(480, 150, 300, 30);

        MyLabel passwordLabel = new MyLabel("Mật khẩu:", fontLabel, colorLabel);
        passwordLabel.setBounds(290, 250, 200, 30);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(480, 250, 300, 30);

        // Tạo nút hiển thị/ẩn mật khẩu
        JButton togglePasswordButton = new JButton("👁");
        togglePasswordButton.setBounds(780, 250, 50, 30);
        togglePasswordButton.setFocusPainted(false); // Tắt viền khi click
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

        MyButton loginButton = new MyButton("Đăng nhập", new MyFont(Font.BOLD, 16), Color.WHITE, Color.BLUE);
        loginButton.setBounds(490, 350, 150, 40);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("") && password.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Đăng nhập thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    frame.dispose();
                    new MainForm();
                } else {
                    JOptionPane.showMessageDialog(frame, "Tên tài khoản hoặc mật khẩu sai!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(togglePasswordButton);
        loginPanel.add(loginButton);

        frame.add(loginPanel);

        frame.setVisible(true);
    }
}
