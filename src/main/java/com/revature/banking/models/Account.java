package com.revature.banking.models;

public class Account {
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private int age;

//    public Trainer(){}

    public Account(String email, String password, String first_name, String last_name, int age) {
        super(); // just always there, by default of EVERY CLASS is Object
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }

    public Account() {

    }

    // Getters & Setters
    public String getEmail() {
        return email;
    }

    public void set(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_Name() {
        return first_name;
    }

    public void setFirst_Name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_Name() {
        return last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String toFileString() {
        // StringBuilder, there is also a StringBuffer (it's thread-safe)
        // Is another class for Strings that allows them to be mutated
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(email).append(",")
                .append(password).append(",")
                .append(first_name).append(",")
                .append(last_name).append(",")
                .append(age);

        // Without changing the mutableString class from StringBuilder we wont' have an appropriate return type
        return mutableString.toString(); // We need the toString to return it to it's appropriate type
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "newUser{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
