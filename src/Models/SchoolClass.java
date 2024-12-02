package Models;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SchoolClass {
    // Attributes
    private String className;
    private Date[] schedule;
    private Date beginTime;
    private Date endTime;
    private List<Student> studentList; // Aggregation relationship with Student class


    // Constructor
    public SchoolClass(String className, Date[] schedule, Date beginTime, Date endTime) {
        this.className = className;
        this.schedule = schedule;
        this.beginTime = beginTime;
        this.endTime = endTime;
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


    public void viewTimeTable() {
        System.out.println("Thời Khóa Biểu " + className);
        System.out.println("Thời Gian Bắt Đầu: " + beginTime);
        System.out.println("Thời Gian Kết Thúc: " + endTime);
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
    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
    }


    public Date[] getSchedule() {
        return schedule;
    }


    public void setSchedule(Date[] schedule) {
        this.schedule = schedule;
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
