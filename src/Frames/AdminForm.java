package Frames;

import java.awt.*;

import Frames.my.MyFrame;
import Frames.my.MyPanel;

public class AdminForm {
    public Header teacherHD  = new Header("Admin");
    public MyPanel mainPanel = new MyPanel();
    public AdminDashboard teacherDB = new AdminDashboard(this);
    public AdminForm() {
        MyFrame frame = new MyFrame("Admin Form");

        frame.add(teacherDB, BorderLayout.WEST);
        frame.add(teacherHD, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

