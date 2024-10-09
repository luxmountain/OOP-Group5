package Class;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Class {
    private String className;
    private String teacherID;
    private Timestamp schedule;
    private String[] studentNames; 
    private int studentCount;      

    public Class(String className, String teacherID, Date scheduleDate, Time scheduleTime, int maxStudents) {
        this.className = className;
        this.teacherID = teacherID;
        this.schedule = new Timestamp(scheduleDate.getTime() + scheduleTime.getTime());
        this.studentNames = new String[maxStudents];
        this.studentCount = 0;
    }

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

    public Timestamp getSchedule() {
        return schedule;
    }

    public void setSchedule(Date scheduleDate, Time scheduleTime) {
        this.schedule = new Timestamp(scheduleDate.getTime() + scheduleTime.getTime());
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

    public void displayClassInfo() {
        System.out.println("Tên lớp: " + className);
        System.out.println("Mã GV: " + teacherID);
        System.out.println("Thời khóa biểu: " + schedule);
        System.out.println("Học sinh: ");
        for (int i = 0; i < studentCount; i++) {
            System.out.println((i + 1) + ". " + studentNames[i]);
        }
        System.out.println("Sĩ số: " + studentCount);
    }

    
}
