package Model;

import java.io.Serializable;

public class Weapon implements Serializable {
    private Dices damages;
    private String damageType;
    private int range;
    private int bonus_hit;
    private String name;

    public Weapon(String name, Dices damages, String damageType, int range, int bonus_hit) {
        this.damages = damages;
        this.damageType = damageType;
        this.range = range;
        this.bonus_hit = bonus_hit;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dices getDamages() {
        return damages;
    }

    public void setDamages(Dices damages) {
        this.damages = damages;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getBonus_hit() {
        return bonus_hit;
    }

    public void setBonus_hit(int bonus_hit) {
        this.bonus_hit = bonus_hit;
    }
}
