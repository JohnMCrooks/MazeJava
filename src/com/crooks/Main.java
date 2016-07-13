package com.crooks;

import java.util.ArrayList;

/**
 * Created by johncrooks on 7/13/16.
 */
public class Main {

    static final int SIZE = 10;

    static Room[][] createRooms(){
        Room[][] rooms = new Room[SIZE][SIZE];
        for(int row = 0; row < SIZE; row++){
            for(int col = 0; col <SIZE; col++){
                rooms[row][col] = new Room(row,col);
            }
        }
        return rooms;
    }

    public static void main(String[] args) {
        Room[][] rooms = createRooms();

        for (Room[] row : rooms){  //creates the cap on top of the grid
            System.out.print(" _");
        }
        System.out.println();

        for (Room[] row: rooms) {
            System.out.print("|"); // adds the starting pipe on the left hand side
            for (Room room : row){ // loops through and adds bottom right for each index position
                System.out.print("_|");
            }
            System.out.println();
        }
    }

}
