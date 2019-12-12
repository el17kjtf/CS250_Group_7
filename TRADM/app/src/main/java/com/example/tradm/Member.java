package com.example.tradm;

public class Member {
    private String id;
    private String email;

    public Member() {
    }

    public Member(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

