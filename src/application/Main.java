package application;

import Frames.MainForm;

public class Main {
    public static void main(String[] args) {
        // Launch the Login Form
        javax.swing.SwingUtilities.invokeLater(() -> {
            // new LoginForm();
            new MainForm();
        });
    }
}
