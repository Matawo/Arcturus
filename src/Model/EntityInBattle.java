package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EntityInBattle extends Creature {
    private Battlefield battlefield;
    private Boolean isAlly;
    private Cell cell;

    public int getX() {
        return cell.x;
    }

    public ArrayList<Weapon> getWeaponsEquipped() {
        return weaponsEquipped;
    }

    public void setWeaponsEquipped(ArrayList<Weapon> weaponsEquipped) {
        this.weaponsEquipped = weaponsEquipped;
    }

    public int getCurrent_initiative() {
        return current_initiative;
    }

    public void setCurrent_initiative(int current_initiative) {
        this.current_initiative = current_initiative;
    }

    public int getY() {
        return y;
    }

    private int y;
    private ArrayList<Weapon> weaponsEquipped;
    private int current_initiative;
    private int movement = getSpeed();

    private void turn() {
        int movement = getSpeed();
        Boolean action_= true;
        Boolean bonusAction = true;
        EntityInBattle e = battlefield.getClosest(getX(),y,battlefield.getEnemies());

    }

    private void moveToward(Cell target) {
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
            System.out.println(this.getName() + "has moved from " + cell.toString() + " to " + dest.toString());
            cell.addEntity(this);
        }
    }


}
