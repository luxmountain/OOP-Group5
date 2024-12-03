package Frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import Models.Teacher;
import application.Database;
import application.Main;

public class TeacherMethod extends JPanel {
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected JTextField searchField;

    public TeacherMethod() {
        setLayout(new BorderLayout());
        addTeacherTable();

        JPanel topPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        JButton searchButton = new JButton("Tìm kiếm");

        JPanel buttonPanel = new JPanel();
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(new JLabel("  ✎  "), BorderLayout.WEST);
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
                    return false; 
                }
            };
        }
   
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);
        Database dtb = new Database();
        String sql = "SELECT id, name, email, phone, birthDate FROM teachers";

        try (PreparedStatement preparedStatement = dtb.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            int i = 1; // Số thứ tự bắt đầu từ 1
            while (resultSet.next()) {
                // Lấy dữ liệu từ ResultSet
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Date birthDate = resultSet.getDate("birthDate");
                // String className = resultSet.getString("className");

                // Format ngày tháng nếu cần
                String formattedBirthDate = new SimpleDateFormat("dd/MM/yyyy").format(birthDate);

                // Thêm vào tableModel
                tableModel.addRow(new Object[]{
                    i,         // Số thứ tự
                    name,      // Tên giáo viên
                    email,     // Email
                    phone,     // Số điện thoại
                    id,        // ID
                    formattedBirthDate // Ngày sinh
                    // className  
                });

                i++; // Tăng số thứ tự
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        JTextField dobField = new JTextField();
        JTextField classField = new JTextField();
   
        Object[] message = {
            "Tên giáo viên:", nameField,
            "Email:", emailField,
            "Số điện thoại:", phoneField,
            "Ngày sinh (dd/MM/yyyy):", dobField,
            "Lớp:", classField,
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
                String dob = dobField.getText().trim();
                String clazz = classField.getText().trim();
   
                // Kiểm tra dữ liệu rỗng
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || dob.isEmpty() || clazz.isEmpty()) {
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
                Database dtb = new Database();
                int newIdNumber = dtb.countAdmins() + 1;
                String newId = String.format("%d", newIdNumber);
                Teacher newTeacher = new Teacher(name, phone, email, newId, birthDate);
                dtb.insertTeacher(name, phone, email, birthDate);
   
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
                    newTeacher.getClazz().getClassName()
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
                Database dtb = new Database();
                dtb.deleteTeacher(selectedRow);
                JOptionPane.showMessageDialog(this, "Xóa hàng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.", "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    }
}