package Class;

import java.sql.Date;
import java.sql.Time;

public class Class {
    private String className;
    private String teacherID;
    private Date scheduleDate;
    private Time scheduleTime;
    private String[] studentNames; 
    private int studentCount;      

    public Class(String className, String teacherID, Date scheduleDate, Time scheduleTime, int maxStudents) {
        this.className = className;
        this.teacherID = teacherID;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.studentNames = new String[maxStudents];
        this.studentCount = 0;
    }

    // Getter và setter cho các thuộc tính

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Time getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Time scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String[] getStudentNames() {
        return studentNames;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void addStudent(String studentName) {
        if (studentCount < studentNames.length) {
            studentNames[studentCount] = studentName;
            studentCount++;
        } else {
            System.out.println("Lớp đã đầy, không thể thêm học sinh mới.");
        }
    }

}
