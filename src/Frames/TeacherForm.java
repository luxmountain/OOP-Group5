package Frames;

import java.awt.*;

import Frames.my.MyFrame;
import Frames.my.MyPanel;

public class TeacherForm {
        public TeacherHeader teacherHD  = new TeacherHeader();
        public MyPanel mainPanel = new MyPanel();
        public TeacherDashBoard teacherDB = new TeacherDashBoard(this);
    public TeacherForm() {
        MyFrame frame = new MyFrame("Teacher Form");

        frame.add(teacherDB, BorderLayout.WEST);
        frame.add(teacherHD, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
