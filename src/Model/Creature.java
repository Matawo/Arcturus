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

    public void setMaxHitPoint(int maxHitPoint) {
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

    private int maxHitPoint;
    private int speed;
    private int armorClass;
    private int[] stats = new int[6];
    private int[] savingThrow = new int[6];

    public Creature() {
        id = current_id;
        current_id++;
    }

}
