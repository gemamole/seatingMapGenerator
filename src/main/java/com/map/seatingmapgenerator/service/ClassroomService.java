package com.map.seatingmapgenerator.service;

import com.map.seatingmapgenerator.model.Classroom;
import com.map.seatingmapgenerator.database.ClassroomDAO;

import java.util.List;

public class ClassroomService {
    private final ClassroomDAO classroomDAO;

    public ClassroomService(ClassroomDAO classroomDAO) {
        this.classroomDAO = classroomDAO;
    }

    public void addClassroom(Classroom classroom) {
        classroomDAO.insert(classroom);
        System.out.println("Classroom " + classroom.getId() + " has been added successfully");
    }

    public void editClassroom(Classroom classroom, int id) {
        classroomDAO.update(classroom, id);
        System.out.println("Classroom " + classroom.getId() + " has been updated successfully");
    }

    public  void deleteClassroom(int id) {
        classroomDAO.delete(id);
        System.out.println("Classroom " + id + " has been deleted successfully");
    }

    public List<Classroom> getClassrooms() {
        return classroomDAO.getAllClassrooms();
    }
}
