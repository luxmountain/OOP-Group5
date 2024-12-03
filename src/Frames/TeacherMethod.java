package Frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Models.SchoolClass;
import Models.Student;
import Models.Teacher;
import application.Main;

public class TeacherMethod extends JPanel {
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected JTextField searchField;

    public TeacherMethod() {
        setLayout(new BorderLayout());
        addTeacherTable();

        // Tạo sorter cho bảng
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        // Panel phía trên
        JPanel topPanel = new JPanel(new BorderLayout());

        // Thanh tìm kiếm
        searchField = new JTextField();
        JButton searchButton = new JButton("Tìm kiếm");

        // Panel chứa thanh tìm kiếm
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(new JLabel("  ✎  "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // Panel chứa nút (thêm, sửa, xóa nếu có)
        JPanel buttonPanel = new JPanel(); // Có thể thêm nút khác vào đây nếu cần

        // Thêm các panel vào topPanel
        topPanel.add(searchPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Thêm topPanel vào phía trên của giao diện chính
        add(topPanel, BorderLayout.NORTH);

        // Thêm chức năng tìm kiếm
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchField.getText().trim();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null); // Hiển thị toàn bộ dữ liệu nếu chuỗi tìm kiếm rỗng
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text)); // Không phân biệt hoa thường
                    } catch (PatternSyntaxException pse) {
                        JOptionPane.showMessageDialog(TeacherMethod.this, "Invalid search pattern!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
        
                // Cập nhật lại cột STT
                for (int i = 0; i < table.getRowCount(); i++) {
                    tableModel.setValueAt(i + 1, table.convertRowIndexToModel(i), 0); // Cập nhật giá trị STT
                }
            }
        });
    }

    protected void addTeacherTable() {
        // Khởi tạo tableModel nếu chưa có
        if (tableModel == null) {
            tableModel = new DefaultTableModel(new String[]{"STT", "Họ và tên", "Email", "SĐT", "ID", "Ngày sinh", "Lớp"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; 
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

    protected void addTeacher() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField idField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField classField = new JTextField();
    
        // Tạo các nút radio cho giới tính
        JRadioButton maleButton = new JRadioButton("Nam");
        JRadioButton femaleButton = new JRadioButton("Nữ");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
    
        // Tùy chỉnh kích thước cho các trường nhập liệu
        Dimension inputSize = new Dimension(150, 25);
        nameField.setPreferredSize(inputSize);
        emailField.setPreferredSize(inputSize);
        phoneField.setPreferredSize(inputSize);
        idField.setPreferredSize(inputSize);
        dobField.setPreferredSize(inputSize);
        classField.setPreferredSize(inputSize);
    
        // Tùy chỉnh kích thước cho Panel chứa các nút radio
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderPanel.setPreferredSize(new Dimension(200, 30)); // Kích thước tùy chỉnh
    
        Object[] message = {
            "Tên giáo viên:", nameField,
            "Email:", emailField,
            "Số điện thoại:", phoneField,
            "ID:", idField,
            "Ngày sinh (dd/MM/yyyy):", dobField,
            "Lớp:", classField,
            "Giới tính:", genderPanel
        };
    
        boolean isValid = false; // Biến kiểm tra tính hợp lệ
        while (!isValid) {
            int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Thêm giáo viên mới",
                JOptionPane.OK_CANCEL_OPTION
            );
    
            if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                return; // Thoát nếu người dùng hủy bỏ
            }
    
            try {
                // Lấy dữ liệu từ các trường nhập
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                String id = idField.getText().trim();
                String dob = dobField.getText().trim();
                String clazz = classField.getText().trim();
                String gender = maleButton.isSelected() ? "Nam" : femaleButton.isSelected() ? "Nữ" : null;
    
                // Kiểm tra dữ liệu rỗng
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || dob.isEmpty() || clazz.isEmpty() || id.isEmpty() || gender == null) {
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
                    tableModel.getRowCount() + 1,
                    newTeacher.getName(),
                    newTeacher.getEmail(),
                    newTeacher.getPhone(),
                    newTeacher.getId(),
                    formattedDob,
                    newTeacher.getClazz().getClassName(),
                    //newTeacher.getGender() // Cột giới tính
                });
    
                // Hiển thị thông báo thành công
                JOptionPane.showMessageDialog(this, "Thêm giáo viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                isValid = true; // Thoát vòng lặp nếu dữ liệu hợp lệ
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng (dd/MM/yyyy).", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm giáo viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    protected void editTeacher(){
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) { // Kiểm tra nếu có hàng được chọn
            // Chuyển từ view row sang model row
            int modelRow = table.convertRowIndexToModel(selectedRow);
            
            // Lấy dữ liệu từ dòng đã chọn
            String name = (String) tableModel.getValueAt(modelRow, 1);
            String email = (String) tableModel.getValueAt(modelRow, 2);
            String phone = (String) tableModel.getValueAt(modelRow, 3);
            String id = (String) tableModel.getValueAt(modelRow, 4);
            String dob = (String) tableModel.getValueAt(modelRow, 5);
            String clazz = (String) tableModel.getValueAt(modelRow, 6);
            
            // Tạo các trường nhập liệu
            JTextField nameField = new JTextField(name);
            JTextField emailField = new JTextField(email);
            JTextField phoneField = new JTextField(phone);
            JTextField idField = new JTextField(id);
            JTextField dobField = new JTextField(dob);
            JTextField classField = new JTextField(clazz);
            
            // Tạo các nút radio cho giới tính (có thể thêm giới tính vào bảng nếu cần)
            JRadioButton maleButton = new JRadioButton("Nam");
            JRadioButton femaleButton = new JRadioButton("Nữ");
            ButtonGroup genderGroup = new ButtonGroup();
            genderGroup.add(maleButton);
            genderGroup.add(femaleButton);
    
            // Tùy chỉnh kích thước cho các trường nhập liệu
            Dimension inputSize = new Dimension(150, 25);
            nameField.setPreferredSize(inputSize);
            emailField.setPreferredSize(inputSize);
            phoneField.setPreferredSize(inputSize);
            idField.setPreferredSize(inputSize);
            dobField.setPreferredSize(inputSize);
            classField.setPreferredSize(inputSize);
    
            // Tùy chỉnh kích thước cho Panel chứa các nút radio
            JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            genderPanel.add(maleButton);
            genderPanel.add(femaleButton);
            genderPanel.setPreferredSize(new Dimension(200, 30));
    
            // Set the selected gender
            // If gender data is available, pre-select the gender radio button
            // You can use a method like teacher.getGender() or any other field to check gender
            // For now, assuming gender is "Nam" or "Nữ"
            // if ("Nam".equals(gender)) {
            //     maleButton.setSelected(true);
            // } else {
            //     femaleButton.setSelected(true);
            // }
    
            Object[] message = {
                "Tên giáo viên:", nameField,
                "Email:", emailField,
                "Số điện thoại:", phoneField,
                "ID:", idField,
                "Ngày sinh (dd/MM/yyyy):", dobField,
                "Lớp:", classField,
                "Giới tính:", genderPanel
            };
    
            boolean isValid = false; // Biến kiểm tra tính hợp lệ
            while (!isValid) {
                int option = JOptionPane.showConfirmDialog(
                    this,
                    message,
                    "Chỉnh sửa thông tin giáo viên",
                    JOptionPane.OK_CANCEL_OPTION
                );
    
                if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                    return; // Thoát nếu người dùng hủy bỏ
                }
    
                try {
                    // Lấy dữ liệu từ các trường nhập
                    String nameUpdated = nameField.getText().trim();
                    String emailUpdated = emailField.getText().trim();
                    String phoneUpdated = phoneField.getText().trim();
                    String idUpdated = idField.getText().trim();
                    String dobUpdated = dobField.getText().trim();
                    String clazzUpdated = classField.getText().trim();
                    //String genderUpdated = maleButton.isSelected() ? "Nam" : femaleButton.isSelected() ? "Nữ" : null;
    
                    // Kiểm tra dữ liệu rỗng
                    if (nameUpdated.isEmpty() || emailUpdated.isEmpty() || phoneUpdated.isEmpty() || dobUpdated.isEmpty() || clazzUpdated.isEmpty() || idUpdated.isEmpty()) {
                        throw new IllegalArgumentException("Tất cả các trường đều phải được điền.");
                    }
    
                    // Kiểm tra định dạng email
                    if (!emailUpdated.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                        throw new IllegalArgumentException("Email không hợp lệ.");
                    }
    
                    // Kiểm tra định dạng số điện thoại
                    if (!phoneUpdated.matches("^\\d{9,11}$")) {
                        throw new IllegalArgumentException("Số điện thoại không hợp lệ (9-11 chữ số).");
                    }
    
                    // Kiểm tra định dạng ngày sinh
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    dateFormat.setLenient(false);
                    Date birthDateUpdated = dateFormat.parse(dobUpdated);
    
                    // Cập nhật thông tin giáo viên
                    Teacher updatedTeacher = Main.adminList.get(0).getTeachers().get(modelRow);
                    updatedTeacher.setName(nameUpdated);
                    updatedTeacher.setEmail(emailUpdated);
                    updatedTeacher.setPhone(phoneUpdated);
                    updatedTeacher.setId(idUpdated);
                    updatedTeacher.setBirthDate(birthDateUpdated);
                    updatedTeacher.getClazz().setClassName(clazzUpdated);
                    // You can update gender if necessary as well
    
                    // Cập nhật lại dữ liệu trong bảng
                    tableModel.setValueAt(nameUpdated, modelRow, 1);
                    tableModel.setValueAt(emailUpdated, modelRow, 2);
                    tableModel.setValueAt(phoneUpdated, modelRow, 3);
                    tableModel.setValueAt(idUpdated, modelRow, 4);
                    tableModel.setValueAt(dobUpdated, modelRow, 5);
                    tableModel.setValueAt(clazzUpdated, modelRow, 6);
    
                    // Hiển thị thông báo thành công
                    JOptionPane.showMessageDialog(this, "Chỉnh sửa thông tin giáo viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    isValid = true; // Thoát vòng lặp nếu dữ liệu hợp lệ
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng (dd/MM/yyyy).", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi chỉnh sửa giáo viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}