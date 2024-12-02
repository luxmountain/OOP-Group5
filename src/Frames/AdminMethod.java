package Frames;


import java.awt.BorderLayout;
import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


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
        // Khởi tạo tableModel nếu chưa có
        if (tableModel == null) {
            tableModel = new DefaultTableModel(new String[]{"STT", "Họ và tên", "Email", "SĐT", "ID", "Ngày sinh", "Lớp"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Chỉ cho phép chỉnh sửa cột "Giáo viên"

                }
            };
        }
   
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);
   
        // Thêm dữ liệu từ danh sách giáo viên
        List<Teacher> teacherList = Main.adminList.get(0).getTeachers();
        for (int i = 0; i < teacherList.size(); i++) {
            Teacher teacher = teacherList.get(i);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String formattedBirthDate = dateFormat.format(teacher.getBirthDate());
            tableModel.addRow(new Object[]{i + 1, teacher.getName(), teacher.getEmail(), teacher.getPhone(), teacher.getId(), formattedBirthDate, teacher.getClazz().getClassName()});
        }
   
        // Tạo JTable
        if (table == null) {
            table = new JTable(tableModel);
        } else {
            table.setModel(tableModel);
        }


        // Cài đặt căn lề cho các cột
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i == 0 || i == 3 || i == 4 || i == 5 || i == 6) { // Cột "STT", "SĐT", "ID", "Ngày sinh"
                table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        setHorizontalAlignment(SwingConstants.CENTER); // Căn lề giữa
                        return c;
                    }
                });
            } else { // Các cột còn lại sẽ căn lề trái
                table.getColumnModel().getColumn(i).setCellRenderer(new TableCellRendererWithIndent(10));
            }
        }


         // Thiết lập độ rộng các cột
        table.getColumnModel().getColumn(0).setPreferredWidth(30);  // Thu nhỏ cột "STT" đi 20
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
   
        // Thêm khả năng sắp xếp
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
   
        // Vô hiệu hóa sắp xếp cho cột "STT" (cột 0)
        sorter.setSortable(0, false);
   
        // Cập nhật lại cột STT khi thay đổi thứ tự
        sorter.addRowSorterListener(e -> {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt(i + 1, table.convertRowIndexToView(i), 0); // Cập nhật giá trị STT
            }
        });
   
        // Bọc JTable trong JScrollPane và thêm vào giao diện
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        // Lùi table vào mỗi bên 10
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));  // Lùi vào 10px ở mỗi bên
   
        // Lắng nghe thay đổi trong bảng
        // tableModel.addTableModelListener(e -> {
        //     int row = e.getFirstRow();
        //     int column = e.getColumn();
        //     if (column == 1) { // Chỉ xử lý thay đổi ở cột "Giáo viên"
        //         String newName = tableModel.getValueAt(row, column).toString();
        //         Teacher updatedTeacher = teacherList.get(table.convertRowIndexToModel(row));
        //         updatedTeacher.setName(newName);
        //         JOptionPane.showMessageDialog(table, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        //     }
        // });
    }
   
   // Tạo lớp TableCellRendererWithIndent để lùi vào các cột
    class TableCellRendererWithIndent extends DefaultTableCellRenderer {
        private int indent;


        public TableCellRendererWithIndent(int indent) {
            this.indent = indent;
        }


        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(SwingConstants.LEFT); // Căn trái
            setBorder(BorderFactory.createEmptyBorder(0, indent, 0, 0)); // Lùi vào 10px từ trái
            return c;
        }
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


    protected void deleteTeacher() {
            // Lấy chỉ số hàng được chọn
            int selectedRow = table.getSelectedRow();
        
            if (selectedRow != -1) { // Kiểm tra nếu có hàng được chọn
                // Xác nhận từ người dùng trước khi xóa
                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xóa hàng này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION
                );
        
                if (confirm == JOptionPane.YES_OPTION) {
                    // Xóa hàng khỏi tableModel
                    int modelRow = table.convertRowIndexToModel(selectedRow); // Chuyển từ view row sang model row
                    tableModel.removeRow(modelRow);
        
                    // Cập nhật lại STT sau khi xóa
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        tableModel.setValueAt(i + 1, i, 0); // Cột STT là cột 0
                    }
        
                    JOptionPane.showMessageDialog(this, "Xóa hàng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            }

    }


    protected void updateTeacher() {}


    protected void addTeacher() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField idField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField classField = new JTextField();
   
        Object[] message = {
            "Tên giáo viên:", nameField,
            "Email:", emailField,
            "Số điện thoại:", phoneField,
            "ID:", idField,
            "Ngày sinh (dd/MM/yyyy):", dobField,
            "Lớp:", classField,
        };

        int option = JOptionPane.showConfirmDialog(
            this,
            message,
            "Thêm giáo viên mới",
            JOptionPane.OK_CANCEL_OPTION
        );
   
        if (option == JOptionPane.OK_OPTION) {
            try {
                // Lấy dữ liệu từ các trường nhập
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                String id = idField.getText().trim();
                String dob = dobField.getText().trim();
                String clazz = classField.getText().trim();
    
                // Kiểm tra dữ liệu rỗng
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || dob.isEmpty() || clazz.isEmpty() || id.isEmpty()) {
                    throw new IllegalArgumentException("Tất cả các trường đều phải được điền.");
                }
    
                // Kiểm tra định dạng email
                if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    throw new IllegalArgumentException("Email không hợp lệ.");
                }
    
                // Kiểm tra định dạng số điện thoại
                if (!phone.matches("^\\d{9,11}$")) {
                    throw new IllegalArgumentException("Số điện thoại không hợp lệ (9-11 chữ số).");
                }
    
                // Kiểm tra định dạng ngày sinh
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);
                Date birthDate = dateFormat.parse(dob);
    
                // Tạo đối tượng giáo viên mới
                SchoolClass newClazz = new SchoolClass(clazz);
                Teacher newTeacher = new Teacher(name, phone, email, id, birthDate, newClazz);
    
                // Thêm giáo viên vào danh sách
                Main.adminList.get(0).getTeachers().add(newTeacher);
    
                // Định dạng ngày sinh trước khi thêm vào bảng
                String formattedDob = dateFormat.format(birthDate);
    
                // Thêm giáo viên vào bảng
                tableModel.addRow(new Object[]{
                    tableModel.getRowCount() + 1, // STT

                    newTeacher.getName(),
                    newTeacher.getEmail(),
                    newTeacher.getPhone(),
                    newTeacher.getId(),
                    formattedDob,
                    newTeacher.getClazz().getClassName()
                });
    
                // Hiển thị thông báo thành công
                JOptionPane.showMessageDialog(this, "Thêm giáo viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
    
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng (dd/MM/yyyy).", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
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
