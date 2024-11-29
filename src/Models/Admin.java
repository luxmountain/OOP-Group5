package Models;

import java.util.Date;

public class Admin extends Student{
    public Admin(String name, String className, String subject, String role, String phone, String email, String id, Date birthDate) {
        super(name, className, subject, role, phone, email, id, birthDate, id);
   }
   
}
