package Model;

import com.sun.javafx.scene.text.TextLayout;

import java.util.*;
import java.util.stream.Stream;

public class EntityInBattle extends Creature {
    private Battlefield battlefield;
    private Boolean isAlly;
    private Cell cell;
    private int y;
    private Set<Weapon> weaponsEquipped;
    private int currentInitiative;
    private int movement = getSpeed();
    private int hitPoints = getMaxHitPoint();

    public int getTeam() {
        return team;
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(Battlefield battlefield) {
        this.battlefield = battlefield;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    private int team;


    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getX() {
        return cell.x;
    }

    public Set<Weapon> getWeaponsEquipped() {
        return weaponsEquipped;
    }

    public void setWeaponsEquipped(Set<Weapon> weaponsEquipped) {
        this.weaponsEquipped = weaponsEquipped;
    }

    public int getCurrentInitiative() {
        return currentInitiative;
    }

    public void setCurrentInitiative(int currentInitiative) {
        this.currentInitiative = currentInitiative;
    }

    public int getY() {
        return y;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    private void takeDamage(int value, String type) {
        hitPoints -= value;
        if (hitPoints < 0) {
            hitPoints = 0;
        }
    }

    public void turn() {
        System.out.println("Turn of " + this.getName());
        int movement = getSpeed();
        Boolean action_= true;
        Boolean bonusAction = true;
        EntityInBattle e = battlefield.getClosest(this.cell, battlefield.getTeam((team+1)%2));
        moveToward(e.cell);
        attack(weaponsEquipped.iterator().next(),e);
    }

    private void moveToward(Cell target) {
        System.out.println("la cible est " + target);
        Comparator<Cell> comparator = new Comparator<Cell>() {
            @Override
            public int compare(Cell cell, Cell t1) {
                return cell.getDistance(target)- t1.getDistance(target);
            }
        };
        Stream<Cell> stream = Arrays.stream(battlefield.getCells()).flatMap(Arrays::stream);
        Optional<Cell> dest = stream.filter(c -> (c.entity == null) && (c.getDistance(cell) < movement))
                            .min(comparator);
        if (dest.isPresent()) {
            movement = movement - dest.get().getDistance(cell);
            cell.removeEntity(this);
            System.out.println(this.getName() + " has moved from " + cell.toString() + " to " + dest.toString());
            cell.addEntity(this);
        }
    }

    private Boolean attack(Weapon w, EntityInBattle target) {
        System.out.println(getName() + " attack " + target.getName() + " with " + w.getName());
        if (target.cell.getDistance(this.cell)>w.getRange()) {
            System.out.println(" Cible trop éloignée ");
            return false;
        }
        int rollToHit = Dice.quick_roll(20);
        int damage = w.getDamages().critical();
        if (rollToHit == 20) {
            System.out.println(" Critical for " + damage + " " + w.getDamageType());
            target.takeDamage(damage,w.getDamageType());
            return true;
        }
        if (rollToHit < target.getArmorClass()) {
            System.out.println(" Cible manquée");
            return false;
        }
        damage = w.getDamages().roll();
        System.out.println(" Hit for " + damage + " " + w.getDamageType());
        target.takeDamage(damage,w.getDamageType());
        return true;
    }

    public boolean isDead() {
        return (hitPoints == 0);
    }
}
