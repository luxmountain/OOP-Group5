package Frames;

import java.awt.BorderLayout;

import Frames.my.MyFrame;
import Frames.my.MyPanel;
import Models.Admin;

public class AdminForm {
    public MyPanel mainPanel = new MyPanel();
    public AdminDashboard teacherDB = new AdminDashboard(this);
    public AdminForm(Admin ad) {
        MyFrame frame = new MyFrame("Admin Form");

        Header teacherHD = new Header("Admin", frame, ad);

        frame.add(teacherDB, BorderLayout.WEST);
        frame.add(teacherHD, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
