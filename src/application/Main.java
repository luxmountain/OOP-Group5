package application;

import java.sql.SQLException;

import Frames.LoginForm;

public class Main {
    public static void main(String[] args) throws SQLException {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginForm();
            // new MainForm();
        });
        Database dtb = new Database();
    }
}
