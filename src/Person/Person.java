package Person;

public abstract class Person {
   private String name;
   private String className;
   private String role;
   private String phone;
   private String email;
   private String id;


   public Person(String name, String className, String role, String phone, String email, String id) {
      this.name = name;
      this.className = className;
      this.role = role;
      this.phone = phone;
      this.email = email;
      this.id = id;
   }


   public String getName() {
      return this.name;
   }


   public String getClassName() {
      return this.className;
   }


   public String getRole() {
      return this.role;
   }


   public String getPhone() {
      return this.phone;
   }


   public String getEmail() {
      return this.email;
   }


   public String getId() {
      return this.id;
   }

   public abstract void displayRole();
}

