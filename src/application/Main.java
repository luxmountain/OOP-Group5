package application;

import java.sql.SQLException;
import java.util.ArrayList;

import Frames.LoginForm;
import Models.Admin;

public class Main {
    public static ArrayList<Admin> adminList = new ArrayList<>();
    public static Database database = new Database();
    public static void main(String[] args) throws SQLException {
            javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginForm();
        });
    }
}
