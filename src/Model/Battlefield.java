package Model;

import javafx.collections.transformation.SortedList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

public class Battlefield {
    private Set<EntityInBattle> enemies;
    private Set<EntityInBattle> allies;
    private int sizex;
    private int sizey;
    private Cell[][] cells;
    private ArrayList<EntityInBattle> initiativeOrder;


    public Battlefield(Set<EntityInBattle> allies, Set<EntityInBattle> enemies) {
        this.enemies = enemies;
        this.allies = allies;
        this.sizex = 8;
        this.sizey = 8;
        this.cells = new Cell[sizex][sizey];
        for(int i=0; i< sizex; i++) {
            for(int j=0; j< sizey; j++) {
                cells[i][j] = new Cell(i,j);
            }
        }
        enemies.forEach(entityInBattle -> entityInBattle.setCurrentInitiative(entityInBattle.check("dex")));
        initiativeOrder = new ArrayList<EntityInBattle>();
        initiativeOrder.addAll(enemies);
        initiativeOrder.addAll(allies);
        initiativeOrder.sort(Comparator.comparing(EntityInBattle::getCurrentInitiative));
        initiativeOrder.forEach(e -> e.setBattlefield(this));

    }

    public Set<EntityInBattle> getEnemies() {
        return enemies;
    }

    public void setEnemies(Set<EntityInBattle> enemies) {
        this.enemies = enemies;
    }

    public Set<EntityInBattle> getAllies() {
        return allies;
    }

    public Set<EntityInBattle> getTeam(int a) {
        if (a==0) {
            return allies;
        }
        return enemies;
    }

    public void setAllies(Set<EntityInBattle> allies) {
        this.allies = allies;
    }

    public int getSizex() {
        return sizex;
    }

    public void setSizex(int sizex) {
        this.sizex = sizex;
    }

    public int getSizey() {
        return sizey;
    }

    public void setSizey(int sizey) {
        this.sizey = sizey;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCell(int x, int y) {
        if (x >= 0 && x < sizex && y >= 0 && y < sizey) {
            return cells[x][y];
        }
        return null;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Boolean checkIfOver() {
        return enemies.stream().allMatch(EntityInBattle::isDead) || allies.stream().allMatch(EntityInBattle::isDead);
    }

    public void calculateTurn(){
        System.out.println("New turn");
        System.out.println("Ennemies" + this.enemies.stream().reduce("", (s,e) -> s + e.getName(), String::concat));
        System.out.println("Allies" + this.allies.stream().reduce("", (s,e) -> s + e.getName(), String::concat));
        initiativeOrder.forEach(EntityInBattle::turn);
    }

    public void run(){
        int i = 0;
        while (!checkIfOver() && i < 10) {
            i++;
            calculateTurn();
        }
    }

    public EntityInBattle getClosest(Cell c, Set<EntityInBattle> list) {
        int min_dist = Integer.MAX_VALUE;
        EntityInBattle closest = null;
        for(EntityInBattle e : list) {
            System.out.println(c);
            int dist = c.getDistance(e.getCell());
            if (dist < min_dist) {
                min_dist = dist;
                closest = e;
            }
        }
        return closest;
    }

    public int getDistance(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x1-x2);
        int dy = Math.abs(y1-y2);
        return (int) Math.sqrt((double) (dx*dx+dy*dy));
    }

    public ArrayList<Cell> getAdjacentDiagonal(Cell c) {
        ArrayList<Cell> adjacents = new ArrayList<Cell>();
        for(int i = -1; i<=1; i++) {
            for(int j = -1; j<=1; j++) {
                if (c.x + i > 0 && c.x + i <= sizex && c.y + j > 0 && c.y + j <= sizey) {
                    if (i != 0 || j != 0) {
                        adjacents.add(cells[c.x + i][c.y + j]);
                    }
                }
            }
        }
        return adjacents;
    }
}
