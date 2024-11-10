package Frames.my;
import java.awt.*;
import javax.swing.*;

public class MyButton extends JButton {
    public MyButton(String text, Font font, Color colorText, Color colorBackground){
        super(text);
        this.setFont(font);
        this.setForeground(colorText);
        this.setBackground(colorBackground);
        this.setFocusPainted(false);
    }    
}
