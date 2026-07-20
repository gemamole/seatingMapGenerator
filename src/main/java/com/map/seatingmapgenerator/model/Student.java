package com.map.seatingmapgenerator.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Student implements Selectable{
    private String name;
    private int id;
    private Grade grade;
    private final BooleanProperty selected = new SimpleBooleanProperty(false);

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

    //selected property getters and setters
    public BooleanProperty selectedProperty() {
        return selected;
    }
    public boolean isSelected() {
        return selected.get();
    }
    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}