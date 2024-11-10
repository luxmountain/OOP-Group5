package Frames;

import Frames.my.MyButton;
import Frames.my.MyFont;
import Frames.my.MyPanel;

import java.awt.*;
import javax.swing.*;

public class DashBoard extends MyPanel{
    public DashBoard(){
        super(200, 720, Color.GRAY);

        MyButton studentBtn = createBtn("Students");
        MyButton courseBtn  = createBtn("Courses");
        MyButton teacherBtn = createBtn("Teachers");

        this.add(studentBtn);
        this.add(courseBtn);
        this.add(teacherBtn);
    }
    private MyButton createBtn(String text){
        MyFont fontBtn = new MyFont(Font.BOLD, 18);
        Color colorTextBtn = Color.WHITE;
        Color colorBackgroundBtn = Color.BLUE;
        MyButton button = new MyButton(text, fontBtn, colorTextBtn, colorBackgroundBtn);
        return button;
    }
}