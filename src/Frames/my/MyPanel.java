package Frames.my;

import java.awt.*;
import javax.swing.*;

public class MyPanel extends JPanel{
    public MyPanel(){
        this.setLayout(null);
        this.setBackground(Color.PINK);
    }   
    public MyPanel(int width, int height, Color color){
        this.setBackground(color);
        this.setPreferredSize(new Dimension(width, height));
    }
}
