package Models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Admin extends Person{
    private List<Teacher> teacherList;

    public Admin(String name, String role, String phone, String email, String id, Date birthDate) throws ParseException {
        super(name, role, phone, email, id, birthDate);
        this.teacherList = new ArrayList<>();
        teacherList.add(new Teacher(name, role, phone, email, id, birthDate));
    }

    public List<Teacher> getTeachers() {
        return teacherList;
    }

    public void addTeacher(Teacher teacher) {
        teacherList.add(teacher);
        System.out.println("Teacher " + teacher.getName() + " added successfully!");
    }

    public void viewTeacherInfo() {
        if (teacherList.isEmpty()) {
            System.out.println("No teachers available!");
        } else {
            System.out.println("Teacher Information:");
            for (Teacher teacher : teacherList) {
                System.out.println(teacher);
            }
        }
    }

    public void updateTeacher(String teacherId, Teacher updatedTeacher) {
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getId().equals(teacherId)) {
                teacherList.set(i, updatedTeacher);
                System.out.println("Teacher with ID " + teacherId + " updated successfully!");
                return;
            }
        }
        System.out.println("Teacher with ID " + teacherId + " not found!");
    }

    public void deleteTeacher(String teacherId) {
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getId().equals(teacherId)) {
                teacherList.remove(i);
                System.out.println("Teacher with ID " + teacherId + " deleted successfully!");
                return;
            }
        }
        System.out.println("Teacher with ID " + teacherId + " not found!");
    }
}
