package Frames;

import Frames.my.MyPanel;
import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class Header extends MyPanel {
    public Header() {
        super(1080, 50, Color.WHITE);

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10)); // Căn trái với padding

        addLogo();
    }

    private void addLogo() {
        try {
            URL logoUrl = new URL("https://dhs.ptit.edu.vn/web/image/385-67ae5bc7/Logo_PTIT_University.png");
            ImageIcon logoIcon = new ImageIcon(new ImageIcon(logoUrl).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
            JLabel logoLabel = new JLabel(logoIcon);
            this.add(logoLabel);
        } catch (Exception e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Logo not available");
            errorLabel.setForeground(Color.RED);
            this.add(errorLabel);
        }
    }
}
