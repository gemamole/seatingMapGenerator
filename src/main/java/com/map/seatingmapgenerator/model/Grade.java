package com.map.seatingmapgenerator.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serializable;

public enum Grade implements Selectable {
    FIRST("1 ano"),
    SECOND("2 ano"),
    THIRD("3 ano"),
    FOURTH("4 ano"),
    FIFTH("5 ano"),
    SIXTH("6 ano"),
    SEVENTH("7 ano"),
    EIGHTH("8 ano"),
    NINTH("9 ano"),
    TENTH("1 ano EM"),
    ELEVENTH("2 ano EM"),
    TWELFTH("3 ano EM");

    private final String displayName;
    Grade(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Grade fromDisplayName(String displayName) {
        for(Grade grade : values()) {
            if(grade.getDisplayName().equals(displayName)) {
                return grade;
            }
        }
        return null;
    }

    private final BooleanProperty selected = new SimpleBooleanProperty(false);

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
