package com.map.seatingmapgenerator.database;

import com.map.seatingmapgenerator.model.SeatMap;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ChartDAO {

    public void save(SeatMap seatMap) {

        String sql =
                "INSERT INTO seating_charts(classroom_id, chart_name) VALUES(?,?)";

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
}
