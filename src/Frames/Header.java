package Frames;

import Frames.my.MyFont;
import Frames.my.MyLabel;
import Frames.my.MyPanel;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Header extends MyPanel {

    private Frame parentFrame; // Tham chiếu đến frame cha để xử lý đăng xuất

    public Header(String title, Frame parentFrame) {
        super(1080, 50, Color.WHITE);
        this.parentFrame = parentFrame;

        this.setLayout(new BorderLayout()); // Sử dụng BorderLayout để chia header thành các vùng

        addLogo();
        addTitle(title);
        addUserMenu();
    }

    public Header(Frame parentFrame) {
        this("Student Management System", parentFrame);
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
            logoPanel.setBorder(new EmptyBorder(0, 20, 0, 0)); // Padding trái 20px
            logoPanel.add(errorLabel, BorderLayout.CENTER);

            this.add(logoPanel, BorderLayout.WEST);
        }
    }

    private void addTitle(String title) {
        MyLabel titleLabel = new MyLabel(title, new MyFont(Font.BOLD, 24), Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel, BorderLayout.CENTER);
    }

    private void addUserMenu() {
        // Tạo nút người dùng
        JButton userButton = new JButton("Personal");
        userButton.setBackground(Color.BLUE);
        userButton.setForeground(Color.WHITE);
        userButton.setBorder(BorderFactory.createEmptyBorder()); // Loại bỏ viền nút
        userButton.setFocusPainted(false);

        // Tạo menu popup
        JPopupMenu userMenu = new JPopupMenu();
        JMenuItem editInfo = new JMenuItem("Sửa thông tin");
        JMenuItem logout = new JMenuItem("Đăng xuất");

        // Xử lý sự kiện cho các mục menu
        editInfo.addActionListener(e -> JOptionPane.showMessageDialog(this, "Chức năng sửa thông tin!"));
        logout.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Đăng xuất thành công!");
            if (parentFrame != null) {
                parentFrame.dispose(); // Đóng frame cha
                // Khởi tạo form đăng nhập
                new LoginForm();
            }
        });

        // Thêm các mục vào menu
        userMenu.add(editInfo);
        userMenu.add(logout);

        // Hiển thị menu khi bấm vào nút
        userButton.addActionListener(e -> userMenu.show(userButton, 0, userButton.getHeight()));

        // Đặt nút vào góc phải
        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.setBackground(Color.WHITE);
        userPanel.setBorder(new EmptyBorder(0, 0, 0, 20)); // Padding phải 20px
        userPanel.add(userButton, BorderLayout.CENTER);

        this.add(userPanel, BorderLayout.EAST);
    }
}
