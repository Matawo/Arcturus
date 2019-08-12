package Model;

import java.util.Random;

public class Dice {
    private int max;

    private static Random seed = new Random();

    public Dice(int max) {
        this.max = max;
    }

    public int roll() {
        return seed.nextInt(max)+1;
    }

    public int crit() {
        return roll() + roll();
    }
}
