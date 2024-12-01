package application;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Frames.LoginForm;
import Models.Admin;

public class Main {
    public static ArrayList<Admin> adminList = new ArrayList<>();
    public static long IDX = 0;
    public static void main(String[] args) throws SQLException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            adminList.add(new Admin("Vu", "09089090", "va@gmail.com", String.format("%d", ++IDX), dateFormat.parse("01/12/2024")));
            javax.swing.SwingUtilities.invokeLater(() -> {
                System.out.println(adminList.get(0).getAccount().getUserID());
                System.out.println(adminList.get(0).getAccount().getPassword());
                new LoginForm();
            });
            // Database dtb = new Database();
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
    }
}
