package Class;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String name;
    private String email;
    private String phone;
    private List<Student> students;
    private List<Class> classes;

    public Teacher(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.students = new ArrayList<>();
        this.classes = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public void removeStudent(Student student) {
        students.remove(student);
    }
    public void addClass(Class c) {
        classes.add(c);
    }
    public void removeClass(Class c) {
        classes.remove(c);
    }
    public void updateTeacherInfo(String newEmail, String newPhone) {
        this.email = newEmail;
        this.phone = newPhone;
    }
    // Xem thông tin của một học sinh cụ thể
    public void viewStudentInfo(String studentName) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(studentName)) {
                System.out.println("Thông tin học sinh:");
                System.out.println("Tên: " + student.getName());
                System.out.println("Ngày tháng năm sinh: " + student.getBirthDay());
                System.out.println("Email: " + student.getEmail());
                System.out.println("Số điện thoại: " + student.getPhone());
                System.out.println("--------------------------");
                return;
            }
        }
        System.out.println("Không tìm thấy học sinh có tên: " + studentName);
    }
    // Xem thông tin một lớp cụ thể
    public void viewClassInfo(String className) {
        for (Class c : classes) {
            if (c.getClassName().equalsIgnoreCase(className)) {
                System.out.println("Thông tin lớp học:");
                System.out.println("Tên lớp: " + c.getClassName());
                System.out.println("Thời khóa biểu: " + c.getSchedule());
                System.out.println("Số lượng học sinh: " + c.getStudentCount());
                System.out.println("--------------------------");
                return;
            }
        }
        System.out.println("Không tìm thấy lớp học có tên: " + className);
    }
}
