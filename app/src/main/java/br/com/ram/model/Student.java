package br.com.ram.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {
    private static long id = 0;
    private final long idStudent;
    private int gender;
    private int age;
    private String name;
    private String phone;
    private String email;

    //Constructor
    public Student(String name, String phone, String email,int age, int gender) {
        id ++;
        this.idStudent = getId();
        setName(name);
        setPhone(phone);
        setEmail(email);
        setAge(age);
        this.gender = gender;
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

    public int getGender() {
        return gender;
    }

    public int getAge() {
        return age;
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

    public void setAge(int age) {
        this.age = age;
    }
    @NonNull
    @Override
    public String toString() {
        return this.getName();
    }
}
