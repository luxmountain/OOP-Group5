package Models;
/*public class Grade {
    private String student_ID;
    private String id;
    private String className;
    private float marks;
    private String remarks;

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id; 
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        if (marks >= 0.0 && marks <= 10.0) {
            this.marks = marks; // *Sửa: Thêm kiểm tra điểm hợp lệ*
        } else {
            System.out.println("Invalid marks! It should be between 0 and 10."); // *Sửa: Thêm thông báo lỗi*
        }
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
*/
import java.util.HashMap;
import java.util.Map;

public class Grade {
    // Danh sách điểm của học sinh trong từng môn học
    private Map<Student, Float> studentGrades; // Lưu điểm theo học sinh

    // Constructor
    public Grade() {
        studentGrades = new HashMap<>();
    }

    // Thêm hoặc sửa điểm cho học sinh
    public void setGrade(Student student, float grade) {
        studentGrades.put(student, grade);
        System.out.println("Grade for " + student.getName() + " has been updated to: " + grade);
    }

    // Xem điểm của học sinh
    public void viewGrade(Student student) {
        if (studentGrades.containsKey(student)) {
            System.out.println(student.getName() + "'s grade: " + studentGrades.get(student));
        } else {
            System.out.println(student.getName() + " does not have a grade recorded.");
        }
    }

    // Tính điểm trung bình của học sinh trong môn học
    public float calculateAverageGrade() {
        if (studentGrades.isEmpty()) {
            System.out.println("No grades recorded.");
            return 0.0f;
        }

        float total = 0.0f;
        int count = 0;
        
        for (float grade : studentGrades.values()) {
            total += grade;
            count++;
        }

        return total / count; // Tính điểm trung bình
    }

    // Xem tất cả điểm của học sinh trong danh sách
    public void viewAllGrades() {
        if (studentGrades.isEmpty()) {
            System.out.println("No grades recorded.");
        } else {
            for (Map.Entry<Student, Float> entry : studentGrades.entrySet()) {
                System.out.println(entry.getKey().getName() + "'s grade: " + entry.getValue());
            }
        }
    }
}
