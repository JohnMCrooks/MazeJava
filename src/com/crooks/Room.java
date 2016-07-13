package com.crooks;

/**
 * Created by johncrooks on 7/13/16.
 */
public class Room {
    int row;
    int col;
    String start;
    String end;
    boolean wasVisited = false;
    boolean hasBottom = true;
    boolean hasRight = true;
    boolean isStart = false;

    public Room(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setIsStart(boolean start) {
        isStart = start;
    }
}
