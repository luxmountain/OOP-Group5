package application;

import Frames.LoginForm;

public class Main {
    public static void main(String[] args) {
        // Launch the Login Form
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginForm();
        });
    }
}
