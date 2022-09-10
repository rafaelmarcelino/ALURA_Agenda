package br.com.ram.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {
    private static long id = 0;
    private long idStudent;
    private String name;
    private String phone;
    private String email;

    //Constructor
    public Student(String name, String phone, String email) {
        id ++;
        this.idStudent = getId();
        setName(name);
        setPhone(phone);
        setEmail(email);
    }

    //Getters
    private long getId() {
        return id;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    //Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getName();
    }
}
