package Class;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

public class Class {
    private String className;
    private String teacherID;
    private Date scheduleDate;
    private Time scheduleTime;
    private int[] studentsCount;

    public Class(String className, String teacherID, Date scheduleDate, Time scheduleTime, int[] studentsCount) {
        this.className = className;
        this.teacherID = teacherID;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.studentsCount = studentsCount;
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

    public int[] getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int[] studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void displayClassInfo() {
        System.out.println("Class Name: " + className);
        System.out.println("Teacher ID: " + teacherID);
        System.out.println("Schedule Date: " + scheduleDate);
        System.out.println("Schedule Time: " + scheduleTime);
        System.out.println("Students Count: " + Arrays.toString(studentsCount));
    }
}
