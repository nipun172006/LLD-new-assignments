package dice;

public interface Dice {
    int roll();
    int getSides(); // useful for strategy logic (e.g., checking max roll)
}
