package com.map.seatingmapgenerator.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class SeatMap {
    private int id;
    private String chartName;
    private int classroomId;
    private Seat[][] chart;
    private LocalDateTime createdAt;

    public SeatMap() {}

    public SeatMap(int id, String chartName, int classroomId, Date createdAt) {
        this.id = id;
        this.chartName = chartName;
        this.classroomId = classroomId;
        this.createdAt = LocalDateTime.now();
    }

    //id getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //chart name getter and setter
    public String getChartName() {
        return chartName;
    }
    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    //room getter and setter
    public int getRoom() {
        return classroomId;
    }
    public void setRoom(int classroomId) {
        this.classroomId = classroomId;
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
