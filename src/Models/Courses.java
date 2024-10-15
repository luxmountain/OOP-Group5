package Models;

import java.util.Date;

public class Courses {
    private String className;  
    private String subjectID;  
    private String teacherID;  
    private Date schedule;     

    public Courses(String className, String subjectID, String teacherID, Date schedule) {
        this.className = className;
        this.subjectID = subjectID;
        this.teacherID = teacherID;
        this.schedule = schedule;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public void displayCourseInfo() {
        System.out.println("Class Name: " + className);
        System.out.println("Subject ID: " + subjectID);
        System.out.println("Teacher ID: " + teacherID);
        System.out.println("Schedule: " + schedule);
    }
}
