package Frames.my;

import java.awt.Dimension;
import java.awt.Font; 
import javax.swing.JTextField;

public class MyTextField extends JTextField {
    public MyTextField(Font font, Dimension dimension) {
        this.setFont(font);
        this.setPreferredSize(dimension);
    }
}
