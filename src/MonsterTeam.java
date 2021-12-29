import java.util.ArrayList;

/**
 * A class that defines a group of monsters
 */
public class MonsterTeam {
    private ArrayList<Monster> aliveMonster;
    private ArrayList<Monster> deadMonster;

    public MonsterTeam(ArrayList<Monster> aliveMonster) {
        this.aliveMonster = aliveMonster;
        this.deadMonster = new ArrayList<>();
    }

    // used to calculate all coins the heroes can gain after fight
    public int levelSum() {
        int res = 0;
        for (Monster m : this.getAliveMonster()) {
            res += m.getLevel();
        }
        for (Monster m : this.getDeadMonster()) {
            res += m.getLevel();
        }
        return res;
    }

    public void addMonster(Monster m) {
        this.aliveMonster.add(m);
    }

    public Monster getCurFightMonster() {
        return this.aliveMonster.get(0);
    }

    public void updateAlive() {
        Monster m = this.aliveMonster.remove(0);
        this.deadMonster.add(m);
    }

    public ArrayList<Monster> getAliveMonster() {
        return aliveMonster;
    }

    public void setAliveMonster(ArrayList<Monster> aliveMonster) {
        this.aliveMonster = aliveMonster;
    }

    public ArrayList<Monster> getDeadMonster() {
        return deadMonster;
    }

    public void setDeadMonster(ArrayList<Monster> deadMonster) {
        this.deadMonster = deadMonster;
    }

    public String toString() {
        String res = "Monsters\n";
        int i = 1;
        res += "Alive:\n";
        for (Monster monster : getAliveMonster()) {
            res = res + i + "." + monster.toStringList() + "\n";
            i++;
        }
        res += "Dead:\n";
        for (Monster monster : getDeadMonster()) {
            res = res + i + "." + monster.toStringList() + "\n";
            i++;
        }
        return res;
    }
}
