package Models;

public class Account {
    private String password;
    private String userID;

    public Account(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
