package com.map.seatingmapgenerator.service;

import com.map.seatingmapgenerator.database.ChartDAO;
import com.map.seatingmapgenerator.database.ClassroomDAO;
import com.map.seatingmapgenerator.database.StudentDAO;
import com.map.seatingmapgenerator.model.Classroom;
import com.map.seatingmapgenerator.model.SeatMap;
import com.map.seatingmapgenerator.model.SeatingGenerator;
import com.map.seatingmapgenerator.model.Student;

import java.util.List;

public class SeatingService {

    private final ChartDAO chartDAO;
    private final StudentDAO studentDAO;
    private final ClassroomDAO classroomDAO;

    public SeatingService(ChartDAO chartDAO,  StudentDAO studentDAO, ClassroomDAO classroomDAO) {
        this.chartDAO = chartDAO;
        this.studentDAO = studentDAO;
        this.classroomDAO = classroomDAO;
    }

    public SeatMap generate(Classroom room, List<Student> students) {
        return SeatingGenerator.generate(room, students);
    }

    public void saveChart(SeatMap map) {
        chartDAO.save(map);
    }

    public void editChartInfo(SeatMap map) {
        chartDAO.editInfo(map);
    }

    public void editChart(SeatMap map) {
        chartDAO.editChart(map);
    }

    public List<SeatMap> getSavedCharts() {
        return chartDAO.getAll();
    }

    public List<Student> getStudents() {
        return studentDAO.getAllStudents();
    }

    public List<Classroom> getClassrooms() {
        return classroomDAO.getAllClassrooms();
    }
}
