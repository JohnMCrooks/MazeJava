package com.crooks;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by johncrooks on 7/13/16.
 */
public class Main {

    static final int SIZE = 10;
    static boolean firstLoop = true;
    static boolean firstStop = true;
    static Random r = new Random();

    static int startingX = r.nextInt(SIZE);
    static int startingY = r.nextInt(SIZE);

    static Room[][] createRooms(){
        Room[][] rooms = new Room[SIZE][SIZE];
        for(int row = 0; row < SIZE; row++){
            for(int col = 0; col <SIZE; col++){
                rooms[row][col] = new Room(row,col);
            }
        }
        return rooms;
    }

    static ArrayList<Room> possibleNeighbors(Room[][] rooms, int row, int col){
        ArrayList<Room> neighbors = new ArrayList<>();

       //getting top room
        if (row > 0) {
            neighbors.add(rooms[row-1][col]);
        }
       //get bottom room
        if (row < SIZE-1){
            neighbors.add(rooms[row+1][col]);
        }
        //get left room
        if (col >0) {
            neighbors.add(rooms[row][col-1]);
        }
        //get right room
        if (col <SIZE-1){
            neighbors.add(rooms[row][col+1]);
        }

        neighbors = neighbors.stream()
                .filter(room -> !room.wasVisited)
                .collect(Collectors.toCollection(ArrayList<Room>::new));

        return neighbors;
    }
    static Room randomNeighbor(Room[][] rooms, int row, int col) {
        ArrayList<Room> neighbors = possibleNeighbors(rooms, row, col);
        if (neighbors.size() > 0) {
            Random r = new Random();
            int index = r.nextInt(neighbors.size());
            return neighbors.get(index);
        } else {
            if (firstStop == true){
                rooms[row][col].setIsEnd(true);
                firstStop = false;
            }
            return null;
        }
    }

    static void tearDownWall(Room oldRoom, Room newRoom){
        // going up
        if (newRoom.row < oldRoom.row) {
            newRoom.hasBottom = false;
        }
        //going down
        else if (newRoom.row >oldRoom.row){
            oldRoom.hasBottom = false;
        }
        //going left
        else if (newRoom.col < oldRoom.col) {
            newRoom.hasRight=false;
        }
        //going right
        else if ( newRoom.col > oldRoom.col){
            oldRoom.hasRight = false;
        }
    }

    static boolean createMaze( Room[][] rooms, Room currentRoom){
        currentRoom.wasVisited = true;
        Room nextRoom = randomNeighbor(rooms,currentRoom.row,currentRoom.col);

        if(nextRoom==null){
            return false;
        }
        tearDownWall(currentRoom, nextRoom);
        while (createMaze(rooms,nextRoom)) {
        }
        if(firstLoop == false){
            rooms[startingX][startingY].setIsStart(true);
            firstLoop = true;
        }
        return true;
    }


    public static void main(String[] args) {
        Room[][] rooms = createRooms();

        System.out.println("Starting index positions: " + startingX + ":" + startingY);
        System.out.println("The starting and ending position lack Underscores.");
        rooms[startingX][startingY].setIsStart(true);

        createMaze(rooms, rooms[startingX][startingY]);

        for (Room[] row : rooms){  //creates the cap on top of the grid
            System.out.print(" _");
        }
        System.out.println();

        for (Room[] row: rooms) {
            System.out.print("|"); // adds the starting pipe on the left hand side
            for (Room room : row) { // loops through and adds bottoms and Pipes for each index position
                if (room.isStart) {
                    System.out.print(room.isStart ? "O" : "");
                    System.out.print(room.hasRight ? "|" : " ");
                } else if (room.isEnd) {
                    System.out.print(room.isEnd ? "X" : "");
                    System.out.print(room.hasRight ? "|" : " ");

                } else {
                    System.out.print(room.hasBottom ? "_" : " ");  //inline conditional - if the room has a bottom print one otherwise print a space
                    System.out.print(room.hasRight ? "|" : " ");

                }
            }
            System.out.println();
        }
    }

}
