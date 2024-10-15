package Models;

import java.util.Date;

public abstract class Person {
   private String name;
   private String className; 
   private String subject;    
   private String role;
   private String phone;
   private String email;
   private String id;
   private Date birthDate;

   public Person(String name, String className, String subject, String role, String phone, String email, String id, Date birthDate) {
      this.name = name;
      this.className = className;
      this.subject = subject;
      this.role = role;
      this.phone = phone;
      this.email = email;
      this.id = id;
      this.birthDate = birthDate;
   }

   public Person() {
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getClassName() {
      return this.className;
   }

   public void setClassName(String className) {
      this.className = className;
   }

   public String getSubject() {
      return this.subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public String getPhone() {
      return this.phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Date getBirthdate() {
      return birthDate;
   }

   public void setBirthdate(Date birthdate) {
      this.birthDate = birthdate;
   }

   public abstract void displayRole();
}
