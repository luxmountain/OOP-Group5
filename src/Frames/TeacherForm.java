package Frames;

import java.awt.*;
import javax.swing.*;

import Frames.my.MyFrame;
import Frames.my.MyPanel;

public class TeacherForm {
    public TeacherForm() {
        MyFrame frame = new MyFrame("Teacher Form");

        Header headerPanel  = new Header();
        MyPanel mainPanel   = new MyPanel();
        TeacherDashBoard dashBoard = new TeacherDashBoard();

        frame.add(dashBoard, BorderLayout.WEST);
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
