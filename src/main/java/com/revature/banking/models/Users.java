package com.revature.banking.models;

public class Users {
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private int age;


    // This is a No-Args Constructor. IT's the default, IFFFl there is no other constructor added.
    // Otherwise, the custom constructor overwrites
    public Users(){
        super();
    }

    public Users(String email, String password, String first_name, String last_name, int age ){
        super();
        this.email  = email; // shadowing, with provided arguments
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;

    }

    public String getEmail(){
        return getEmail();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "users{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
