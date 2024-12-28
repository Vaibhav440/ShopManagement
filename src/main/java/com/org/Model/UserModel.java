package com.org.Model;



public class UserModel {
    private int uid;
    private String useremail;
    private String password;
    private String role;
    private String contact;

    // Default constructor
    public UserModel() {}

    // Parameterized constructor
    public UserModel(int uid, String useremail, String password, String role, String contact) {
        this.uid = uid;
        this.useremail = useremail;
        this.password = password;
        this.role = role;
        this.contact = contact;
    }
    
 // Parameterized constructor
    public UserModel(int uid, String useremail, String password, String contact) {
        this.uid = uid;
        this.useremail = useremail;
        this.password = password;
        this.role = "Customer";
        this.contact = contact;
    }
    public UserModel( String useremail, String role, String contact) {
        
        this.useremail = useremail;
        
        this.role = role;
        this.contact = contact;
    }

    // Getter and Setter methods
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // toString method
    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", useremail='" + useremail + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}

