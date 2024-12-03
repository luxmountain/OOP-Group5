package Frames;


import java.awt.BorderLayout;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Models.SchoolClass;
import Models.Student;
import Models.Teacher;
import application.Database;
import application.Main;


public class AdminMethod extends JPanel {
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected JTextField searchField;

    public AdminMethod() {
        setLayout(new BorderLayout());
        addTeacherTable();

        JPanel topPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        JButton searchButton = new JButton("Search");

        JPanel buttonPanel = new JPanel();
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);




        topPanel.add(searchPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);




        add(topPanel, BorderLayout.NORTH);
    }


    protected void addTeacherTable() {
        // Khởi tạo tableModel nếu chưa có
        if (tableModel == null) {
            tableModel = new DefaultTableModel(new String[]{"No.", "Fullname", "Email", "Phone", "ID", "Date of birth", "Class"}, 0) {
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


    protected void addTeacher() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField idField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField classField = new JTextField();
     
        Object[] message = {
            "Teacher's name:", nameField,
            "Email:", emailField,
            "Phone number:", phoneField,
            "ID:", idField,
            "Date of birth (dd/MM/yyyy):", dobField,
            "Class:", classField,
        };
    
        boolean isValid = false; // Validity check variable
        while (!isValid) {
            int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Add New Teacher",
                JOptionPane.OK_CANCEL_OPTION
            );
    
            if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                return; // Exit if the user cancels
            }
    
            try {
                // Get data from input fields
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                String id = idField.getText().trim();
                String dob = dobField.getText().trim();
                String clazz = classField.getText().trim();
    
                // Check for empty fields
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || dob.isEmpty() || clazz.isEmpty() || id.isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }
    
                // Check email format
                if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    throw new IllegalArgumentException("Invalid email format.");
                }
    
                // Check phone number format
                if (!phone.matches("^\\d{9,11}$")) {
                    throw new IllegalArgumentException("Invalid phone number format (9-11 digits).");
                }
    
                // Check date of birth format
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);
                Date birthDate = dateFormat.parse(dob);
    
                // Create new teacher object
                SchoolClass newClazz = new SchoolClass(clazz);
                Database dtb = new Database();
                int newIdNumber = dtb.countTeachers() + 1;
                String newId = String.format("%d", newIdNumber);
                Teacher newTeacher = new Teacher(name, phone, email, newId, birthDate);
                
                dtb.insertTeacher(name, phone, email, birthDate);
                // Add teacher to list
                Main.adminList.get(0).getTeachers().add(newTeacher);
    
                // Format birth date before adding to table
                String formattedDob = dateFormat.format(birthDate);
    
                // Add teacher to table
                tableModel.addRow(new Object[]{
                    tableModel.getRowCount() + 1,
                    newTeacher.getName(),
                    newTeacher.getEmail(),
                    newTeacher.getPhone(),
                    newTeacher.getId(),
                    formattedDob,
                    newTeacher.getClazz().getClassName()
                });
    
                // Show success message
                JOptionPane.showMessageDialog(this, "Teacher added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                isValid = true; // Exit loop if data is valid
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Date of birth is not in the correct format (dd/MM/yyyy).", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "An error occurred while adding the teacher.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Name, date of birth, gender, id, phone, email, class, enrollment date
    protected void addStudentTable() {
        // Initialize tableModel if not already done
        if (tableModel == null) {
            tableModel = new DefaultTableModel(new String[]{"No.", "Full Name", "Email", "Phone", "ID", "Date of Birth", "Class", "Enrollment Date"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Only allow editing of "Teacher" column
                }
            };
        }
    
        // Clear old data
        tableModel.setRowCount(0);
    
        // Add data from teacher's class
        SchoolClass className = Main.adminList.get(0).getTeachers().get(0).getClazz();
        List<Student> studentsList = className.getStudentList();
        for (int i = 0; i < studentsList.size(); i++) {
            Student stu = studentsList.get(i);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String formattedBirthDate = dateFormat.format(stu.getBirthDate());
            tableModel.addRow(new Object[]{i + 1, stu.getName(), formattedBirthDate, stu.getId(), stu.getPhone(), stu.getEmail(), className.getClassName()});
        }
    
        // Create JTable
        if (table == null) {
            table = new JTable(tableModel);
        } else {
            table.setModel(tableModel);
        }
    
        // Set column alignment
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i == 0 || i == 3 || i == 4 || i == 5 || i == 6) { // "No.", "Phone", "ID", "Date of Birth"
                table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
                        return c;
                    }
                });
            } else { // Other columns are left-aligned
                table.getColumnModel().getColumn(i).setCellRenderer(new TableCellRendererWithIndent(10));
            }
        }
    
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(30);  // Shrink "No." column
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
    
        // Enable sorting
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
    
        // Disable sorting for "No." column (column 0)
        sorter.setSortable(0, false);
    
        // Update "No." column when row order changes
        sorter.addRowSorterListener(e -> {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt(i + 1, table.convertRowIndexToView(i), 0); // Update "No." value
            }
        });
    
        // Wrap JTable in JScrollPane and add to interface
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    
        // Indent table by 10px on each side
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));  // 10px padding
    }
}
