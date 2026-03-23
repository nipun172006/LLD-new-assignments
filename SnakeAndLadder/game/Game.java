package game;

import java.util.LinkedList;
import java.util.Queue;

import dice.Dice;
import models.Board;
import models.Player;
import strategies.TurnStrategy;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;
    private TurnStrategy strategy;
    private int winningPosition;

    public Game(Board board, Dice dice, TurnStrategy strategy) {
        this.board = board;
        this.dice = dice;
        this.strategy = strategy;
        this.players = new LinkedList<>();
        this.winningPosition = board.getSize();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        System.out.println("\n🎲 GAME STARTED! Target: position " + winningPosition + "\n");

        while (players.size() > 1) {
            Player current = players.poll();
            playTurn(current);
        }

        // Last player remaining
        if (!players.isEmpty()) {
            System.out.println(players.peek().getName() + " finishes last.\n");
        }

        System.out.println("🏁 GAME OVER!");
    }

    private void playTurn(Player player) {
        System.out.println(player.getName() + "'s turn (at position " + player.getPosition() + "):");

        int moves = strategy.getMoves(dice);

        if (moves == 0) {
            // Turn was skipped (e.g., hard mode penalty)
            System.out.println("  → Turn skipped! Stays at " + player.getPosition() + "\n");
            players.add(player);
            return;
        }

        int currentPos = player.getPosition();
        int tentativePos = currentPos + moves;

        // If move exceeds board, ignore it
        if (tentativePos > winningPosition) {
            System.out.println("  → Move " + currentPos + " + " + moves + " = " + tentativePos + " exceeds board. No movement.\n");
            players.add(player);
            return;
        }

        // Resolve position (apply snake/ladder if present)
        int finalPos = board.resolvePosition(tentativePos);

        if (finalPos != tentativePos) {
            String jumpType = finalPos < tentativePos ? "🐍 Snake" : "🪜 Ladder";
            System.out.println("  → Moved to " + tentativePos + " " + jumpType + "! → " + finalPos);
        } else {
            System.out.println("  → Moved to " + finalPos);
        }

        player.setPosition(finalPos);

        // Check win
        if (finalPos == winningPosition) {
            System.out.println("  🏆 " + player.getName() + " WINS!\n");
            return; // don't re-enqueue
        }

        System.out.println();
        players.add(player);
    }
}
