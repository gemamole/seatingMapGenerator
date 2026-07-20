package com.map.seatingmapgenerator.model;

import java.util.*;

public class SeatingGenerator {

    public static SeatMap generate(Classroom classroom, List<Student> students) {
        SeatMap map = new SeatMap();

        int rows = classroom.getRow();
        int cols = classroom.getColumn();

        Seat[][] chart = new Seat[rows][cols];

        map.setRoom(classroom.getId());
        map.setChart(chart);

        //order students by grade
        List<Student> ordered = interleaveByGrade(students);

        //create seating map zigzag traversing order
        List<int[]> seatOrder = buildSeatOrder(rows, cols);

        //place students in seating map
        for(Student s : ordered) {

            //set to false as default
            boolean placed = false;

            //try valid seats first
            for(int[] pos : seatOrder) {

                //get pos = {x, y}
                int r = pos[0];
                int c = pos[1];

                //if the seat is empty and valid, ...
                if(chart[r][c] == null && canPlace(chart, r, c, s)) {
                    chart[r][c] = new Seat(r, c, s); //place student in seat
                    placed = true;
                    break;
                }
            }

            //if there are no valid seats, ...
            if(!placed) {
                for(int[] pos : seatOrder) {

                    //get pos = {x, y}
                    int r = pos[0];
                    int c = pos[1];

                    //add student to available empty seat
                    if(chart[r][c] == null) {
                        chart[r][c] = new Seat(r, c, s);
                        break;
                    }
                }
            }
        }

        return map;
    }

    //grade ordering algorithm
    private static List<Student> interleaveByGrade(List<Student> students) {

        Map<Grade, Queue<Student>> groups = new LinkedHashMap<>();

        for(Student s : students) {
            //add grade key and create grade list value
            groups.computeIfAbsent(s.getGrade(), g -> new LinkedList<>())
                    .add(s); //add student to list
        }

        //distributed student list
        List<Student> result = new ArrayList<>();

        boolean added = true;

        //check if there are students left in the map
        while(added) {
            //set to false as default
            added = false;

            //check each queue from map
            for(Queue<Student> q : groups.values()) {
                if(!q.isEmpty()) {
                    result.add(q.poll()); //add student to distributed list and remove from queue
                    added = true; //set student added to true
                }
            }
        }

        return result;
    }

    //zigzag traversing order algorithm
    private static List<int[]> buildSeatOrder(int rows, int cols) {
        //traversing order list
        List<int[]> order = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            //for even rows...
            if(r % 2 == 0) {
                for(int c = 0; c < cols; c++) {
                    order.add(new int[]{r, c}); //add seats from left to right
                }
            }
            //for odd rows...
            else {
                for (int c = cols - 1; c >= 0; c--) {
                    order.add(new int[]{r, c}); //add seats from right to left
                }
            }
        }

        return order;
    }

    //adjacency constraint check
    private static boolean canPlace(Seat[][] chart, int r, int c, Student s) {

        int[][] neighborPositions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        for(int[] n : neighborPositions) {

            int neighborRow = r + n[0];
            int neighborColumn = c + n[1];

            //avoid out-of-bounds access
            if(neighborRow < 0 || neighborColumn < 0 ||
                    neighborRow >= chart.length ||
                    neighborColumn >= chart[0].length) {
                continue;
            }

            //set neighbor position
            Seat neighbor = chart[neighborRow][neighborColumn];

            //check neighbor grade and validate possible placement
            if(neighbor != null &&
                    neighbor.getStudent().getGrade() == s.getGrade()) {
                return false;
            }
        }

        return true;
    }
}
