package Models;

import java.util.Date;

public class Subject {
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

