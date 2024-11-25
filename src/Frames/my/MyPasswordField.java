package Frames.my;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPasswordField;

public class MyPasswordField extends JPasswordField{
    public MyPasswordField(Font font, Dimension dimension){
        this.setFont(font);
        this.setPreferredSize(dimension);
    }
}
