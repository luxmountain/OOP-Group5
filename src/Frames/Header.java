package Frames;

import Frames.my.*;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Header extends MyPanel {

    public Header(String title) {
        super(1080, 50, Color.WHITE);

        this.setLayout(new BorderLayout()); // Sử dụng BorderLayout để chia header thành các vùng

        addLogo();
        addTitle(title);
    }

    public Header() {
        super(1080, 50, Color.WHITE);

        this.setLayout(new BorderLayout()); // Sử dụng BorderLayout để chia header thành các vùng

        addLogo();
        addTitle("Student Management System");
    }

    private void addLogo() {
        try {
            // Tải logo từ URL và chỉnh kích thước
            URL logoUrl = new URL("https://dhs.ptit.edu.vn/web/image/385-67ae5bc7/Logo_PTIT_University.png");
            ImageIcon logoIcon = new ImageIcon(
                new ImageIcon(logoUrl).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)
            );
            JLabel logoLabel = new JLabel(logoIcon);

            JPanel logoPanel = new JPanel(new BorderLayout());
            logoPanel.setBackground(Color.WHITE);
            logoPanel.setBorder(new EmptyBorder(0, 20, 0, 0)); 
            logoPanel.add(logoLabel, BorderLayout.CENTER);

            this.add(logoPanel, BorderLayout.WEST);
        } catch (Exception e) {
            e.printStackTrace();

            JLabel errorLabel = new JLabel("Logo not available");
            errorLabel.setForeground(Color.RED);

            JPanel logoPanel = new JPanel(new BorderLayout());
            logoPanel.setBackground(Color.WHITE);
            logoPanel.setBorder(new EmptyBorder(0, 20, 0, 0)); // Padding trái  20px
            logoPanel.add(errorLabel, BorderLayout.CENTER);

            this.add(logoPanel, BorderLayout.WEST);
        }
    }

    private void addTitle(String title ) {
        MyLabel titleLabel = new MyLabel(title , new MyFont(Font.BOLD, 24), Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel, BorderLayout.CENTER);
    }
}