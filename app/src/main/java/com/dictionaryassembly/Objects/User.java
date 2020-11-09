package com.dictionaryassembly.Objects;

public class User {
    private String email, lastName, firstName, gender, age, typeEducation;
    private int permission;

    public User(String email, String lastName, String firstName, String gender, String age, String typeEducation) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.age = age;
        this.typeEducation = typeEducation;
        this.permission = 1;
    }

    public User(String email, String lastName, String firstName) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.permission = 1;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTypeEducation() {
        return typeEducation;
    }

    public void setTypeEducation(String typeEducation) {
        this.typeEducation = typeEducation;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
