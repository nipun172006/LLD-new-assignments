import models.*;
import dice.*;
import strategies.*;
import game.Game;

public class Main {

    public static void main(String[] args) {

        // ─── Build a 10x10 board (100 cells) ───
        int boardSize = 100;
        Board board = new Board(boardSize);

        System.out.println("─── BOARD SETUP ───");

        // Ladders (start < end)
        board.addJump(2, 38);
        board.addJump(7, 14);
        board.addJump(8, 31);
        board.addJump(15, 26);
        board.addJump(28, 84);
        board.addJump(51, 67);
        board.addJump(71, 91);
        board.addJump(78, 98);

        // Snakes (start > end)
        board.addJump(16, 6);
        board.addJump(46, 25);
        board.addJump(49, 11);
        board.addJump(62, 19);
        board.addJump(64, 60);
        board.addJump(87, 24);
        board.addJump(93, 73);
        board.addJump(95, 75);
        board.addJump(99, 78);

        // ─── Create dice ───
        Dice dice = new StandardDice(6); // standard 6-sided

        // ─── Choose strategy ───
        // TurnStrategy strategy = new EasyModeStrategy();
        TurnStrategy strategy = new HardModeStrategy(); // uncomment for hard mode

        // ─── Create game and add players ───
        Game game = new Game(board, dice, strategy);
        game.addPlayer(new Player("P1", "Alice"));
        game.addPlayer(new Player("P2", "Bob"));
        game.addPlayer(new Player("P3", "Charlie"));

        // ─── Start! ───
        game.startGame();
    }
}
