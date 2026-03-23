package strategies;

import dice.Dice;

// Easy mode: unlimited extra turns when rolling max (e.g., 6 on a 6-sided dice)
public class EasyModeStrategy implements TurnStrategy {

    @Override
    public int getMoves(Dice dice) {
        int totalMoves = 0;
        int maxFace = dice.getSides();

        while (true) {
            int roll = dice.roll();
            System.out.print("  rolled " + roll);
            totalMoves += roll;

            if (roll == maxFace) {
                System.out.println(" → extra turn!");
            } else {
                System.out.println();
                break;
            }
        }

        return totalMoves;
    }
}
