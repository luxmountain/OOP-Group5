package Frames;

import java.awt.BorderLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Models.SchoolClass;
import Models.Student;
import Models.Teacher;
import application.Main;

public class AdminMethod extends JPanel {
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected static ArrayList<Student> studentList;
    protected JTextField searchField;

    public AdminMethod() {
        setLayout(new BorderLayout());

        studentList = new ArrayList<>();
        addTeacherTable();

        JPanel topPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        JButton searchButton = new JButton("Tìm kiếm");

        JPanel buttonPanel = new JPanel();
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(new JLabel("Tìm kiếm: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        topPanel.add(searchPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
    }

    protected void addTeacherTable() {
        // Danh sách giáo viên
        List<Teacher> teacherList = Main.adminList.get(0).getTeachers();

        // Tạo DefaultTableModel và chỉ cho phép chỉnh sửa cột "Giáo viên" (cột 1)
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"STT", "Giáo viên", "Lớp"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép chỉnh sửa cột "Giáo viên" (cột 1)
                return column == 1;
            }
        };

        // Thêm dữ liệu từ danh sách giáo viên vào bảng
        for (int i = 0; i < teacherList.size(); i++) {
            Teacher teacher = teacherList.get(i);
            tableModel.addRow(new Object[]{i + 1, teacher.getName(), teacher.getClazz().getClassName()});
        }

        // Tạo JTable
        JTable table = new JTable(tableModel);

        // Lắng nghe thay đổi trong bảng và cập nhật danh sách giáo viên
        tableModel.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 1) { // Chỉ xử lý thay đổi ở cột "Giáo viên"
                String newName = tableModel.getValueAt(row, column).toString();

                // Cập nhật tên giáo viên trong danh sách
                Teacher updatedTeacher = teacherList.get(row);
                updatedTeacher.setName(newName);
                System.out.println(updatedTeacher.getName());

                JOptionPane.showMessageDialog(table, "Tên giáo viên đã được cập nhật thành: " + newName, "Cập nhật thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Bọc JTable trong JScrollPane và thêm vào giao diện
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }


    protected void addClass() {
        JTextField nameClass = new JTextField();

        Object[] message = {
                "Tên lớp", nameClass,
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Thêm lớp mới", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameClass.getText();
 
            try {
                Teacher teacher = Main.adminList.get(0).getTeachers().get(0);
                String nameTeacher = teacher.getName();
                String totalStu = "10";
                SchoolClass newClazz = new SchoolClass(name);
                // System.out.println(schoolClass.getClassName());
                // Student newStudent = new Student(name, totalStu, email, String.valueOf(studentList.size() + 1), birthDate);
                // studentList.add(newStudent);
                tableModel.addRow(new Object[]{studentList.size(), name, totalStu});
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Định dạng ngày sinh không hợp lệ. Vui lòng sử dụng định dạng dd/mm/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected void deleteTeacher() {}
        protected void updateTeacher() {}

    protected void addTeacher() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField classField = new JTextField();
    
        Object[] message = {
                "Tên giáo viên:", nameField,
                "Email:", emailField,
                "Số điện thoại:", phoneField,
                "Ngày sinh (dd/mm/yyyy):", dobField,
                "Lớp", classField,
        };
    
        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Thêm giáo viên mới",
                JOptionPane.OK_CANCEL_OPTION
        );
    
        if (option == JOptionPane.OK_OPTION) {
            try {
                // Lấy dữ liệu từ JTextField
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String dob = dobField.getText();
                String clazz = classField.getText();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                // Kiểm tra tính hợp lệ của dữ liệu
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || dob.isEmpty()) {
                    throw new IllegalArgumentException("Tất cả các trường đều phải được điền.");
                }
                SchoolClass newClazz = new SchoolClass(clazz);
                // Tạo đối tượng Teacher
                Teacher newTeacher = new Teacher(name, email, phone, String.format("%d", ++Main.IDX), dateFormat.parse(dob), newClazz);
    
                // Thêm giáo viên vào danh sách quản lý (ví dụ: danh sách adminList)
                Main.adminList.get(0).getTeachers().add(newTeacher);
    
                // Thêm thông tin vào bảng giao diện
                tableModel.addRow(new Object[]{
                        Main.adminList.get(0).getTeachers().size(),
                        newTeacher.getName(),
                        newTeacher.getClazz().getClassName(),
                        newTeacher.getEmail(),
                        newTeacher.getPhone(),
                        newTeacher.getBirthDate()
                });
                // Thông báo thành công
                JOptionPane.showMessageDialog(this, "Thêm giáo viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm giáo viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    protected void editStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một học viên để sửa.");
            return;
        }

        Student selectedStudent = studentList.get(selectedRow);

        JTextField nameClass = new JTextField(selectedStudent.getName());
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        JFormattedTextField nameTeacher = new JFormattedTextField(dateFormat);
        nameTeacher.setValue(selectedStudent.getBirthDate());
        //JTextField totalStu = new JTextField(selectedStudent.gettotalStu());
        JTextField emailField = new JTextField(selectedStudent.getEmail());

        Object[] message = {
                "Tên học viên:", nameClass,
                "Ngày sinh:", nameTeacher,
                //"SĐT:", totalStu,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Sửa học viên", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            selectedStudent.setName(nameClass.getText());
            try {
                selectedStudent.setBirthDate(dateFormat.parse(nameTeacher.getText()));
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Định dạng ngày sinh không hợp lệ. Vui lòng sử dụng định dạng dd/mm/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Không cập nhật nếu ngày sinh không hợp lệ
            }
            //selectedStudent.settotalStu(totalStu.getText());
            selectedStudent.setEmail(emailField.getText());

            tableModel.setValueAt(selectedStudent.getName(), selectedRow, 1);
            tableModel.setValueAt(dateFormat.format(selectedStudent.getBirthDate()), selectedRow, 2);
            //tableModel.setValueAt(selectedStudent.gettotalStu(), selectedRow, 3);
            tableModel.setValueAt(selectedStudent.getEmail(), selectedRow, 4);
        }
    }

    protected void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một học viên để xóa.");
            return;
        }

        studentList.remove(selectedRow);
        tableModel.removeRow(selectedRow);
    }

    protected void searchStudent() {
        String keyword = searchField.getText().toLowerCase();
        DefaultTableModel searchTableModel = new DefaultTableModel(new String[]{"STT", "Tên học viên", "Ngày sinh", "SĐT", "Email"}, 0);

        for (Student student : studentList) {
            if (student.getName().toLowerCase().contains(keyword) ||
                    //student.gettotalStu().toLowerCase().contains(keyword) ||
                    student.getEmail().toLowerCase().contains(keyword)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy"); // Định dạng ngày tháng năm
                String formattedBirthDate = dateFormat.format(student.getBirthDate());
                searchTableModel.addRow(new Object[]{
                        studentList.indexOf(student) + 1,
                        student.getName(),
                        formattedBirthDate,
                        //student.gettotalStu(),
                        student.getEmail()
                });
            }
        }

        table.setModel(searchTableModel);
    }
}