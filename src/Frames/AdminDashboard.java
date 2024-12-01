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
    private JButton buttonLop; // Button for "Lớp"
    private JButton buttonHocSinh; // Button for "Học sinh"
    private JButton addTeacherBtn; // Button for "Thêm lớp"
    private JButton deleteTeacherBtn; // Button for "Xóa lớp"
    private JButton updateTeacherBtn; // Button for "Cập nhật lớp"
    private JButton viewStuInfo;
    private JButton entGrade;
    private AdminForm mainForm;
    private AdminMethod npanel;

    public AdminDashboard(AdminForm mainPanel) {
        super(150, 720, Color.GRAY);

        this.mainForm = mainPanel;
        // Set up the main panel
        this.setLayout(null);

        // Create toggle button with "≡" icon
        MyButton toggleButton = createBtn("≡", 20);

        // Create the buttons for "Lớp" and "Học sinh"
        buttonLop = createBtn("Giáo viên", 60);
        buttonHocSinh = createBtn("Học sinh", 100);

        buttonLop.setVisible(false);
        buttonHocSinh.setVisible(false);

        npanel = new AdminMethod();
        npanel.setBounds(0, 0, 915, 630);
        npanel.setBackground(Color.BLUE);
        npanel.setVisible(false);
        npanel.setEnabled(true);
        mainForm.mainPanel.add(npanel);

        // Initially hide the buttons for "Thêm lớp", "Xóa lớp", "Cập nhật lớp"
        addTeacherBtn = createBtn("Thêm giáo viên", 140);
        deleteTeacherBtn = createBtn("Xóa giáo viên", 180);
        updateTeacherBtn = createBtn("Cập nhật giáo viên", 220);
        viewStuInfo = createBtn("Thông tin học sinh", 300);
        entGrade = createBtn("Nhập điểm", 340);

        addTeacherBtn.setVisible(false);
        deleteTeacherBtn.setVisible(false);
        updateTeacherBtn.setVisible(false);
        viewStuInfo.setVisible(false);
        entGrade.setVisible(false);

        // Event for toggle button
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleButtonsVisibility();
            }
        });

        // Event for "Lớp" button
        buttonLop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleClassButtonsVisibility();
            }
        });

        buttonHocSinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleStuButtonsVisibility();
            }
        });

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
                npanel.updateTeacher();
            }
        });

        // Add buttons to the panel
        add(toggleButton);
        add(buttonLop);
        add(buttonHocSinh); // Add "Học sinh" button permanently
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
        boolean areButtonsVisible = buttonLop.isVisible();
        buttonLop.setVisible(!areButtonsVisible);
        buttonHocSinh.setVisible(!areButtonsVisible);
    }

    // Toggle visibility of "Thêm lớp", "Xóa lớp", "Cập nhật lớp" buttons under "Lớp"
    private void toggleClassButtonsVisibility() {
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
        addTeacherBtn.setBounds(10, 100, 120, 30);
        deleteTeacherBtn.setBounds(10, 140, 120, 30);
        updateTeacherBtn.setBounds(10, 180, 120, 30);

        // Add buttons when they become visible
        if (!areClassButtonsVisible) {
            add(addTeacherBtn);
            add(deleteTeacherBtn);
            add(updateTeacherBtn);
            // Remove "Lớp" and "Học sinh" buttons
            buttonLop.setVisible(true);
            buttonHocSinh.setVisible(false);
        } else {
            remove(addTeacherBtn);
            remove(deleteTeacherBtn);
            remove(updateTeacherBtn);
            // Show "Lớp" and "Học sinh" buttons again
            buttonLop.setVisible(true);
            buttonHocSinh.setVisible(true);
            buttonHocSinh.setBounds(10, 100, 120, 30);
        }

        // Revalidate and repaint to reflect changes in UI
        revalidate();
        repaint();
    }

    private void toggleStuButtonsVisibility() {
        boolean areStuButtonsVisible = viewStuInfo.isVisible();

        viewStuInfo.setVisible(!areStuButtonsVisible);
        entGrade.setVisible(!areStuButtonsVisible);

        viewStuInfo.setBounds(10, 140, 120, 30);
        entGrade.setBounds(10, 180, 120, 30);

        if (!areStuButtonsVisible) {
            add(viewStuInfo);
            add(entGrade);
            buttonLop.setVisible(true);
            buttonHocSinh.setVisible(true);
        } else {
            remove(viewStuInfo);
            remove(entGrade);
            buttonLop.setVisible(true);
            buttonHocSinh.setVisible(true);
        }

        revalidate();
        repaint();
    } 
}
