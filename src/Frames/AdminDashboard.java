package Frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Frames.my.MyButton;
import Frames.my.MyFont;
import Frames.my.MyPanel;

public class AdminDashboard extends MyPanel {
    private MyButton toggleButton; // Nút 3 gạch
    private JButton buttonGiaoVien; // Nút "Giáo Viên"
    private JButton buttonHocSinh; // Nút "Học Sinh"
    private JButton buttonLopHoc; // Nút "Lớp Học"
    private JButton addTeacherBtn, deleteTeacherBtn, updateTeacherBtn; // Các nút chức năng của "Giáo Viên"
    private JButton viewStudentBtn, deleteStudentBtn, addStudentBtn; // Các nút chức năng của "Học Sinh"
    private JButton viewClassInfoBtn; // Nút chức năng của "Lớp Học"
    private AdminForm mainForm;
    private AdminMethod npanel;
    private TeacherMethod tpanel;
    private StudentMethod spanel;
    private ClassInfoPanel cpanel;
    private StudentListFrame studentListFrame;

    public AdminDashboard(AdminForm mainPanel) {
        super(150, 720, Color.GRAY);
        this.mainForm = mainPanel;
        this.setLayout(null);
        // Tạo nút 3 gạch
        toggleButton = createBtn("≡", 20);

        // Tạo các nút chính
        buttonGiaoVien = createBtn("Teacher", 60);
        buttonHocSinh = createBtn("Student", 100);
        buttonLopHoc = createBtn("Class", 140);

        // Ẩn các nút chính ban đầu
        buttonGiaoVien.setVisible(false);
        buttonHocSinh.setVisible(false);
        buttonLopHoc.setVisible(false);

        // Tạo các nút chức năng
        addTeacherBtn = methodBtn("Add", 180);
        deleteTeacherBtn = methodBtn("Delete", 220);
        updateTeacherBtn = methodBtn("Info", 260);

        viewStudentBtn = methodBtn("Info", 300);
        deleteStudentBtn = methodBtn("Delete", 340);
        addStudentBtn = methodBtn("Add", 380);

        viewClassInfoBtn = methodBtn("Info", 420);

        // Ẩn tất cả các nút chức năng ban đầu
        addTeacherBtn.setVisible(false);
        deleteTeacherBtn.setVisible(false);
        updateTeacherBtn.setVisible(false);
        viewStudentBtn.setVisible(false);
        deleteStudentBtn.setVisible(false);
        addStudentBtn.setVisible(false);
        viewClassInfoBtn.setVisible(false);

        // Thêm sự kiện cho nút 3 gạch
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleButtonsVisibility();
            }
        });

        // Thêm sự kiện cho nút "Giáo Viên"
        buttonGiaoVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tpanel = new TeacherMethod();
                tpanel.setBounds(0, 0, 915, 630);
                tpanel.setBackground(Color.BLUE);
                tpanel.setVisible(false);
                tpanel.setEnabled(true);
                mainForm.mainPanel.removeAll(); 
                mainForm.mainPanel.add(tpanel);
                mainForm.mainPanel.revalidate();
                mainForm.mainPanel.repaint();

                toggleTeacherButtonsVisibility();
            }
        });

        // Thêm sự kiện cho nút "Học Sinh"
        buttonHocSinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spanel = new StudentMethod();
                spanel.setBounds(0, 0, 915, 630);
                spanel.setBackground(Color.BLUE);
                spanel.setVisible(false);
                spanel.setEnabled(true);
                mainForm.mainPanel.removeAll(); 
                mainForm.mainPanel.add(spanel);
                mainForm.mainPanel.revalidate();
                mainForm.mainPanel.repaint();

                toggleStuButtonsVisibility();
            }
        });

        // Thêm sự kiện cho nút "Lớp Học"
        buttonLopHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 // Tạo ClassInfoPanel và truyền danh sách lớp học
            cpanel = new ClassInfoPanel();
            
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

        addTeacherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                tpanel.addTeacher();
            }
        });

        deleteTeacherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                tpanel.deleteTeacher();
            }
        });

        updateTeacherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                // tpanel.editTeacher();
            }
        });

        addStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                spanel.addStudent();
            }
        });

        deleteStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                spanel.deleteStudent();
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
                studentListFrame = new StudentListFrame(cpanel.getSelectedRow());
                toggleClassButtonsVisibility();
            }
        });

        add(toggleButton);
        add(buttonGiaoVien);
        add(buttonHocSinh);
        add(buttonLopHoc);
    }

    private MyButton createBtn(String text, int yPosition) {
        MyFont fontBtn = new MyFont(Font.BOLD, 18);
        Color colorTextBtn = Color.WHITE;
        Color colorBackgroundBtn = new Color(100, 149, 237);

        MyButton button = new MyButton(text, fontBtn, colorTextBtn, colorBackgroundBtn);
        button.setSize(120, 30); // Set kích thước nút
        button.setBounds(10, yPosition, 120, 30); // Vị trí x, y, width, height
        return button;
    }

    private MyButton methodBtn(String text, int yPosition) {
        MyFont fontBtn = new MyFont(Font.BOLD, 14);
        Color colorTextBtn = Color.WHITE;
        Color colorBackgroundBtn = new Color(221,160,221);

        MyButton button = new MyButton(text, fontBtn, colorTextBtn, colorBackgroundBtn);
        button.setSize(120, 30); // Set kích thước nút
        button.setBounds(10, yPosition, 120, 30); // Vị trí x, y, width, height
        return button;
    }

    private void toggleButtonsVisibility() {

        boolean areMainButtonsVisible = buttonGiaoVien.isVisible();

        // Ẩn hoặc hiện 3 nút chính
        buttonGiaoVien.setVisible(!areMainButtonsVisible);
        buttonHocSinh.setVisible(!areMainButtonsVisible);
        buttonLopHoc.setVisible(!areMainButtonsVisible);

        // Đảm bảo nút 3 gạch luôn bật khi chỉ hiện 3 nút chính
        toggleButton.setEnabled(true);

        revalidate();
        repaint();
    }

    private void toggleTeacherButtonsVisibility() {
        if(!tpanel.isVisible()){
            tpanel.setVisible(true);
        }
        mainForm.mainPanel.revalidate();
        mainForm.mainPanel.repaint();

        boolean areTeacherButtonsVisible = addTeacherBtn.isVisible();

        addTeacherBtn.setBounds(25, 100, 120, 30);
        deleteTeacherBtn.setBounds(25, 140, 120, 30);
        updateTeacherBtn.setBounds(25, 180, 120, 30);

        // Hiện hoặc ẩn các nút chức năng của "Giáo Viên"
        addTeacherBtn.setVisible(!areTeacherButtonsVisible);
        deleteTeacherBtn.setVisible(!areTeacherButtonsVisible);
        updateTeacherBtn.setVisible(!areTeacherButtonsVisible);

        if (!areTeacherButtonsVisible) {
            // Vô hiệu hóa nút 3 gạch
            toggleButton.setEnabled(false);
            add(addTeacherBtn);
            add(deleteTeacherBtn);
            add(updateTeacherBtn);
            // Remove "Lớp" and "Học sinh" buttons
            buttonGiaoVien.setVisible(true);

            buttonHocSinh.setVisible(true);
            buttonHocSinh.setBounds(10, 260, 120, 30);
            buttonHocSinh.setEnabled(false);

            buttonLopHoc.setVisible(true);
            buttonLopHoc.setBounds(10, 220, 120, 30);
            buttonLopHoc.setEnabled(false);
        } else {
            // Bật lại nút 3 gạch
            toggleButton.setEnabled(true);
            remove(addTeacherBtn);
            remove(deleteTeacherBtn);
            remove(updateTeacherBtn);
            // Show "Lớp" and "Học sinh" buttons again
            buttonGiaoVien.setVisible(true);
                        
            buttonHocSinh.setVisible(true);
            buttonLopHoc.setVisible(true);

            buttonHocSinh.setEnabled(true);
            buttonLopHoc.setEnabled(true);

            buttonHocSinh.setBounds(10, 100, 120, 30);
            buttonLopHoc.setBounds(10, 140, 120, 30);
        }

        revalidate();
        repaint();
    }

    private void toggleStuButtonsVisibility() {
        if(!spanel.isVisible()){
            spanel.setVisible(true);
        }
        mainForm.mainPanel.revalidate();
        mainForm.mainPanel.repaint();
        mainForm.mainPanel.revalidate();
        mainForm.mainPanel.repaint();

        boolean areStudentButtonsVisible = viewStudentBtn.isVisible();

        addStudentBtn.setBounds(25, 140, 120, 30);
        deleteStudentBtn.setBounds(25, 180, 120, 30);
        viewStudentBtn.setBounds(25, 220, 120, 30);

        // Hiện hoặc ẩn các nút chức năng của "Học Sinh"
        viewStudentBtn.setVisible(!areStudentButtonsVisible);
        deleteStudentBtn.setVisible(!areStudentButtonsVisible);
        addStudentBtn.setVisible(!areStudentButtonsVisible);

        if (!areStudentButtonsVisible) {
            add(addStudentBtn);
            add(deleteStudentBtn);
            add(viewStudentBtn);
            toggleButton.setEnabled(false);
            buttonHocSinh.setVisible(true);

            buttonGiaoVien.setVisible(true);
            buttonGiaoVien.setEnabled(false);

            buttonLopHoc.setVisible(true);
            buttonLopHoc.setBounds(10, 260, 120, 30);
            buttonLopHoc.setEnabled(false);

        } else {
            toggleButton.setEnabled(true);
            remove(addStudentBtn);
            remove(deleteStudentBtn);
            remove(viewStudentBtn);
            buttonGiaoVien.setVisible(true);
            buttonGiaoVien.setEnabled(true);

            buttonHocSinh.setVisible(true);

            buttonLopHoc.setVisible(true);
            buttonLopHoc.setBounds(10, 140, 120, 30);
            buttonLopHoc.setEnabled(true);
        }

        revalidate();
        repaint();
    }

    private void toggleClassButtonsVisibility() {

        boolean isClassInfoVisible = viewClassInfoBtn.isVisible();

        viewClassInfoBtn.setBounds(25, 180, 120, 30);

        // Hiện hoặc ẩn nút chức năng của "Lớp Học"
        viewClassInfoBtn.setVisible(!isClassInfoVisible);

        if (!isClassInfoVisible) {
            add(viewClassInfoBtn);
            buttonLopHoc.setVisible(true);
            toggleButton.setEnabled(false);
            buttonGiaoVien.setVisible(true);
            buttonGiaoVien.setEnabled(false);
            buttonHocSinh.setVisible(true);
            buttonHocSinh.setEnabled(false);
        } else {
            remove(viewClassInfoBtn);
            toggleButton.setEnabled(true);
            buttonGiaoVien.setVisible(true);
            buttonHocSinh.setVisible(true);
            buttonGiaoVien.setEnabled(true);
            buttonHocSinh.setEnabled(true);
        }

        revalidate();
        repaint();
    }
}