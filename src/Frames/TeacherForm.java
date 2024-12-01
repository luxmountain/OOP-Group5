package Frames;

import Frames.my.MyFrame;
import Frames.my.MyPanel;
import java.awt.*;

public class TeacherForm {
    MyPanel mainPanel = new MyPanel();
    public TeacherDashBoard teacherDB = new TeacherDashBoard(this);
    public TeacherForm() {
        MyFrame frame = new MyFrame("Teacher Form");
        Header teacherHD  = new Header("Teacher", frame);
        frame.add(teacherDB, BorderLayout.WEST);
        frame.add(teacherHD, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

