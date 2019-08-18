package Model;

import java.io.Serializable;
import java.util.Random;

public class Dice implements Serializable {
    private int max;

    private static Random seed = new Random();

    public Dice(int max) {
        this.max = max;
    }

    public Dice() {
    }

    public int roll() {
        return seed.nextInt(max)+1;
    }

    public int crit() {
        return roll() + roll();
    }

    public static int quick_roll(int max) { return seed.nextInt(max)+1;}
}
