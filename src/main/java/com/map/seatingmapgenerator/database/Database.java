package com.map.seatingmapgenerator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:school.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initialize() {

        try(Connection conn = connect();
            Statement stmt = connect().createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS students(
                    student_id INTEGER PRIMARY KEY,
                    student_name TEXT NOT NULL,
                    student_grade TEXT NOT NULL
               )
           """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS classrooms(
                    classroom_id INTEGER PRIMARY KEY,
                    max_students INTEGER NOT NULL,
                    row_num INTEGER,
                    col_num INTEGER
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS seating_charts(
                    chart_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    chart_name TEXT,
                    classroom_id INTEGER,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY(classroom_id) REFERENCES classrooms(classroom_id)
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS seats(
                    seat_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    chart_id INTEGER,
                    row_num INTEGER,
                    col_num INTEGER,
                    student_id INTEGER,
                    FOREIGN KEY(chart_id) REFERENCES seating_charts(chart_id),
                    FOREIGN KEY(student_id) REFERENCES students(id)
                )
            """);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}