package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Database {
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/studentswing";
    private Statement statement;
    private Connection connection;
    public Database(){
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
            System.out.println("SUCCESS");
        } catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public void insertAdmin(String id, String name, String phone, String email, Date birthDate, String password) {
        String sql = "INSERT INTO admins (id, name, phone, email, birthDate, password) VALUES (?, ?, ?, ?, ?, ?)";
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlBirthDate = new java.sql.Date(birthDate.getTime());
    
            // Set parameters in the PreparedStatement
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, email);
            preparedStatement.setDate(5, sqlBirthDate); // Use java.sql.Date here
            preparedStatement.setString(6, password);
    
            // Execute the SQL statement
            preparedStatement.executeUpdate();
            System.out.println("Admin inserted successfully!");
        } catch (SQLException e) {
            System.out.println("ERROR inserting admin.");
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    public Statement getStatement(){
        return statement;
    }
}