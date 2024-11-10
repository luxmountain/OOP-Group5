package Frames;

import Frames.my.MyFrame;
import Frames.my.MyPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginForm {
    public LoginForm() {
        MyFrame frame = new MyFrame("Login Form");

        MyPanel loginPanel = new MyPanel();

        JLabel usernameLabel = new JLabel("Tên tài khoản:");
        usernameLabel.setBounds(290, 150, 200, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(480, 150, 300, 30);

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setBounds(290, 250, 200, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(480, 250, 300, 30);

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(490, 350, 150, 40);
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("") && password.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Đăng nhập thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Đóng cửa sổ đăng nhập và mở dashboard
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
        loginPanel.add(loginButton);

        frame.add(loginPanel);

        frame.setVisible(true);
    }
}
