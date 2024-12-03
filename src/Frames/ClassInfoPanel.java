package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import application.Database;
public class ClassInfoPanel extends JPanel {
    private JTable classTable;
    private DefaultTableModel tableModel;
    private JTextArea classDetailsArea;
    private Database dtb;
    private int selected = -1; // Biến lưu dòng được chọn, mặc định là -1 (không chọn)

    public ClassInfoPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        dtb = new Database();
        // Bảng hiển thị danh sách lớp
        String[] columnNames = {"STT", "Tên Lớp", "Sĩ Số", "Giáo Viên"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa dữ liệu trên bảng
            }
        };

        classTable = new JTable(tableModel);
        classTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Căn lề và định dạng cột
        for (int i = 0; i < classTable.getColumnCount(); i++) {
            if (i == 0 || i == 2) { // Căn giữa cho cột STT và Sĩ Số
                classTable.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        setHorizontalAlignment(SwingConstants.CENTER);
                        return c;
                    }
                });
            }
        }

        // Kích thước cột
        classTable.getColumnModel().getColumn(0).setPreferredWidth(50);  // STT
        classTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Tên Lớp
        classTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Sĩ Số

        // Thêm khả năng sắp xếp
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        classTable.setRowSorter(sorter);

        // Cập nhật lại cột STT khi thay đổi thứ tự
        sorter.addRowSorterListener(e -> {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt(i + 1, classTable.convertRowIndexToView(i), 0);
            }
        });

        // Thêm bảng vào JScrollPane
        JScrollPane tableScrollPane = new JScrollPane(classTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        classDetailsArea = new JTextArea();
        JScrollPane detailsScrollPane = new JScrollPane(classDetailsArea);
        detailsScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Thêm các thành phần vào giao diện
        add(tableScrollPane, BorderLayout.NORTH);
        add(detailsScrollPane, BorderLayout.CENTER);

        // Hiển thị danh sách lớp học
        populateClassTable();

        // Sự kiện khi chọn lớp
        classTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selected = classTable.getSelectedRow(); // Cập nhật dòng được chọn
                if (selected != -1) {
                    selected = classTable.convertRowIndexToModel(selected); // Chuyển về chỉ số model
                }
            }
        });
    }

    protected void populateClassTable() {
        String sql = "SELECT id, class_name FROM classes";

        try (PreparedStatement preparedStatement = dtb.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            int i = 1; // Số thứ tự bắt đầu từ 1
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String className = resultSet.getString("class_name");

                // Thêm dữ liệu vào tableModel
                tableModel.addRow(new Object[]{
                        i,         // Số thứ tự
                        id,        // ID của lớp
                        className  // Tên lớp
                });

                i++; // Tăng số thứ tự
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to populate class table.");
        }
    }

    public int getSelectedRow() {
        return selected; // Trả về dòng được chọn
    }
}
