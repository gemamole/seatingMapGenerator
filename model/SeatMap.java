package com.map.seatingmapgenerator.model;

import java.time.LocalDateTime;

public class SeatMap {
    private int id;
    private Classroom classroom;
    private Seat[][] chart;
    private LocalDateTime createdAt;

    //id getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //room getter and setter
    public Classroom getRoom() {
        return classroom;
    }
    public void setRoom(Classroom classroom) {
        this.classroom = classroom;
    }

    //seat map getter and setter
    public Seat[][] getChart() {
        return chart;
    }
    public void setChart(Seat[][] chart) {
        this.chart = chart;
    }

    //creation time getter and setter
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
