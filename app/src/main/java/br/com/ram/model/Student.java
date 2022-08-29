package br.com.ram.model;

public class Student {
    private String name;
    private String phone;
    private String email;
    private  static long qtyStudents;

    //Constructor
    public Student(String name, String phone, String email) {
        qtyStudents ++;
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

    public static long getQtyStudents() {
        return qtyStudents;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
