package com.map.seatingmapgenerator.service;

import com.map.seatingmapgenerator.model.Classroom;
import com.map.seatingmapgenerator.model.Student;

public class ValidationService {

    public static void requireNonEmpty(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Atenção: " + fieldName + " não pode estar vazio");
        }
    }

    public static void requireNonNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException("Atenção: " + fieldName + " não pode estar vazio");
        }
    }

    public static void isInt(String value, String fieldName) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Atenção: " + fieldName + " deve ser um número");
        }
    }

    public static void requireUniqueStudentId(int id, Student selectedStudent, StudentService service) {

        boolean exists = service.getStudents()
                .stream()
                .anyMatch(s -> s.getId() == id && s.getId() != selectedStudent.getId());

        if (exists) {
            throw new IllegalArgumentException("Essa matrícula já existe");
        }
    }

    public static void requireUniqueClassroomId(int id, Classroom selectedClassroom, ClassroomService service) {

        boolean exists = service.getClassrooms()
                .stream()
                .anyMatch(c -> c.getId() == id && c.getId() != selectedClassroom.getId());

        if (exists) {
            throw new IllegalArgumentException("Essa sala já existe");
        }

    }

    public static void requireValidCapacity(String value, int row, int column, String fieldName) {

        if (Integer.parseInt(value) > row * column) {
            throw new IllegalArgumentException("Atenção: " + fieldName + " excede a capacidade da sala");
        }
    }
}
