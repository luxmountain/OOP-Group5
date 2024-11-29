package Models;

import java.util.ArrayList;
import java.util.Date;

public class Teacher extends Person {
    // Danh sách lưu trữ thông tin lớp học và sinh viên
    private ArrayList<String> classList; // Danh sách các lớp
    private ArrayList<String> studentList; // Danh sách sinh viên

    // Constructor
    public Teacher(String name, String className, String role, String phone, String email, String id, Date birthDate) {
        super(name, className, role, phone, email, id, birthDate);
        this.classList = new ArrayList<>();
        this.studentList = new ArrayList<>();
    }

    // Phương thức CRUD lớp học
    public void addClass(String className) {
        classList.add(className);
        System.out.println("Class " + className + " added successfully!");
    }

    public void deleteClass(String className) {
        if (classList.remove(className)) {
            System.out.println("Class " + className + " deleted successfully!");
        } else {
            System.out.println("Class " + className + " not found!");
        }
    }

    public void updateClass(String oldClassName, String newClassName) {
        int index = classList.indexOf(oldClassName);
        if (index != -1) {
            classList.set(index, newClassName);
            System.out.println("Class " + oldClassName + " updated to " + newClassName);
        } else {
            System.out.println("Class " + oldClassName + " not found!");
        }
    }

    public void readClasses() {
        System.out.println("Classes: " + classList);
    }

    // Phương thức xem thông tin sinh viên
    public void viewStudentInfo() {
        System.out.println("Student Information:");
        for (String student : studentList) {
            System.out.println(student);
        }
    }

    // Phương thức xem thông tin lớp học
    public void viewClassInfo() {
        System.out.println("Class Information:");
        for (String className : classList) {
            System.out.println(className);
        }
    }

    // Phương thức nhập điểm
    public void enterGrade(String student, String grade) {
        System.out.println("Entered grade for " + student + ": " + grade);
    }

    // Phương thức điểm danh
    public void checkAttendance() {
        System.out.println("Attendance checked for class: " + getClassName());
    }

    public void addStudent(String student) {
        studentList.add(student);
        System.out.println("Student " + student + " added successfully!");
    }
}
