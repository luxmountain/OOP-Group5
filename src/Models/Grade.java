package Models;
public class Grade {
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
