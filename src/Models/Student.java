package Models;

import java.time.*;
import java.util.*;

public class Student extends Person {
    private String parentID;
    private LocalDate enrollmentDate;
    private String status;
    private Map<String, String> grades; 
    private String attendance; 
    private double gpa; 

    public Student(String name, String className, String role, String phone, String email, String id, Date birthDate, String parentID) {
        super(name, className, role, phone, email, id, birthDate);
        this.enrollmentDate = LocalDate.now();
        this.status = "Active"; 
        this.grades = new HashMap<>(); 
        this.attendance = ""; 
        this.gpa = 0.0; 
        this.parentID = parentID;
    }
    public String getStudentInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Tên: ").append(getName()).append("\n")
            .append("Lớp: ").append(getClassName()).append("\n")
            .append("Mã học sinh: ").append(getId()).append("\n")
            .append("Ngày nhập học: ").append(enrollmentDate).append("\n")
            .append("Trạng thái: ").append(status).append("\n")
            .append("Điểm và môn học:\n");
    
        for (Map.Entry<String, String> entry : grades.entrySet()) {
            info.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
    
        info.append("GPA: ").append(gpa).append("\n")
            .append("Điểm danh: ").append(attendance).append("\n");  
        return info.toString();
    }
    
    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    public void setGrade(String course, String grade) {
        grades.put(course, grade);
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0)
            this.gpa = 0.0;
        else this.gpa = gpa;
    }
    public void viewAttendance() {
        System.out.println("Attendance: " + attendance);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: " + getRole());
    }
}
