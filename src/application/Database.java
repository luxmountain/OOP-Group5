package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

public class Database {
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/student";
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

    public void insertAdmin(String name, String phone, String email, Date birthDate, String password) {
        String sql = "INSERT INTO admins (name, phone, email, birthDate, password) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlBirthDate = new java.sql.Date(birthDate.getTime());
            
            // Set parameters in the PreparedStatement (id is not needed as it is auto-incremented)
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, sqlBirthDate);
            preparedStatement.setString(5, password);
    
            // Execute the SQL statement
            preparedStatement.executeUpdate();
            System.out.println("Admin inserted successfully!");
        } catch (SQLException e) {
            System.out.println("ERROR inserting admin.");
            e.printStackTrace();
        }
    }
    
    public void deleteStudent(int studentId) {
        try {
            // Begin transaction
            connection.setAutoCommit(true);
    
            // Delete student from the students table
            String deleteStudentSql = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteStudentSql)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.executeUpdate();
            }
    
            // Commit the transaction
            connection.commit();
            System.out.println("Student and related records deleted successfully.");
        } catch (SQLException e) {
            // Rollback transaction in case of an error
            try {
                connection.rollback();
                System.out.println("Transaction rolled back due to an error.");
            } catch (SQLException ex) {
                System.out.println("Error during rollback.");
                ex.printStackTrace();
            }
            System.out.println("ERROR deleting student.");
            e.printStackTrace();
        } finally {
            // Reset auto-commit mode to default
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit.");
                e.printStackTrace();
            }
        }
    }
    

    public void deleteTeacher(int teacherId) {
        try {
            // Bắt đầu giao dịch (transaction)
            connection.setAutoCommit(true);

            // Xóa học sinh trong các lớp mà giáo viên này dạy
            String deleteStudentsSql = "DELETE FROM students WHERE id IN (SELECT id FROM classes WHERE teacher_id = ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteStudentsSql)) {
                preparedStatement.setInt(1, teacherId);
                preparedStatement.executeUpdate();
            }

            // Xóa các lớp học mà giáo viên này dạy
            String deleteClassesSql = "DELETE FROM classes WHERE teacher_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteClassesSql)) {
                preparedStatement.setInt(1, teacherId);
                preparedStatement.executeUpdate();
            }

            // Xóa giáo viên khỏi bảng teachers
            String deleteTeacherSql = "DELETE FROM teachers WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteTeacherSql)) {
                preparedStatement.setInt(1, teacherId);
                preparedStatement.executeUpdate();
            }

            // Cam kết giao dịch
            connection.commit();
            System.out.println("Teacher and all related records deleted successfully.");
        } catch (SQLException e) {
            // Nếu có lỗi, rollback giao dịch để đảm bảo tính toàn vẹn
            try {
                connection.rollback();
                System.out.println("Transaction rolled back due to an error.");
            } catch (SQLException ex) {
                System.out.println("Error during rollback.");
                ex.printStackTrace();
            }
            System.out.println("ERROR deleting teacher.");
            e.printStackTrace();
        } finally {
            // Đặt lại chế độ auto commit
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto commit.");
                e.printStackTrace();
            }
        }
    }
    
    public void insertStudent(String name, String phone, String email, Date birthDate) {
        String sql = "INSERT INTO students (name, phone, email, birthDate) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlBirthDate = new java.sql.Date(birthDate.getTime());
            
            
            // Set parameters in the PreparedStatement (id is not needed as it is auto-incremented)
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, sqlBirthDate);
    
            // Execute the SQL statement
            preparedStatement.executeUpdate();
            System.out.println("Student inserted successfully!");
        } catch (SQLException e) {
            System.out.println("ERROR inserting student.");
            e.printStackTrace();
        }
    }

    public void insertTeacher(String name, String phone, String email, Date birthDate) {
        String sql = "INSERT INTO teachers (name, phone, email, birthDate) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlBirthDate = new java.sql.Date(birthDate.getTime());
            
            // Set parameters in the PreparedStatement (id is not needed as it is auto-incremented)
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, sqlBirthDate);
    
            // Execute the SQL statement
            preparedStatement.executeUpdate();
            System.out.println("Student inserted successfully!");
        } catch (SQLException e) {
            System.out.println("ERROR inserting admin.");
            e.printStackTrace();
        }
    }
    public void insertClazz(String name, String phone, String email, Date birthDate) {
        String sql = "INSERT INTO classes (name, phone, email, birthDate) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlBirthDate = new java.sql.Date(birthDate.getTime());
            
            // Set parameters in the PreparedStatement (id is not needed as it is auto-incremented)
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, sqlBirthDate);
    
            // Execute the SQL statement
            preparedStatement.executeUpdate();
            System.out.println("Student inserted successfully!");
        } catch (SQLException e) {
            System.out.println("ERROR inserting admin.");
            e.printStackTrace();
        }
    }
    public int countAdmins() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM admins";  // SQL query to count the number of rows in the admins table
    
        try (Statement statement = connection.createStatement()) {
            // Execute the query
            ResultSet resultSet = statement.executeQuery(sql);
    
            // If resultSet has data, get the count
            if (resultSet.next()) {
                count = resultSet.getInt(1);  // The result of COUNT(*) is in the first column
            }
        } catch (SQLException e) {
            System.out.println("ERROR counting admins.");
            e.printStackTrace();
        }
    
        return count;  // Return the total number of rows
    }
    public int countTeachers() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM teachers";  // SQL query to count the number of rows in the admins table
    
        try (Statement statement = connection.createStatement()) {
            // Execute the query
            ResultSet resultSet = statement.executeQuery(sql);
    
            // If resultSet has data, get the count
            if (resultSet.next()) {
                count = resultSet.getInt(1);  // The result of COUNT(*) is in the first column
            }
        } catch (SQLException e) {
            System.out.println("ERROR counting admins.");
            e.printStackTrace();
        }
    
        return count;  // Return the total number of rows
    }
    public int countClazz() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM classes";  // SQL query to count the number of rows in the admins table
    
        try (Statement statement = connection.createStatement()) {
            // Execute the query
            ResultSet resultSet = statement.executeQuery(sql);
    
            // If resultSet has data, get the count
            if (resultSet.next()) {
                count = resultSet.getInt(1);  // The result of COUNT(*) is in the first column
            }
        } catch (SQLException e) {
            System.out.println("ERROR counting admins.");
            e.printStackTrace();
        }
    
        return count;  // Return the total number of rows
    }
    public int countStudents() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM students";  // SQL query to count the number of rows in the admins table
    
        try (Statement statement = connection.createStatement()) {
            // Execute the query
            ResultSet resultSet = statement.executeQuery(sql);
    
            // If resultSet has data, get the count
            if (resultSet.next()) {
                count = resultSet.getInt(1);  // The result of COUNT(*) is in the first column
            }
        } catch (SQLException e) {
            System.out.println("ERROR counting admins.");
            e.printStackTrace();
        }
    
        return count;  // Return the total number of rows
    }
    public Connection getConnection() {
        return connection;
    }
    public Statement getStatement(){
        return statement;
    }

    public String checkLogin(String username, String password) {
        String sql = "SELECT id FROM admins WHERE id = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
    
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Lấy id của admin
                return resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi truy vấn dữ liệu!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null; // Trả về null nếu không tìm thấy admin
    }
    
}