package com.map.seatingmapgenerator.service;

import com.map.seatingmapgenerator.database.StudentDAO;
import com.map.seatingmapgenerator.model.Student;

import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void addStudent(Student student) {
        studentDAO.insert(student);
        System.out.println("Added Student: " + student.getId());
    }

    public void editStudent(Student student, int id) {
        studentDAO.update(student, id);
        System.out.println("Edited Student: " + student.getId());
    }

    public void deleteStudent(int id) {
        studentDAO.delete(id);
        System.out.println("Deleted Student: " + id);
    }

    public List<Student> getStudents() {
        return studentDAO.getAllStudents();
    }
}
