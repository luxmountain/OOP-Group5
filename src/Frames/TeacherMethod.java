// package Frames;

// import java.awt.*;
// import java.awt.event.*;
// import java.util.regex.*;
// import javax.swing.*;
// import javax.swing.table.*;
// import Frames.my.MyPanel;

// public class TeacherMethod extends MyPanel {
//     private JTable table;
//     private DefaultTableModel model;

//     public TeacherMethod() {
//         // Set up the panel layout
//         setLayout(new BorderLayout());

//         // Table data and columns
//         Object rows[][] = {
//             {"Adithya", "Content Developer", 25000},
//             {"Jai", "SME", 30000},
//             {"Chaitanya", "Java Engineer", 45000},
//             {"Ramesh", "Scala Developer", 40000},
//             {"Ravi", "SAP Consultant", 70000}
//         };
//         Object columns[] = {"Name", "Designation", "Salary"};

//         // Create table model
//         model = new DefaultTableModel(rows, columns) {
//             @Override
//             public Class<?> getColumnClass(int column) {
//                 if (column >= 0 && column < getColumnCount()) {
//                     return getValueAt(0, column).getClass();
//                 } else {
//                     return Object.class;
//                 }
//             }
//         };

//         // Initialize table
//         table = new JTable(model);
//         TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
//         table.setRowSorter(sorter);

//         // Add table to the panel
//         add(new JScrollPane(table), BorderLayout.CENTER);

//         // Filter panel
//         JPanel filterPanel = new JPanel(new BorderLayout());
//         JLabel label = new JLabel("Filter:");
//         filterPanel.add(label, BorderLayout.WEST);
//         JTextField filterText = new JTextField();
//         filterPanel.add(filterText, BorderLayout.CENTER);

//         add(filterPanel, BorderLayout.NORTH);

//         // Filter button
//         JButton filterButton = new JButton("Filter");
//         filterButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String text = filterText.getText();
//                 if (text.isEmpty()) {
//                     sorter.setRowFilter(null);
//                 } else {
//                     try {
//                         sorter.setRowFilter(RowFilter.regexFilter(text));
//                     } catch (PatternSyntaxException pse) {
//                         JOptionPane.showMessageDialog(
//                             TeacherMethod.this, // Referencing the current panel
//                             "Invalid regex pattern.",
//                             "Error",
//                             JOptionPane.ERROR_MESSAGE
//                         );
//                     }
//                 }
//             }
//         });

//         JPanel buttonPanel = new JPanel();
//         buttonPanel.add(filterButton);
//         add(buttonPanel, BorderLayout.SOUTH);
//     }
// }

package Frames;

import Models.Student;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class TeacherMethod extends JPanel {
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected static ArrayList<Student> studentList;
    protected JTextField searchField;

    public TeacherMethod() {
        setLayout(new BorderLayout());

        studentList = new ArrayList<>();
        addTable();

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

    protected void addTable() {
        // Create the table model with the column names
        tableModel = new DefaultTableModel(new String[]{"STT", "Lớp", "Sĩ số"}, 0);
        table = new JTable(tableModel);
    
        // Set up the table sorter
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel) {
            @Override
            public boolean isSortable(int column) {
                // Disable sorting for the STT column (column index 0)
                return column != 0;
            }
        };
        table.setRowSorter(sorter);
    
        // Add listener to the table header
        JTableHeader header = table.getTableHeader();
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int columnIndex = header.columnAtPoint(e.getPoint()); // Get the clicked column index
                if (columnIndex != -1 && sorter.isSortable(columnIndex)) {
                    // Toggle sort order for the clicked column if sortable
                    sorter.toggleSortOrder(columnIndex);
                }
            }
        });
    
        // Wrap the table in a scroll pane and add it to the layout
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
    

    protected void addStudent() {
        JTextField nameField = new JTextField();
        JTextField birthDateField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] message = {
                "Tên lớp", nameField,
                "Ngày sinh (dd/mm/yyyy):", birthDateField,
                "SĐT:", phoneField,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Thêm học viên mới", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String birthDateStr = birthDateField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            try {
                DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
                Date birthDate = dateFormat.parse(birthDateStr); 

                Student newStudent = new Student(name, phone, email, String.valueOf(studentList.size() + 1), birthDate);
                studentList.add(newStudent);
                tableModel.addRow(new Object[]{studentList.size(), name, dateFormat.format(birthDate), phone, email});
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Định dạng ngày sinh không hợp lệ. Vui lòng sử dụng định dạng dd/mm/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

        JTextField nameField = new JTextField(selectedStudent.getName());
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        JFormattedTextField birthDateField = new JFormattedTextField(dateFormat);
        birthDateField.setValue(selectedStudent.getBirthDate());
        JTextField phoneField = new JTextField(selectedStudent.getPhone());
        JTextField emailField = new JTextField(selectedStudent.getEmail());

        Object[] message = {
                "Tên học viên:", nameField,
                "Ngày sinh:", birthDateField,
                "SĐT:", phoneField,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Sửa học viên", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            selectedStudent.setName(nameField.getText());
            try {
                selectedStudent.setBirthDate(dateFormat.parse(birthDateField.getText()));
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Định dạng ngày sinh không hợp lệ. Vui lòng sử dụng định dạng dd/mm/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Không cập nhật nếu ngày sinh không hợp lệ
            }
            selectedStudent.setPhone(phoneField.getText());
            selectedStudent.setEmail(emailField.getText());

            tableModel.setValueAt(selectedStudent.getName(), selectedRow, 1);
            tableModel.setValueAt(dateFormat.format(selectedStudent.getBirthDate()), selectedRow, 2);
            tableModel.setValueAt(selectedStudent.getPhone(), selectedRow, 3);
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
                    student.getPhone().toLowerCase().contains(keyword) ||
                    student.getEmail().toLowerCase().contains(keyword)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy"); // Định dạng ngày tháng năm
                String formattedBirthDate = dateFormat.format(student.getBirthDate());
                searchTableModel.addRow(new Object[]{
                        studentList.indexOf(student) + 1,
                        student.getName(),
                        formattedBirthDate,
                        student.getPhone(),
                        student.getEmail()
                });
            }
        }

        table.setModel(searchTableModel);
    }
}