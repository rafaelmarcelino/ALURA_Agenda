package br.com.ram.model;

import java.io.Serializable;

public class Student implements Serializable {
    private static long id = 0;
    private static int position = 0;
    private String name;
    private String phone;
    private String email;

    //Constructor
    public Student(String name, String phone, String email) {
        this.id ++;
        this.position = (int) (getId() -1);
        setName(name);
        setPhone(phone);
        setEmail(email);
    }

    //Getters
    public long getId() {
        return id;
    }

    public static int getPosition() {
        return position;
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

    @Override
    public String toString() {
        return this.getName();
    }
}
