package com.map.seatingmapgenerator.database;

import com.map.seatingmapgenerator.model.Grade;
import com.map.seatingmapgenerator.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public void insert(Student student) {

        String sql =
                "INSERT INTO students(student_id,student_name,student_grade) VALUES(?,?,?)";

        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getGrade().name());

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(Student student, int oldId) {
        String sql =
                "UPDATE students SET student_id=?,student_name=?,student_grade=? WHERE student_id=?";

        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getGrade().name());
            ps.setInt(4, oldId);

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql =
                "DELETE FROM students WHERE student_id=?";

        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try(Connection conn = Database.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){

                Student s = new Student(
                        rs.getString("student_name"),
                        rs.getInt("student_id"),
                        Grade.valueOf(rs.getString("student_grade"))
                );

                students.add(s);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return students;
    }
}
