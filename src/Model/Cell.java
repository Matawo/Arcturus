package Model;

import java.util.Set;

public class Cell {
    public int x;
    public int y;
    public EntityInBattle entity;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDistance(Cell otherCell) {
        int dx = Math.abs(x-otherCell.x);
        int dy = Math.abs(y-otherCell.y);
        return (int) Math.sqrt((double) (dx*dx+dy*dy));
    }

    public void removeEntity(EntityInBattle entity) {
        this.entity = null;
    }

    public void addEntity(EntityInBattle entity) {
        this.entity = entity;
    }

    public String toString() {
        return "("+x+","+y+")";
    }
}
