package application;

import java.sql.SQLException;

import Frames.LoginForm;
import Frames.MainForm;
import Frames.TeacherForm;
import Frames.TeacherMethod;

public class Main {
    public static void main(String[] args) throws SQLException {
        javax.swing.SwingUtilities.invokeLater(() -> {
            //new LoginForm();
            // new MainForm();
            new TeacherForm();
        });
        Database dtb = new Database();
    }
}
