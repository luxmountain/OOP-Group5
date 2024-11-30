package application;

import java.sql.SQLException;

import Frames.LoginForm;
import Frames.TeacherForm;

public class Main {
    public static void main(String[] args) throws SQLException {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TeacherForm();
            // new MainForm();
        });
        Database dtb = new Database();
    }
}
