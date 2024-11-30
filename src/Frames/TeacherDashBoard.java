package Frames;

import javax.swing.*;
import Frames.my.MyButton;
import Frames.my.MyFont;
import Frames.my.MyPanel;
import Models.Student;
import Frames.TeacherForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TeacherDashBoard extends MyPanel {
    private JButton buttonLop; // Button for "Lớp"
    private JButton buttonHocSinh; // Button for "Học sinh"
    private JButton addClassButton; // Button for "Thêm lớp"
    private JButton deleteClassButton; // Button for "Xóa lớp"
    private JButton updateClassButton; // Button for "Cập nhật lớp"
    private JButton viewClassInfo;
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
        viewClassInfo = createBtn("Xem thông tin", 260);
        viewStuInfo = createBtn("Thông tin học sinh", 300);
        entGrade = createBtn("Nhập điểm", 340);

        addClassButton.setVisible(false);
        deleteClassButton.setVisible(false);
        updateClassButton.setVisible(false);
        viewClassInfo.setVisible(false);
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
            public void actionPerformed(ActionEvent e1) {
                toggleClassButtonsVisibility();
            }
        });

        buttonHocSinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                toggleStuButtonsVisibility();
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
        addClassButton.setVisible(!areButtonsVisible);
        deleteClassButton.setVisible(!areButtonsVisible);
        updateClassButton.setVisible(!areButtonsVisible);
        viewClassInfo.setVisible(!areButtonsVisible);
        viewStuInfo.setVisible(!areButtonsVisible);
        entGrade.setVisible(!areButtonsVisible);
    }

    // Toggle visibility of "Thêm lớp", "Xóa lớp", "Cập nhật lớp" buttons under "Lớp"
    private void toggleClassButtonsVisibility() {
    
        if(!npanel.isVisible()){
            npanel.setVisible(true);
        }
        mainForm.mainPanel.revalidate();
        mainForm.mainPanel.repaint();

        boolean areClassButtonsVisible = addClassButton.isVisible();
    
        // Toggle visibility of class-related buttons
        addClassButton.setVisible(!areClassButtonsVisible);
        deleteClassButton.setVisible(!areClassButtonsVisible);
        updateClassButton.setVisible(!areClassButtonsVisible);
        viewClassInfo.setVisible(!areClassButtonsVisible);
    
        if (!areClassButtonsVisible) {
            // Reposition and add class-related buttons
            addClassButton.setBounds(10, 100, 120, 30);
            deleteClassButton.setBounds(10, 140, 120, 30);
            updateClassButton.setBounds(10, 180, 120, 30);
            viewClassInfo.setBounds(10, 220, 120, 30);
    
            add(addClassButton);
            add(deleteClassButton);
            add(updateClassButton);
            add(viewClassInfo);
    
            // Adjust "Học sinh" button position
            buttonHocSinh.setBounds(10, 260, 120, 30);
        } else {
            // Remove class-related buttons
            remove(addClassButton);
            remove(deleteClassButton);
            remove(updateClassButton);
            remove(viewClassInfo);
    
            // Reset "Học sinh" button position
            buttonHocSinh.setBounds(10, 100, 120, 30);
        }
    
        // Refresh panel to apply changes
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
