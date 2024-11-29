package Models;

import java.util.ArrayList;
import java.util.Date;

public class Teacher extends Person {
    private ArrayList<String> classList; // List of classes
    private ArrayList<String> studentList; // List of students

    // Constructor
    public Teacher(String name, String role, String phone, String email, String id, Date birthDate) {
        super(name, role, phone, email, id, birthDate);
        this.classList = new ArrayList<>();
        this.studentList = new ArrayList<>();
    }

    // CRUD Methods for Classes
    public void addClass(String className) {
        if (!classList.contains(className)) {
            classList.add(className);
            System.out.println("Class " + className + " added successfully!");
        } else {
            System.out.println("Class " + className + " already exists!");
        }
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

    public void viewClasses() {
        System.out.println("Classes managed by " + getName() + ":");
        if (classList.isEmpty()) {
            System.out.println("No classes to display.");
        } else {
            for (String clazz : classList) {
                System.out.println("- " + clazz);
            }
        }
    }

    // Methods for Student Information
    public void addStudent(String student) {
        if (!studentList.contains(student)) {
            studentList.add(student);
            System.out.println("Student " + student + " added successfully!");
        } else {
            System.out.println("Student " + student + " already exists!");
        }
    }

    public void viewStudentInfo() {
        System.out.println("Students in managed classes:");
        if (studentList.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (String student : studentList) {
                System.out.println("- " + student);
            }
        }
    }

    // Methods for Class and Student Management
    public void viewClassInfo() {
        viewClasses();
        viewStudentInfo();
    }

    // Method for Grading
    public void enterGrade(String student, String grade) {
        if (studentList.contains(student)) {
            System.out.println("Grade entered for " + student + ": " + grade);
        } else {
            System.out.println("Student " + student + " not found in the list.");
        }
    }
}

