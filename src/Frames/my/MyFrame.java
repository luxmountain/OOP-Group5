package Frames.my;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
    public MyFrame(String title) {
        super(title);

        int frameWidth  = 1080;
        int frameHeight = 720;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null); // Center the frame on screen
        this.setResizable(false);
    }
}
