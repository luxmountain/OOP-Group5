package Frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Frames.my.MyButton;
import Frames.my.MyFont;
import Frames.my.MyPanel;
import Models.SchoolClass;
import Models.Teacher;
import application.Main;

public class AdminDashboard extends MyPanel {
    private JButton buttonGiaoVien; // Button for "Lớp"
    private JButton buttonHocSinh; // Button for "Học sinh"
    private JButton buttonLopHoc; //Button for "Lớp học"
    private JButton addTeacherBtn; // Button for "Thêm"
    private JButton deleteTeacherBtn; // Button for "Xóa"
    private JButton updateTeacherBtn; // Button for "Cập nhật lớp"
    private JButton viewStudentBtn;
    private JButton deleteStudentBtn;
    private JButton addStudentBtn;
    private JButton viewClassInfoBtn;
    private JButton entGrade;
    private AdminForm mainForm;
    private AdminMethod npanel;
    private ClassInfoPanel cpanel;
    private ClassInfoPanel cpanel;

    public AdminDashboard(AdminForm mainPanel) {
        super(150, 720, Color.GRAY);

        this.mainForm = mainPanel;
        // Set up the main panel
        this.setLayout(null);

        // Create toggle button with "≡" icon
        MyButton toggleButton = createBtn("≡", 20);

        // Create the buttons for "Lớp" and "Học sinh"
        buttonGiaoVien = createBtn("Giáo Viên", 60);
        buttonHocSinh = createBtn("Học Sinh", 100);
        buttonLopHoc = createBtn("Lớp Học", 140);

        buttonGiaoVien.setVisible(false);
        buttonHocSinh.setVisible(false);
        buttonLopHoc.setVisible(false);

       

        // Initially hide the buttons for "Thêm", "Xóa", "Cập nhật"
        addTeacherBtn = createBtn("Thêm ", 180);
        deleteTeacherBtn = createBtn("Xóa ", 220);
        updateTeacherBtn = createBtn("Thông Tin GV", 260);
        //Khởi tạo button cho Học Sinh
        viewStudentBtn = createBtn("Thông tin HS", 300);
        deleteStudentBtn = createBtn("Xóa", 340);
        addStudentBtn = createBtn("Thêm", 380);
        //Khởi tạo button cho Lớp Học
        viewClassInfoBtn = createBtn("Thông tin", 420);

        // entGrade = createBtn("Nhập điểm", 340);

        addTeacherBtn.setVisible(false);
        deleteTeacherBtn.setVisible(false);
        updateTeacherBtn.setVisible(false);
        viewStudentBtn.setVisible(false);
        //entGrade.setVisible(false);
        viewStudentBtn.setVisible(false);
        deleteStudentBtn.setVisible(false);
        addStudentBtn.setVisible(false);
        viewClassInfoBtn.setVisible(false);

        // Event for toggle button
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleButtonsVisibility();
            }
        });

        // Event for "Giáo Viên" button
        buttonGiaoVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 npanel = new AdminMethod();
                    npanel.setBounds(0, 0, 915, 630);
                    npanel.setBackground(Color.BLUE);
                    npanel.setVisible(false);
                    npanel.setEnabled(true);
                    mainForm.mainPanel.removeAll(); 
                    mainForm.mainPanel.add(npanel);
                    mainForm.mainPanel.revalidate();
                    mainForm.mainPanel.repaint();
                toggleTeacherButtonsVisibility();
            }
        });
        // Event for "Học Sinh" button
        buttonHocSinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleStuButtonsVisibility();
            }
        });
        // Event for "Lơp Học" button
        buttonLopHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 // Tạo ClassInfoPanel và truyền danh sách lớp học
            ClassInfoPanel cpanel = new ClassInfoPanel();
            
            // Thiết lập kích thước và các thuộc tính của ClassInfoPanel
            cpanel.setBounds(0, 0, 915, 630);
            cpanel.setBackground(Color.LIGHT_GRAY);
            cpanel.setVisible(true);
            cpanel.setEnabled(true);
                    
            // Thêm ClassInfoPanel vào mainPanel
            mainForm.mainPanel.removeAll(); // Xóa các component cũ trong mainPanel
            mainForm.mainPanel.add(cpanel);
            mainForm.mainPanel.revalidate();
            mainForm.mainPanel.repaint();
                    toggleClassButtonsVisibility();
            }
        });
        
        
        // Them chuc nang cho cac nut cua button Teacher
        addTeacherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                npanel.addTeacher();
            }
        });

        deleteTeacherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                npanel.deleteTeacher();
            }
        });

        updateTeacherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                
            }
        });
        viewClassInfoBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        
        toggleClassButtonsVisibility();
    }
});


        viewClassInfoBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy danh sách lớp học từ adminList (hoặc nơi bạn lưu trữ danh sách lớp học)
        List<SchoolClass> classList = new ArrayList<>(); // Lấy danh sách lớp học từ hệ thống
        for (Teacher teacher : Main.adminList.get(0).getTeachers()) {
            if (teacher.getClazz() != null) {
                classList.add(teacher.getClazz());
            }
        }

        // Kiểm tra nếu không có lớp học nào
        if (classList.isEmpty()) {
            JOptionPane.showMessageDialog(npanel, "Không có lớp học nào để hiển thị.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Tạo ClassInfoPanel và truyền danh sách lớp học
        ClassInfoPanel cpanel = new ClassInfoPanel((ArrayList<SchoolClass>) classList);
        
        // Thiết lập kích thước và các thuộc tính của ClassInfoPanel
        cpanel.setBounds(0, 0, 915, 630);
        cpanel.setBackground(Color.LIGHT_GRAY);
        cpanel.setVisible(true);
        
        
        // Gọi phương thức viewClassInfo() để hiển thị thông tin chi tiết của lớp đầu tiên (hoặc lớp được chọn)
        // Bạn có thể thay đổi chỉ số lớp (classIndex) theo nhu cầu
        cpanel.viewClassInfo(0);  // Ví dụ, gọi thông tin lớp đầu tiên
        toggleClassButtonsVisibility();
    }
});


        // Add buttons to the panel
        add(toggleButton);
        add(buttonGiaoVien);
        add(buttonHocSinh); // Add "Học sinh" button permanently
        add(buttonLopHoc);
    }

    private MyButton createBtn(String text, int yPosition) {
        MyFont fontBtn = new MyFont(Font.BOLD, 18);
        Color colorTextBtn = Color.WHITE;
        Color colorBackgroundBtn = new Color(100, 149, 237);

        MyButton button = new MyButton(text, fontBtn, colorTextBtn, colorBackgroundBtn);
        button.setSize(120, 30); // Set button size
        button.setBounds(10, yPosition, 120, 30); // x, y, width, height
        return button;
    }

    // Toggle visibility of "Lớp" and "Học sinh" buttons
    private void toggleButtonsVisibility() {
        boolean areButtonsVisible = buttonGiaoVien.isVisible();
        buttonGiaoVien.setVisible(!areButtonsVisible);
        buttonHocSinh.setVisible(!areButtonsVisible);
        buttonLopHoc.setVisible(!areButtonsVisible);
    }

    // Toggle visibility of "Thêm", "Xóa", "Cập nhật" buttons under "Giáo Viên"
    private void toggleTeacherButtonsVisibility() {
        if(!npanel.isVisible()){
            npanel.setVisible(true);
        }
        mainForm.mainPanel.revalidate();
        mainForm.mainPanel.repaint();

        boolean areClassButtonsVisible = addTeacherBtn.isVisible();

        // Show/hide class-related buttons under "Lớp"
        addTeacherBtn.setVisible(!areClassButtonsVisible);
        deleteTeacherBtn.setVisible(!areClassButtonsVisible);
        updateTeacherBtn.setVisible(!areClassButtonsVisible);

        // Reposition buttons to appear below "Lớp"
        buttonHocSinh.setBounds(10, 260, 120, 30);
        buttonLopHoc.setBounds(10, 300, 120, 30);
        addTeacherBtn.setBounds(10, 100, 120, 30);
        deleteTeacherBtn.setBounds(10, 140, 120, 30);
        updateTeacherBtn.setBounds(10, 180, 120, 30);

        // Add buttons when they become visible
        if (!areClassButtonsVisible) {
            add(addTeacherBtn);
            add(deleteTeacherBtn);
            add(updateTeacherBtn);
            // Remove "Lớp" and "Học sinh" buttons
            buttonGiaoVien.setVisible(true);
            buttonHocSinh.setVisible(false);
            buttonLopHoc.setVisible(false);
        } else {
            remove(addTeacherBtn);
            remove(deleteTeacherBtn);
            remove(updateTeacherBtn);
            // Show "Lớp" and "Học sinh" buttons again
            buttonGiaoVien.setVisible(true);
            buttonHocSinh.setVisible(true);
            buttonLopHoc.setVisible(true);
            buttonHocSinh.setBounds(10, 100, 120, 30);
            buttonLopHoc.setBounds(10, 140, 120, 30);
        }

        // Revalidate and repaint to reflect changes in UI
        revalidate();
        repaint();
    }

    private void toggleStuButtonsVisibility() {
        boolean areStuButtonsVisible = viewStudentBtn.isVisible();

        viewStudentBtn.setVisible(!areStuButtonsVisible);
        deleteStudentBtn.setVisible(!areStuButtonsVisible);
        addStudentBtn.setVisible(!areStuButtonsVisible);

        addStudentBtn.setBounds(10, 140, 120, 30);
        deleteStudentBtn.setBounds(10, 180, 120, 30);
        viewStudentBtn.setBounds(10, 220, 120, 30);

        if (!areStuButtonsVisible) {
            add(addStudentBtn);
            add(deleteStudentBtn);
            add(viewStudentBtn);
            buttonGiaoVien.setVisible(true);
            buttonHocSinh.setVisible(true);
            buttonLopHoc.setVisible(false);
        } else {
            remove(addStudentBtn);
            remove(deleteStudentBtn);
            remove(viewStudentBtn);
            buttonGiaoVien.setVisible(true);
            buttonHocSinh.setVisible(true);
            buttonLopHoc.setVisible(true);
            buttonLopHoc.setBounds(10, 140, 120, 30);
        }

        revalidate();
        repaint();
    } 

    private void toggleClassButtonsVisibility() {
         
        boolean areClassButtonsVisible = viewClassInfoBtn.isVisible();

        viewClassInfoBtn.setVisible(!areClassButtonsVisible);

        viewClassInfoBtn.setBounds(10, 180, 120, 30);

        if (!areClassButtonsVisible) {
            add(viewClassInfoBtn);
        } else {
            remove(viewClassInfoBtn);
        }
        buttonGiaoVien.setVisible(true);
        buttonHocSinh.setVisible(true);
        buttonLopHoc.setVisible(true);
        revalidate();
        repaint();
    } 

}
