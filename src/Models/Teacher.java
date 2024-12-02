package Models;

import java.text.ParseException;
import java.util.Date;

public class Teacher extends Person {
    private SchoolClass clazz;

    // Constructor
    public Teacher(String name, String phone, String email, String id, Date birthDate) throws ParseException {
        super(name, "Teacher", phone, email, id, birthDate);
        this.clazz = new SchoolClass("test");
    }

    public Teacher(String name, String phone, String email, String id, Date birthDate, SchoolClass clazz) {
        super(name, "Teacher", phone, email, id, birthDate);
        this.clazz = clazz;
    }

    public SchoolClass getClazz(){
        return clazz;
    }
    
    
    @Override
    public String toString() {
        return "Student [getName()=" + getName() + "]";
    }
}

