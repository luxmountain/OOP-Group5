package Frames;

import Frames.my.MyFrame;
import Frames.my.MyPanel;
import java.awt.*;

public class AdminForm {
    public MyPanel mainPanel = new MyPanel();
    public AdminDashboard teacherDB = new AdminDashboard(this);
    public AdminForm() {
        MyFrame frame = new MyFrame("Admin Form");

        Header teacherHD  = new Header("Admin", frame);

        frame.add(teacherDB, BorderLayout.WEST);
        frame.add(teacherHD, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

