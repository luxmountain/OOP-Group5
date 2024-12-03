package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import application.Main;

public class StudentListFrame extends JFrame {
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentListFrame(int classIndex) {
        // Lấy tên lớp học từ cơ sở dữ liệu
        String className = getClassNameById(classIndex);
    
        // Thiết lập giao diện
        setTitle("Danh Sách Học Sinh - " + className);
        setSize(700, 400);
        setLocationRelativeTo(null);
    
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Tạo bảng hiển thị danh sách học sinh
        String[] columnNames = {"STT", "ID", "Tên Học Sinh", "Năm Sinh", "Email", "Số Điện Thoại"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    
        studentTable = new JTable(tableModel);
    
        // Căn giữa cột STT và ID
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        studentTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // STT
        studentTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // ID
    
        JScrollPane scrollPane = new JScrollPane(studentTable);
    
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel);
    
        // Điền dữ liệu vào bảng
        populateStudentTable(classIndex);
    }
    

    private String getClassNameById(int classId) {
        String sql = "SELECT class_name FROM classes WHERE id = ?";
        try (PreparedStatement preparedStatement = Main.database.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, classId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("class_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching class name.");
        }
        return "Không xác định"; // Giá trị mặc định nếu không tìm thấy
    }
    
    private void populateStudentTable(int classId) {
        int index = 1; // Số thứ tự
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        String sql = "SELECT s.id, s.name, s.birthDate, s.email, s.phone " +
                    "FROM students s " +
                    "WHERE s.class_id = ?";

        try (PreparedStatement preparedStatement = Main.database.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, classId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String studentId = resultSet.getString("id");
                String studentName = resultSet.getString("name");
                String birthDate = resultSet.getDate("birthDate") != null
                        ? yearFormat.format(resultSet.getDate("birthDate"))
                        : "Chưa xác định";
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                tableModel.addRow(new Object[]{
                    index++,      // Số thứ tự
                    studentId,    // ID
                    studentName,  // Tên học sinh
                    birthDate,    // Năm sinh
                    email,        // Email
                    phone         // Số điện thoại
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching student data.");
        }
    }

}
