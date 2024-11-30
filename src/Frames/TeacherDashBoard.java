package Frames;

import javax.swing.*;
import Frames.my.MyButton;
import Frames.my.MyFont;
import Frames.my.MyPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherDashBoard extends MyPanel {
    private JButton buttonA; // Button for "Lớp"
    private JButton buttonB; // Button for "Học sinh"
    private JButton addClassButton; // Button for "Thêm lớp"
    private JButton deleteClassButton; // Button for "Xóa lớp"
    private JButton updateClassButton; // Button for "Cập nhật lớp"
    private JButton viewClassInfo;
    private JButton viewStuInfo;
    private JButton entGrade;

    public TeacherDashBoard() {
        // Set up the main panel
        super(150, 720, Color.GRAY);
        this.setLayout(null);

        // Create toggle button with "≡" icon
        MyButton toggleButton = createBtn("≡", 20);

        // Create the buttons for "Lớp" and "Học sinh"
        buttonA = createBtn("Lớp", 60);
        buttonB = createBtn("Học sinh", 100);

        buttonA.setVisible(false);
        buttonB.setVisible(false);

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
        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleClassButtonsVisibility();
            }
        });

        buttonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleStuButtonsVisibility();
            }
        });

        // Add buttons to the panel
        add(toggleButton);
        add(buttonA);
        add(buttonB); // Add "Học sinh" button permanently
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
        boolean areButtonsVisible = buttonA.isVisible();
        buttonA.setVisible(!areButtonsVisible);
        buttonB.setVisible(!areButtonsVisible);
    }

    // Toggle visibility of "Thêm lớp", "Xóa lớp", "Cập nhật lớp" buttons under "Lớp"
    private void toggleClassButtonsVisibility() {
        boolean areClassButtonsVisible = addClassButton.isVisible();

        // Show/hide class-related buttons under "Lớp"
        addClassButton.setVisible(!areClassButtonsVisible);
        deleteClassButton.setVisible(!areClassButtonsVisible);
        updateClassButton.setVisible(!areClassButtonsVisible);
        viewClassInfo.setVisible(!areClassButtonsVisible);

        // Reposition buttons to appear below "Lớp"
        buttonB.setBounds(10, 260, 120, 30);
        addClassButton.setBounds(10, 100, 120, 30);
        deleteClassButton.setBounds(10, 140, 120, 30);
        updateClassButton.setBounds(10, 180, 120, 30);
        viewClassInfo.setBounds(10, 220, 120, 30);

        // Add buttons when they become visible
        if (!areClassButtonsVisible) {
            add(addClassButton);
            add(deleteClassButton);
            add(updateClassButton);
            add(viewClassInfo);
            // Remove "Lớp" and "Học sinh" buttons
            buttonA.setVisible(true);
            buttonB.setVisible(false);
        } else {
            remove(addClassButton);
            remove(deleteClassButton);
            remove(updateClassButton);
            remove(viewClassInfo);
            // Show "Lớp" and "Học sinh" buttons again
            buttonA.setVisible(true);
            buttonB.setVisible(true);
            buttonB.setBounds(10, 100, 120, 30);
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
            buttonA.setVisible(true);
            buttonB.setVisible(true);
        } else {
            remove(viewStuInfo);
            remove(entGrade);
            buttonA.setVisible(true);
            buttonB.setVisible(true);
        }

        revalidate();
        repaint();
    }
}
