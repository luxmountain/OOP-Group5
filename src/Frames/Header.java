package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Frames.my.MyFont;
import Frames.my.MyLabel;
import Frames.my.MyPanel;

public class Header extends MyPanel {

    private Frame parentFrame; // Tham chiếu đến frame cha để xử lý đăng xuất
    private String adminID;
    public Header(String title, Frame parentFrame, String adminID) {
        super(1080, 50, Color.WHITE);
        this.parentFrame = parentFrame;
        this.adminID = adminID;
        this.setLayout(new BorderLayout()); // Sử dụng BorderLayout để chia header thành các vùng

        addLogo();
        addTitle(title);
        addUserMenu();
    }

    // public Header(Frame parentFrame) {
    //     this("Student Management System", parentFrame);
    // }

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
        JButton userButton = new JButton("👨");
        userButton.setBackground(Color.WHITE);
        userButton.setBorder(BorderFactory.createEmptyBorder()); // Loại bỏ viền nút
        userButton.setFocusPainted(true);
    
        // Tạo menu popup
        JPopupMenu userMenu = new JPopupMenu();
        JMenuItem editInfo = new JMenuItem("Sửa thông tin");
        JMenuItem logout = new JMenuItem("Đăng xuất");
    
        // Xử lý sự kiện cho các mục menu
        editInfo.addActionListener(e -> updatePersonalInfo());
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
        userButton.addActionListener(e -> {
            // Show the menu at the left of the button (x = button's x - menu's width, y = button's y + button's height)
            userMenu.show(userButton, -userMenu.getPreferredSize().width, userButton.getHeight());
        });
    
        // Đặt nút vào góc phải
        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.setBackground(Color.WHITE);
        userPanel.setBorder(new EmptyBorder(0, 0, 0, 20)); // Padding phải 20px
        userPanel.add(userButton, BorderLayout.CENTER);
    
        this.add(userPanel, BorderLayout.EAST);
    }
    

    private void updatePersonalInfo() {
        // // Tạo các trường nhập thông tin
        // JTextField nameField = new JTextField(admin.getName());
        // JTextField emailField = new JTextField(admin.getEmail());
        // JTextField phoneField = new JTextField(admin.getPhone());
        // SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // String formattedDate = dateFormat.format(admin.getBirthDate());

        // JTextField dobField = new JTextField(formattedDate);

        // Object[] message = {
        //     "Tên:", nameField,
        //     "Email:", emailField,
        //     "Số điện thoại:", phoneField,
        //     "Ngày sinh (dd/MM/yyyy):", dobField,
        // };

        // int option = JOptionPane.showConfirmDialog(
        //     this,
        //     message,
        //     "Cập nhật thông tin cá nhân",
        //     JOptionPane.OK_CANCEL_OPTION
        // );

        // if (option == JOptionPane.OK_OPTION) {
        //     String name = nameField.getText();
        //     String email = emailField.getText();
        //     String phone = phoneField.getText();
        //     String dobText = dobField.getText();

        //     if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || dobText.isEmpty()) {
        //         JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        //     } else {
        //         try {
        //             dateFormat.setLenient(false); // Đảm bảo kiểm tra chặt chẽ định dạng
        //             Date dob = dateFormat.parse(dobText);

        //             JOptionPane.showMessageDialog(this, "Thông tin cá nhân đã được cập nhật thành công!");
        //             admin.setName(name);
        //             admin.setEmail(email);
        //             admin.setPhone(phone);
        //             admin.setBirthDate(dob);
        //             System.out.println(admin.getName());
        //         } catch (Exception e) {
        //             JOptionPane.showMessageDialog(
        //                 this,
        //                 "Định dạng ngày sinh không hợp lệ. Vui lòng sử dụng định dạng dd/MM/yyyy",
        //                 "Lỗi",
        //                 JOptionPane.ERROR_MESSAGE
        //             );
        //         }
        //     }
        // }
    }
}