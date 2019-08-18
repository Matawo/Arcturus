package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Dices implements Serializable {
    private Set<Dice> dicelist = new HashSet<>();
    private int modifier;

    public Set<Dice> getDicelist() {
        return dicelist;
    }

    public void setDicelist(Set<Dice> dicelist) {
        this.dicelist = dicelist;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public int roll() {
        return dicelist.stream().reduce(0, (result,dice) -> dice.roll() + result, Integer::sum) + modifier;
    }

    public int critical() {
        return dicelist.stream().reduce(0, (result,dice) -> dice.crit() + result, Integer::sum) + modifier;
    }
}
