package Frames;

import Models.SchoolClass;
import Models.Student;
import Models.Teacher;
import application.Main;

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

public class AdminMethod extends JPanel {
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected static ArrayList<Student> studentList;
    protected JTextField searchField;

    public AdminMethod() {
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