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
    private double payFee; 
    private double paidFee; 

    public Student(String name, String className, String subject, String role, String phone, String email, String id, Date birthDate, String parentID) {
        super(name, className, subject, role, phone, email, id, birthDate);
        this.enrollmentDate = LocalDate.now();
        this.status = "Active"; 
        this.grades = new HashMap<>(); 
        this.attendance = ""; 
        this.gpa = 0.0; 
        this.payFee = 1000.0; 
        this.paidFee = 0.0; 
        this.parentID = parentID;
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

    public double getPayFee() {
        return payFee;
    }

    public void setPayFee(double payFee) {
        this.payFee = payFee;
    }

    public double getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(double paidFee) {
        this.paidFee = paidFee;
    }

    public void viewGrades() {
        System.out.println("Grades:");
        for (Map.Entry<String, String> entry : grades.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("GPA: " + gpa);
    }

    public void viewAttendance() {
        System.out.println("Attendance: " + attendance);
    }

    public void doHomework() {
        System.out.println("Doing homework...");
    }

    public void payFee(double amount) {
        if (amount > 0) {
            if (paidFee + amount <= payFee) {
                paidFee += amount;
                System.out.println("Paid " + amount + ". Total paid: " + paidFee);
            } else {
                System.out.println("The amount exceeds the remaining fee. Total remaining: " + (payFee - paidFee));
            }
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void viewFee() {
        System.out.println("Total Fee: " + payFee);
        System.out.println("Paid Fee: " + paidFee);
        System.out.println("Remaining Fee: " + (payFee - paidFee));
    }

    @Override
    public void displayRole() {
        System.out.println("Role: " + getRole());
    }
}
