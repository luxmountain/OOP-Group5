package Models;

import java.util.Date;

public class Subject {
<<<<<<< Updated upstream
    // Attributes
    private String name;
    private Date beginTime;
    private Date endTime;

    // Constructor
    public Subject(String name, Date beginTime, Date endTime) {
        this.name = name;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

=======
    private String className;  
    private String subjectID;  
    private String teacherID;  
    private Date schedule;     

    public Subject(String className, String subjectID, String teacherID, Date schedule) {
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
>>>>>>> Stashed changes
