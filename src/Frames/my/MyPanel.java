package Frames.my;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

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
}