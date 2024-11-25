import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/my_database";
        String user = "root";
        String password = ""; 

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM my_table"; 
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name")); 
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
