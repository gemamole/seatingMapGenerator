package com.map.seatingmapgenerator.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Classroom {
    private int id, row, column, maxStudents;
    private final BooleanProperty selected = new SimpleBooleanProperty(false);

    public Classroom(int id, int row, int column, int maxStudents) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.maxStudents = maxStudents;
    }

    //id getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //row getter and setter
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    //column getter and setter
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    //student limit getter and setter
    public int getMaxStudents() {
        return maxStudents;
    }
    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
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
