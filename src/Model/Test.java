package Model;

import Utils.SaverXML;

import java.io.*;

public class Test {

    public static void main(String[] args) {
        Dices dices = new Dices();
        Dice d = new Dice(6);
        dices.getDicelist().add(d);
        dices.setModifier(4);
        for (int i = 0; i < 100; i++) {
            System.out.println(dices.roll());
        }
        Creature p1 = new Creature();
        p1.setName("Goblin");
        SaverXML.serializeCreature(p1);
    }
}