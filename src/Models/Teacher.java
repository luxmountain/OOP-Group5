package Models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import Models.Class;

public class Teacher extends Person {
    private Class clazz;

    // Constructor
    public Teacher(String name, String role, String phone, String email, String id, Date birthDate) throws ParseException {
        super(name, role, phone, email, id, birthDate);
        this.clazz = new Class();
    }

    public Teacher(String name, String role, String phone, String email, String id, Date birthDate, Class clazz) {
        super(name, role, phone, email, id, birthDate);
        this.clazz = clazz;
    }

    

    // public void updateClass(String oldClassName, String newClassName) {
    //     int index = clazz.indexOf(oldClassName);
    //     if (index != -1) {
    //         clazz.set(index, newClassName);
    //         System.out.println("Class " + oldClassName + " updated to " + newClassName);
    //     } else {
    //         System.out.println("Class " + oldClassName + " not found!");
    //     }
    // }

    // public void viewClasses() {
    //     System.out.println("Classes managed by " + getName() + ":");
    //     if (clazz.isEmpty()) {
    //         System.out.println("No classes to display.");
    //     } else {
    //         for (String clazz : clazz) {
    //             System.out.println("- " + clazz);
    //         }
    //     }
    // }

    // // Methods for Student Information
    // public void addStudent(String student) {
    //     if (!studentList.contains(student)) {
    //         studentList.add(student);
    //         System.out.println("Student " + student + " added successfully!");
    //     } else {
    //         System.out.println("Student " + student + " already exists!");
    //     }
    // }

    // public void viewStudentInfo() {
    //     System.out.println("Students in managed classes:");
    //     if (studentList.isEmpty()) {
    //         System.out.println("No students to display.");
    //     } else {
    //         for (String student : studentList) {
    //             System.out.println("- " + student);
    //         }
    //     }
    // }

    // // Methods for Class and Student Management
    // public void viewClassInfo() {
    //     viewClasses();
    //     viewStudentInfo();
    // }

    // // Method for Grading
    // public void enterGrade(String student, String grade) {
    //     if (studentList.contains(student)) {
    //         System.out.println("Grade entered for " + student + ": " + grade);
    //     } else {
    //         System.out.println("Student " + student + " not found in the list.");
    //     }
    // }
    @Override
    public String toString() {
        return "Student [getName()=" + getName() + "]";
    }
}

