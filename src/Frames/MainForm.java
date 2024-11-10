package Frames;

import java.awt.*;
import javax.swing.*;

import Frames.my.MyFrame;
import Frames.my.MyPanel;

public class MainForm {
    public MainForm() {
        MyFrame frame = new MyFrame("Main Form");

        Header headerPanel  = new Header();
        MyPanel mainPanel   = new MyPanel();
        DashBoard dashBoard = new DashBoard();

        frame.add(dashBoard, BorderLayout.WEST);
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
