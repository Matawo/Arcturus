package Model;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        EntityInBattle c1 = commoner();
        EntityInBattle c2 = commoner();
        Set<EntityInBattle> allies = new HashSet<EntityInBattle>();
        Set<EntityInBattle> ennemies = new HashSet<EntityInBattle>();
        allies.add(c1);
        ennemies.add(c2);
        Battlefield battlefield = new Battlefield(allies,ennemies);
        c1.setCell(battlefield.getCell(0,0));
        c2.setCell(battlefield.getCell(4,4));
        c1.setTeam(0);
        c2.setTeam(1);
        c1.setName("C1");
        c2.setName("C2");
        System.out.println(c1.getCell());
        System.out.println(c2.getCell());
        System.out.println("Battlefield prepared");
        battlefield.run();
    }

    public static EntityInBattle commoner() {
        EntityInBattle johny = new EntityInBattle();
        johny.setName("Johny");
        johny.setArmorClass(10);
        int[] stats= {10,10,10,10,10,10};
        johny.setStats(stats);
        johny.setSpeed(6);
        johny.setMaxHitPoints(4);
        johny.setHitPoints(johny.getMaxHitPoint());
        Dices damages = new Dices();
        HashSet<Dice> hashSetDices = new HashSet<Dice>();
        hashSetDices.add(new Dice(4));
        damages.setDicelist(hashSetDices);
        Weapon club = new Weapon("Club", damages,"bludgeoning",1,+2);
        johny.setCurrentInitiative(Dice.quick_roll(20));
        HashSet<Weapon> weapons = new HashSet<Weapon>();
        weapons.add(club);
        johny.setWeaponsEquipped(weapons);
        johny.setSavingThrow(stats);
        return johny;
    }
}