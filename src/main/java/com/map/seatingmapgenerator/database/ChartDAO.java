package com.map.seatingmapgenerator.database;

import com.map.seatingmapgenerator.model.Grade;
import com.map.seatingmapgenerator.model.SeatMap;
import com.map.seatingmapgenerator.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChartDAO {

    public void save(SeatMap seatMap) {

        String sql =
                "INSERT INTO seating_charts(classroom_id, chart_name) VALUES(?,?)";

        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, seatMap.getId());
            ps.setString(2, seatMap.getChartName());

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void editInfo(SeatMap map) {
    }

    public void editChart(SeatMap map) {
    }

    public List<SeatMap> getAll() {
        List<SeatMap> seatMaps = new ArrayList<>();

        String sql = "SELECT * FROM seating_charts";

        try(Connection conn = Database.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){

                SeatMap s = new SeatMap(
                        rs.getInt("chart_id"),
                        rs.getString("chart_name"),
                        rs.getInt("classroom_id"),
                        rs.getDate("created_at")
                );

                seatMaps.add(s);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return seatMaps;
    }
}
