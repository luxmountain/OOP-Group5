package Models;

import java.util.Date;
import java.util.ArrayList;

public class Class {
    // Attributes
    private String className;
    private Date[] schedule;
    private Date beginTime;
    private Date endTime;
    private ArrayList<Student> students; // Aggregation relationship with Student class

    // Constructor
    public Class(String className, Date[] schedule, Date beginTime, Date endTime) {
        this.className = className;
        this.schedule = schedule;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.students = new ArrayList<>(); // Initialize the student list
    }

    // Methods
    public void viewStudentList() {
        System.out.println("Danh Sách Học Sinh " + className);
        for (Student student : students) {
            System.out.println(student.getName());
        }
    }

    public void viewTimeTable() {
        System.out.println("Thời Khóa Biểu " + className);
        System.out.println("Thời Gian Bắt Đầu: " + beginTime);
        System.out.println("Thời Gian Kết Thúc: " + endTime);
    }

    // CRUD operations for Student
    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void updateStudent(int index, Student newStudent) {
        if (index >= 0 && index < students.size()) {
            students.set(index, newStudent);
        }
    }

    public Student getStudent(int index) {
        if (index >= 0 && index < students.size()) {
            return students.get(index);
        }
        return null;
    }

    // Getters and Setters
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date[] getSchedule() {
        return schedule;
    }

    public void setSchedule(Date[] schedule) {
        this.schedule = schedule;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
