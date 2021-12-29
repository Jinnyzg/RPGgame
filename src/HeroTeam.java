import java.util.ArrayList;

/**
 * A class that defines a group of heroes
 */

public class HeroTeam extends WorldComponent {
    private ArrayList<Hero> aliveHeroes;
    private ArrayList<Hero> faintedHeroes;

    public HeroTeam() {
        super(ComponentType.HeroTeam);
        this.aliveHeroes = new ArrayList<>();
        this.faintedHeroes = new ArrayList<>();
    }

    // used after every fight to regain all faint heroes
    public void init() {
        for (Hero h : getFaintedHeroes()) {
            h.updateHp(h.getLevel() * 50);
            h.updateSkill(SkillType.Mana, 0.5);
        }
        this.getAliveHeroes().addAll(this.faintedHeroes);
        this.faintedHeroes = new ArrayList<>();
    }

    // used after every round during fight, every alive heroes can regain 10% mana and Hp
    public void recover() {
        for (Hero h : getAliveHeroes()) {
            h.updateSkill(SkillType.Mana, 1.1);
            h.setHp((int) (h.getHp() * 1.1));
        }
    }

    public Hero getCurFightHero() {
        return aliveHeroes.get(0);
    }

    public void faintHero() {
        Hero fainted = aliveHeroes.remove(0);
        faintedHeroes.add(fainted);
    }

    public void respawnHero() {
        Hero fainted = aliveHeroes.remove(0);
        faintedHeroes.add(fainted);
    }

    public HeroTeam(ArrayList<Hero> aliveHeroes) {
        super(ComponentType.HeroTeam);
        this.aliveHeroes = aliveHeroes;
        this.faintedHeroes = new ArrayList<>();
    }

    public ArrayList<Hero> getAliveHeroes() {
        return aliveHeroes;
    }

    public void setAliveHeroes(ArrayList<Hero> aliveHeroes) {
        this.aliveHeroes = aliveHeroes;
    }

    public ArrayList<Hero> getFaintedHeroes() {
        return faintedHeroes;
    }

    public void setFaintedHeroes(ArrayList<Hero> faintedHeroes) {
        this.faintedHeroes = faintedHeroes;
    }


    @Override
    public String toString() {
        String res = "Heros\n";
        int i = 1;
        res += "Alive:\n";
        for (Hero hero : getAliveHeroes()) {
            res = res + i + "." + hero.toStringList() + "\n";
            i++;
        }
        res += "Faint:\n";
        for(Hero hero:getFaintedHeroes()){
            res = res + i + "." + hero.toStringList() + "\n";
            i++;
        }
        return res;
    }
}
