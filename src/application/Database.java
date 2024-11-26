package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/test";
    private Statement statement ;
    public Database(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
            System.out.println("SUCCESS");
        } catch (SQLException e) {
            System.out.println("DIJTME");
            e.printStackTrace();
        }
    }
    public Statement getStatement(){
        return statement;
    }
}