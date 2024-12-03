package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Models.Admin;
import Models.SchoolClass;
import Models.Student;
import Models.Teacher;
import application.Main;

public class StudentListFrame extends JFrame {
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentListFrame(int classIndex) {
    Admin admin = Main.adminList.get(0);
    
    if (classIndex < 0 || classIndex >= admin.getTeachers().size()) {
        throw new IllegalArgumentException("Index lớp không hợp lệ.");
    }

    Teacher teacher = admin.getTeachers().get(classIndex);
    SchoolClass schoolClass = teacher.getClazz();
    if (schoolClass == null) {
        throw new IllegalStateException("Không có thông tin lớp học cho giáo viên này.");
    }

    // Thiết lập giao diện
    setTitle("Danh Sách Học Sinh - " + schoolClass.getClassName());
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
    populateStudentTable();
}

   private void populateStudentTable() {
    int index = 1; // Số thứ tự
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    // Lấy danh sách giáo viên từ Admin đầu tiên trong Main.adminList
    Admin admin = Main.adminList.get(0);
    for (Teacher teacher : admin.getTeachers()) {
        SchoolClass schoolClass = teacher.getClazz();
        if (schoolClass != null) {
            for (Student student : schoolClass.getStudentList()) {
                String birthYear = student.getBirthDate() != null ? yearFormat.format(student.getBirthDate()) : "Chưa xác định";
                tableModel.addRow(new Object[]{
                    index++,              // STT
                    student.getId(),      // ID
                    student.getName(),    // Tên học sinh
                    birthYear,            // Năm sinh
                    student.getEmail(),   // Email
                    student.getPhone() // Số điện thoại
                });
            }
        }
    }
}


}
