package com.revature.banking.models;

public class Account {
    private int id;
    private String account;
    private String account_type;
    private int balance;
    private String email;


    public Account(int id, String account, String account_type, int balance, String email) {
        super(); // just always there, by default of EVERY CLASS is Object
        this.id = id;
        this.account = account;
        this.account_type = account_type;
        this.balance = balance;
        this.email = email;
    }

    public Account() {

    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void set(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public int getBalance() {
        return balance;
    }

    public int setBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String toFileString() {
        // StringBuilder, there is also a StringBuffer (it's thread-safe)
        // Is another class for Strings that allows them to be mutated
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(id).append(",")
                .append(account).append(",")
                .append(account_type).append(",")
                .append(balance).append(",")
                .append(email);

        // Without changing the mutableString class from StringBuilder we wont' have an appropriate return type
        return mutableString.toString(); // We need the toString to return it to it's appropriate type
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", account_type='" + account_type + '\'' +
                ", balance='" + balance + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
