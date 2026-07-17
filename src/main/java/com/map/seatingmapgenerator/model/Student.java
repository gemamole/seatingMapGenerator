package com.map.seatingmapgenerator.model;

import java.util.Scanner;

public class Student {
    private String name;
    private int id;
    private Grade grade;

    public Student(String name, int id, Grade grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    //name getter and setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //grade getter and setter
    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    //id getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}