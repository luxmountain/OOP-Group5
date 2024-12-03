package Models;

import java.util.Date;

public class Student extends Person {
    private Date enrollmentDate; // Ngày nhập học
    
    // Constructor
    public Student(String name, String phone, String email, String id, Date birthDate, Date enrollmentDate) {
        super(name, "Student", phone, email, id, birthDate);
        this.enrollmentDate = enrollmentDate;
    }

    public Student(String name, String phone, String email, String id, Date birthDate) {
        super(name, "Student", phone, email, id, birthDate);
    }


    @Override
    public void viewPersonalInfo() {
        super.viewPersonalInfo();
        System.out.println("Name" + enrollmentDate);
    }
}