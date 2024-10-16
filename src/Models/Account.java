package Models;

public class Account {
    private String password;
    private String role;
    private String userID;

    public Account(String userID, String password, String role) {
        this.userID = userID;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean login(String inputPassword) {
        return inputPassword.equals(this.password);
    }

    public void signUp() {

    }
    
}
