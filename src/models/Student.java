package models;

import enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String lastName;
    private String email;                // @ обязательно
                                         // уникальный болушу керек

    private String password;             // не меньше 7 символов
    private Gender gender;





    public Student() {
    }

    public Student(String name, String lastName, String email, String password, Gender gender) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "Student \n" +
                "имя: " + name + "\n"+
                "фамилия: " + lastName + "\n"+
                "email: " + email + "\n"+
                "password: " + password + "\n"+
                "пол: " + gender +"\n";
    }
}
