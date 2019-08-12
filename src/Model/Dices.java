package Model;

import java.util.ArrayList;

public class Dices {
    private ArrayList<Dice> dicelist = new ArrayList<>();
    private int modifier;

    public ArrayList<Dice> getDicelist() {
        return dicelist;
    }

    public void setDicelist(ArrayList<Dice> dicelist) {
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
}
