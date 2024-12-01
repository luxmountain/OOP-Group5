package application;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Frames.LoginForm;
import Frames.TeacherForm;
import Models.Admin;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("hg");
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Admin admin = new Admin("Vu", "admin", "09089090", "vâ@gmail.com", "001", dateFormat.parse("01/12/2024"));
            System.out.println(admin);
            javax.swing.SwingUtilities.invokeLater(() -> {
                new LoginForm();
            });
            // Database dtb = new Database();
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
    }
}
