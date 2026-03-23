package models;

public class Cell {
    private int position;
    private Jump jump; // null if no snake or ladder on this cell

    public Cell(int position) {
        this.position = position;
        this.jump = null;
    }

    public int getPosition() {
        return position;
    }

    public Jump getJump() {
        return jump;
    }

    public void setJump(Jump jump) {
        this.jump = jump;
    }

    public boolean hasJump() {
        return jump != null;
    }
}
