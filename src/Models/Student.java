package Models;

import java.util.Date;

public class Student extends Person {
    private Date enrollmentDate; // Ngày nhập học
    private float cpa;           // Điểm CPA

    // Constructor
    public Student(String name, String phone, String email, String id, Date birthDate, Date enrollmentDate) {
        super(name, "Student", phone, email, id, birthDate);
        this.enrollmentDate = enrollmentDate;
    }

    public Student(String name, String phone, String email, String id, Date birthDate) {
        super(name, "Student", phone, email, id, birthDate);
    }


    // Getter và Setter cho enrollmentDate
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    // Getter và Setter cho cpa
    // public float getCpa() {
    //     return cpa;
    // }

    // public void setCpa(float cpa) {
    //     if (cpa >= 0.0 && cpa <= 4.0) { // Đảm bảo CPA nằm trong khoảng hợp lệ
    //         this.cpa = cpa;
    //     } else {
    //         System.out.println("Invalid CPA! Please enter a value between 0.0 and 4.0.");
    //     }
    // }
    

    // Phương thức hiển thị thông tin sinh viên
    @Override
    public void viewPersonalInfo() {
        super.viewPersonalInfo();
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("CPA: " + cpa);
    }

    // Phương thức cập nhật thông tin sinh viên
    // public void updateStudentInfo(Date enrollmentDate, Float cpa, String attendance) {
    //     if (enrollmentDate != null) this.enrollmentDate = enrollmentDate;
    //     if (cpa != null) setCpa(cpa); // Gọi phương thức setCpa để kiểm tra hợp lệ
    //     if (attendance != null) this.attendance = attendance;
    //     System.out.println("Student information updated successfully!");
    // }



    
}