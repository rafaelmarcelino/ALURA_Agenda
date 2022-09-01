package br.com.ram.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String phone;
    private String email;

    //Constructor
    public Student(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
