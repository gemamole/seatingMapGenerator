package com.map.seatingmapgenerator.model;

public class Seat {
    boolean blocked;
    Student student;
    int row, col;

    public Seat(int row, int col, Student student) {
        this.student = student;
        this.row = row;
        this.col = col;
    }

    //student getter and setter
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
}
