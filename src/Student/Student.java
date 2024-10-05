package Student;

import Person.Person;
//import teacher.Teacher;
import java.util.*;
import java.time.*;

public class Student extends Person {
    private LocalDate enrollmentDate; 
    private boolean status; 
    private List<Double> grades; 
    private double cpa; 
    private String account; 
    private double pay; 
    //private Teacher teacher; 

    public Student(String name, String clazz, String role, String phone, String email, String id){
                   //Teacher teacher) {
        super(name, clazz, role, phone, email, id);
        //this.teacher = teacher;
     
        this.enrollmentDate = LocalDate.now(); 
        this.status = true; 
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public boolean isStatus() {
        return status;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public double getCpa() {
        return cpa;
    }

    public String getAccount() {
        return account;
    }

    public double getPay() {
        return pay;
    }

    // public Teacher getTeacher() {
    //     return teacher;
    // }

    // public void viewTeacherInfo() {
    //     System.out.println("Teacher Name: " + teacher.getName());
    //     System.out.println("Teacher Class: " + teacher.getClazz());
    //     System.out.println("Teacher Phone: " + teacher.getPhone());
    //     System.out.println("Teacher Email: " + teacher.getEmail());
    // }

    public void viewGrades() {
        System.out.println("Grades: " + grades);
        System.out.println("CPA: " + cpa);
    }

    // Phương thức xem tài khoản và thanh toán
    public void viewAccountInfo() {
        System.out.println("Account: " + account);
        System.out.println("Amount Paid: " + pay);
    }

    public void doHomeWork(){
        
    }

    @Override
    public void displayRole() {
        System.out.println("Role: " + getRole());
    }

}
