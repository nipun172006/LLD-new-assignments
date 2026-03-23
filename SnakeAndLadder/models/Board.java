package models;

public class Board {
    private int size;
    private Cell[] cells;

    public Board(int size) {
        this.size = size;
        this.cells = new Cell[size + 1]; // 1-indexed
        for (int i = 1; i <= size; i++) {
            cells[i] = new Cell(i);
        }
    }

    // Place a snake or ladder on the board
    public void addJump(int start, int end) {
        Jump jump = new Jump(start, end);
        cells[start].setJump(jump);
        String type = jump.isSnake() ? "Snake" : "Ladder";
        System.out.println("  " + type + " added: " + start + " → " + end);
    }

    // Resolve a position — apply one jump if present (no chaining)
    public int resolvePosition(int position) {
        if (position < 1 || position > size) {
            return position;
        }
        Cell cell = cells[position];
        if (cell.hasJump()) {
            return cell.getJump().getEnd();
        }
        return position;
    }

    public int getSize() {
        return size;
    }
}
