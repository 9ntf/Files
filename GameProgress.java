import java.io.Serializable;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int hp;
    private int lvl;
    private int weapons;
    private double distance;

    public GameProgress(int hp, int lvl, int weapons, double distance) {
        this.hp = hp;
        this.lvl = lvl;
        this.weapons = weapons;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "hp=" + hp +
                ", lvl=" + lvl +
                ", weapons=" + weapons +
                ", distance=" + distance +
                '}';
    }
}