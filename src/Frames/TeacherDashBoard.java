package Frames;

import javax.swing.*;
import Frames.my.MyButton;
import Frames.my.MyFont;
import Frames.my.MyPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherDashBoard extends MyPanel {
    private JButton buttonLop; // Button for "Lớp"
    private JButton buttonHocSinh; // Button for "Học sinh"
    private JButton addClassButton; // Button for "Thêm lớp"
    private JButton deleteClassButton; // Button for "Xóa lớp"
    private JButton updateClassButton; // Button for "Cập nhật lớp"
    private JButton viewStuInfo;
    private JButton entGrade;
    private TeacherForm mainForm;
    private TeacherMethod npanel;

    public TeacherDashBoard(TeacherForm mainPanel) {
        this.mainForm = mainPanel;
        // Set up the main panel
        super(150, 720, Color.GRAY);
        this.setLayout(null);

        // Create toggle button with "≡" icon
        MyButton toggleButton = createBtn("≡", 20);

        // Create the buttons for "Lớp" and "Học sinh"
        buttonLop = createBtn("Lớp", 60);
        buttonHocSinh = createBtn("Học sinh", 100);

        buttonLop.setVisible(false);
        buttonHocSinh.setVisible(false);

        npanel = new TeacherMethod();
        npanel.setBounds(0, 0, 915, 630);
        npanel.setBackground(Color.BLUE);
        npanel.setVisible(false);
        npanel.setEnabled(true);
        mainForm.mainPanel.add(npanel);

        // Initially hide the buttons for "Thêm lớp", "Xóa lớp", "Cập nhật lớp"
        addClassButton = createBtn("Thêm lớp", 140);
        deleteClassButton = createBtn("Xóa lớp", 180);
        updateClassButton = createBtn("Cập nhật lớp", 220);
        viewStuInfo = createBtn("Thông tin học sinh", 300);
        entGrade = createBtn("Nhập điểm", 340);

        addClassButton.setVisible(false);
        deleteClassButton.setVisible(false);
        updateClassButton.setVisible(false);
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

        addClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                npanel.addStudent();
            }
        });

        deleteClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                npanel.deleteStudent();
            }
        });

        updateClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                npanel.editStudent();
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

        boolean areClassButtonsVisible = addClassButton.isVisible();

        // Show/hide class-related buttons under "Lớp"
        addClassButton.setVisible(!areClassButtonsVisible);
        deleteClassButton.setVisible(!areClassButtonsVisible);
        updateClassButton.setVisible(!areClassButtonsVisible);

        // Reposition buttons to appear below "Lớp"
        buttonHocSinh.setBounds(10, 260, 120, 30);
        addClassButton.setBounds(10, 100, 120, 30);
        deleteClassButton.setBounds(10, 140, 120, 30);
        updateClassButton.setBounds(10, 180, 120, 30);

        // Add buttons when they become visible
        if (!areClassButtonsVisible) {
            add(addClassButton);
            add(deleteClassButton);
            add(updateClassButton);
            // Remove "Lớp" and "Học sinh" buttons
            buttonLop.setVisible(true);
            buttonHocSinh.setVisible(false);
        } else {
            remove(addClassButton);
            remove(deleteClassButton);
            remove(updateClassButton);
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
