package com.prabodhmayekar.androidassignment;

public class Registrations {
    private Integer id;
    private String FirstName;
    private String LastName;
    private Integer Age;
    private Integer RegNo;
    private String Course;
    private String Gender;
    private String Phone;
    private String Email;

    public Integer getId() {
        return id;
    }

    public Registrations setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return FirstName;
    }

    public Registrations setFirstName(String firstName) {
        FirstName = firstName;
        return this;
    }

    public String getLastName() {
        return LastName;
    }

    public Registrations setLastName(String lastName) {
        LastName = lastName;
        return this;
    }

    public Integer getAge() {
        return Age;
    }

    public Registrations setAge(Integer age) {
        Age = age;
        return this;
    }

    public Integer getRegNo() {
        return RegNo;
    }

    public Registrations setRegNo(Integer regNo) {
        RegNo = regNo;
        return this;
    }

    public String getCourse() {
        return Course;
    }

    public Registrations setCourse(String course) {
        Course = course;
        return this;
    }

    public String getGender() {
        return Gender;
    }

    public Registrations setGender(String gender) {
        Gender = gender;
        return this;
    }

    public String getPhone() {
        return Phone;
    }

    public Registrations setPhone(String phone) {
        Phone = phone;
        return this;
    }

    public String getEmail() {
        return Email;
    }

    public Registrations setEmail(String email) {
        Email = email;
        return this;
    }
}
