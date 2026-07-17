package com.map.seatingmapgenerator.database;

import com.map.seatingmapgenerator.model.Classroom;
import com.map.seatingmapgenerator.model.Grade;
import com.map.seatingmapgenerator.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO {

    public void insert(Classroom classroom) {

        String sql =
                "INSERT INTO classrooms(classroom_id, row_num, col_num, max_students) VALUES(?,?,?,?)";

        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, classroom.getId());
            ps.setInt(2, classroom.getRow());
            ps.setInt(3, classroom.getColumn());
            ps.setInt(4, classroom.getMaxStudents());

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(Classroom classroom, int oldId) {
        String sql =
                "UPDATE classrooms SET classroom_id=?,row_num=?,col_num=?,max_students=? WHERE classroom_id=?";

        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, classroom.getId());
            ps.setInt(2, classroom.getRow());
            ps.setInt(3, classroom.getColumn());
            ps.setInt(4, classroom.getMaxStudents());
            ps.setInt(5, oldId);

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql =
                "DELETE FROM classrooms WHERE classroom_id=?";

        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Classroom> getAllClassrooms() {
        List<Classroom> classrooms = new ArrayList<>();

        String sql = "SELECT * FROM classrooms";

        try(Connection conn = Database.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){

                Classroom c = new Classroom(
                        rs.getInt("classroom_id"),
                        rs.getInt("row_num"),
                        rs.getInt("col_num"),
                        rs.getInt("max_students")
                );

                classrooms.add(c);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return classrooms;
    }
}
