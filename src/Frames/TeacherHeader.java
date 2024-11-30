package Frames;

import java.awt.*;
import javax.swing.SwingConstants;
import Frames.my.*;

public class TeacherHeader extends Header {
    @Override
    protected void addTitle() {
        MyLabel titleLabel = new MyLabel("Teacher", new MyFont(Font.BOLD, 24), Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel, BorderLayout.CENTER);
    }
}
