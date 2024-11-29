package Models;

import java.time.*;
import java.util.*;
import java.util.Date;

/*public class Student extends Person {
    private String parentID;
    private LocalDate enrollmentDate;
    private String status;
    private Map<String, String> grades; 
    private String attendance; 
    private double gpa; 
    
    public Student(String name, String className, String role, String phone, String email, String id, Date birthDate, String parentID) {
        super(name, role, phone, email, id, birthDate);
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
*/
public class Student extends Person {
    private Date enrollmentDate; // Ngày nhập học
    private float cpa;           // Điểm CPA
    private String attendance;   // Tình trạng điểm danh

    // Constructor
    public Student(String name, String role, String phone, String email, String id, Date birthDate, Date enrollmentDate, float cpa, String attendance) {
        super(name, role, phone, email, id, birthDate);
        this.enrollmentDate = enrollmentDate;
        this.cpa = cpa;
        this.attendance = attendance;
    }

    // Getter và Setter cho enrollmentDate
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    // Getter và Setter cho cpa
    public float getCpa() {
        return cpa;
    }

    public void setCpa(float cpa) {
        if (cpa >= 0.0 && cpa <= 4.0) { // Đảm bảo CPA nằm trong khoảng hợp lệ
            this.cpa = cpa;
        } else {
            System.out.println("Invalid CPA! Please enter a value between 0.0 and 4.0.");
        }
    }

    // Getter và Setter cho attendance
    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    // Phương thức hiển thị thông tin sinh viên
    @Override
    public void viewPersonalInfo() {
        super.viewPersonalInfo();
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("CPA: " + cpa);
        System.out.println("Attendance: " + attendance);
    }

    // Phương thức cập nhật thông tin sinh viên
    public void updateStudentInfo(Date enrollmentDate, Float cpa, String attendance) {
        if (enrollmentDate != null) this.enrollmentDate = enrollmentDate;
        if (cpa != null) setCpa(cpa); // Gọi phương thức setCpa để kiểm tra hợp lệ
        if (attendance != null) this.attendance = attendance;
        System.out.println("Student information updated successfully!");
    }
}