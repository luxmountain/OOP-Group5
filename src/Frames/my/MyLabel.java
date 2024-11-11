package Frames.my;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel{
    public MyLabel(String text, Font font, Color colorText){
        super(text);
        this.setFont(font);
        this.setForeground(colorText);
    }    
}
