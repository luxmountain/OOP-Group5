package Models;

import java.util.Date;

<<<<<<< Updated upstream
public class Person {

    private String name;
    private String className;
    private String role;
    private String phone;
    private String email;
    private String id;
    private Date birthDate;
=======
public abstract class Person {
   private String name;
   private String subject;    
   private String role;
   private String phone;
   private String email;
   private String id;
   private Date birthDate;

   public Person(String name, String subject, String role, String phone, String email, String id, Date birthDate) {
      this.name = name;
      this.subject = subject;
      this.role = role;
      this.phone = phone;
      this.email = email;
      this.id = id;
      this.birthDate = birthDate;
   }
>>>>>>> Stashed changes

    // Constructor
    public Person(String name, String className, String role, String phone, String email, String id, Date birthDate) {
        this.name = name;
        this.className = className;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.birthDate = birthDate;
    }

    // Getter và Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    // Phương thức hiển thị vai trò (role)
    protected void displayRole() {
        System.out.println("Role: " + role);
    }

    // Phương thức xem thông tin cá nhân
    public void viewPersonalInfo() {
        System.out.println("Personal Information:");
        System.out.println("Name: " + name);
        System.out.println("Class: " + className);
        System.out.println("Role: " + role);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("ID: " + id);
        System.out.println("Birth Date: " + birthDate);
    }

    // Phương thức cập nhật thông tin cá nhân
    public void updatePersonalInfo(String name, String className, String role, String phone, String email, String id, Date birthDate) {
        if (name != null) this.name = name;
        if (className != null) this.className = className;
        if (role != null) this.role = role;
        if (phone != null) this.phone = phone;
        if (email != null) this.email = email;
        if (id != null) this.id = id;
        if (birthDate != null) this.birthDate = birthDate;
        System.out.println("Personal information updated successfully!");
    }
}

