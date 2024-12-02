package Models;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SchoolClass {
    // Attributes
    private String className;
    private Date beginTime;
    private Date endTime;

    private List<Student> studentList; // Aggregation relationship with Student class




    
    public SchoolClass(String className, Date beginTime, Date endTime, Teacher teacher) {

        this.className = className;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.teacher = teacher;
        this.studentList = new ArrayList<>(); // Initialize the student list
       
    }


    public SchoolClass(String className) throws ParseException {
        this.className = className;
        this.studentList = new ArrayList<>(); // Initialize the student list
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        studentList.add(new Student("Vu", "09089090", "vâ@gmail.com", "001", dateFormat.parse("01/12/2024")));
    }


    // Methods
    public void viewStudentList() {
        System.out.println("Danh Sách Học Sinh " + className);
        for (Student student : studentList) {
            System.out.println(student.getName());
        }
    }



   // Phương thức trả về sĩ số lớp
    public int getClassSize() {
        return studentList.size();

    }


    // CRUD operations for Student
    public void addStudent(Student student) {
        studentList.add(student);
    }


    public void removeStudent(Student student) {
        studentList.remove(student);
    }


    public void updateStudent(int index, Student newStudent) {
        if (index >= 0 && index < studentList.size()) {
            studentList.set(index, newStudent);
        }
    }



    public Student getStudent(int index) {

        if (index >= 0 && index < studentList.size()) {
            return studentList.get(index);
        }
        return null;
    }


    public List<Student> getStudenList() {
        return studentList;
    }


    // Getters and Setters
     public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
