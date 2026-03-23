package strategies;

import dice.Dice;

// Hard mode: max 3 consecutive max-rolls allowed
// On 4th consecutive max-roll, entire turn is skipped (0 moves)
public class HardModeStrategy implements TurnStrategy {
    private static final int MAX_CONSECUTIVE = 3;

    @Override
    public int getMoves(Dice dice) {
        int totalMoves = 0;
        int consecutiveMax = 0;
        int maxFace = dice.getSides();

        while (true) {
            int roll = dice.roll();
            System.out.print("  rolled " + roll);

            if (roll == maxFace) {
                consecutiveMax++;

                if (consecutiveMax > MAX_CONSECUTIVE) {
                    // 4th consecutive max — entire turn is forfeited
                    System.out.println(" → 4th consecutive " + maxFace + "! Turn skipped.");
                    return 0;
                }

                totalMoves += roll;
                System.out.println(" → extra turn! (streak: " + consecutiveMax + ")");
            } else {
                totalMoves += roll;
                System.out.println();
                break;
            }
        }

        return totalMoves;
    }
}
