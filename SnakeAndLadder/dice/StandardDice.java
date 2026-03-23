package dice;

import java.util.Random;

public class StandardDice implements Dice {
    private int sides;
    private Random random;

    public StandardDice(int sides) {
        this.sides = sides;
        this.random = new Random();
    }

    @Override
    public int roll() {
        return random.nextInt(sides) + 1;
    }

    @Override
    public int getSides() {
        return sides;
    }
}
