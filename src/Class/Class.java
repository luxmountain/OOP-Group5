package Class;

import java.sql.Date;

public class Class {
    private String className;
    private String TeacherID;
    private Date scheduele;
   
    public Class(String className, String teacherID, Date scheduele) {
        this.className = className;
        TeacherID = teacherID;
        this.scheduele = scheduele;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String teacherID) {
        TeacherID = teacherID;
    }

    public Date getScheduele() {
        return scheduele;
    }

    public void setScheduele(Date scheduele) {
        this.scheduele = scheduele;
    }


}
