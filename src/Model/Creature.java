package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Creature implements Serializable {
    private static int current_id;
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHitPoint() {
        return maxHitPoint;
    }

    public void setMaxHitPoints(int maxHitPoint) {
        this.maxHitPoint = maxHitPoint;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    public int[] getSavingThrow() {
        return savingThrow;
    }

    public void setSavingThrow(int[] savingThrow) {
        this.savingThrow = savingThrow;
    }

    public int check(String score) {
        int v = Dice.quick_roll(20);
        if( v == 20) {
            return -v - getModifier(score);
        }
        return v + getModifier(score);
    }

    public int getModifier(String score) {
        int value = 10;
        if (score.toLowerCase().equals("str")) {
            value = stats[0];
        }
        if (score.toLowerCase().equals("dex")) {
            value = stats[1];
        }
        if (score.toLowerCase().equals("con")) {
            value = stats[0];
        }
        if (score.toLowerCase().equals("int")) {
            value = stats[0];
        }
        if (score.toLowerCase().equals("wis")) {
            value = stats[0];
        }
        if (score.toLowerCase().equals("cha")) {
            value = stats[0];
        }
        return Math.floorDiv(value - 10,2);

    }

    private Dices HitDices;
    private int maxHitPoint;
    private int speed;
    private int armorClass;
    private int[] stats = new int[6];
    private int[] savingThrow = new int[6];
    private ArrayList<Weapon> weapons;

    public Creature() {
        id = current_id;
        current_id++;
    }
}
