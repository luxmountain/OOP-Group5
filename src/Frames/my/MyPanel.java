package Frames.my;

import java.awt.*;
import javax.swing.*;

public class MyPanel extends JPanel{
    public MyPanel(){
        this.setLayout(null);
        this.setBackground(Color.PINK);
    }   
    public MyPanel(Color color){
        this.setLayout(null);
        this.setBackground(color);
    }
    public MyPanel(int width, int height, Color color){
        this.setBackground(color);
        this.setPreferredSize(new Dimension(width, height));
    }
    public void setDefaultCloseOperation(int exitOnClose) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDefaultCloseOperation'");
    }
}