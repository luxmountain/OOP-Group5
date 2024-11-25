package Frames;

import Frames.my.MyButton;
import Frames.my.MyFont;
import Frames.my.MyPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DashBoard extends MyPanel{
    public DashBoard(){
        super(160, 720, Color.GRAY);
    
        // Disable layout manager to position buttons manually
        this.setLayout(null);
    
        // Vị trí bắt đầu
        int yPosition = 20; 
    
        // Tạo các nút và menu
        MyButton studentBtn = createBtn("Học sinh", yPosition);
        JPopupMenu studentMenu = createDropdownMenu();
        attachMenu(studentBtn, studentMenu);
        yPosition += 30 + 50; // Cập nhật vị trí Y cho nút tiếp theo
    
        MyButton courseBtn = createBtn("Môn học", yPosition);
        JPopupMenu courseMenu = createDropdownMenu();
        attachMenu(courseBtn, courseMenu);
        yPosition += 30 + 50; // Cập nhật vị trí Y cho nút tiếp theo
    
        MyButton teacherBtn = createBtn("Giáo viên", yPosition);
        JPopupMenu teacherMenu = createDropdownMenu();
        attachMenu(teacherBtn, teacherMenu);
    
        // Thêm các nút vào panel
        this.add(studentBtn);
        this.add(courseBtn);
        this.add(teacherBtn);
    }
    
    private MyButton createBtn(String text, int yPosition) {
        MyFont fontBtn = new MyFont(Font.BOLD, 18);
        Color colorTextBtn = Color.WHITE;
        Color colorBackgroundBtn = new Color(100, 149, 237);
    
        MyButton button = new MyButton(text, fontBtn, colorTextBtn, colorBackgroundBtn);
        button.setSize(120, 30); // Kích thước của nút
    
        // Đặt vị trí của nút, có margin-bottom là 50
        button.setBounds(20, yPosition, 120, 30); // x, y, width, height
    
        return button;
    }
    
    
    private JPopupMenu createDropdownMenu() {
        JPopupMenu menu = new JPopupMenu();

        JMenuItem addItem       = new JMenuItem("Add");
        JMenuItem manageItem    = new JMenuItem("Manage");

        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
            }
        });

        manageItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Manage clicked");
            }
        });
        menu.add(addItem);
        menu.add(manageItem);

        return menu;
    }

    private void attachMenu(JButton button, JPopupMenu menu) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.show(button, 0, button.getHeight());
            }
        });
    }
}