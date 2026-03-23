package strategies;

import dice.Dice;

public interface TurnStrategy {
    int getMoves(Dice dice);
}
