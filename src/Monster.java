import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that extends Character that defines the states and behaviors of monsters.
 * It also implements Attackable and Displayable.
 */
public class Monster extends Character implements Attackable {
    private static final SkillType[] monsterSkills = new SkillType[]{SkillType.Damage, SkillType.Defense, SkillType.Dodge};
    private final MonsterType type;

    public Monster(String name, MonsterType type, int level, HashMap<SkillType, Double> skills) {
        super(ComponentType.Monster, name, level * 100, level, skills);
        this.type = type;
    }

    public static SkillType[] getMonsterSkills() {
        return monsterSkills;
    }

    public MonsterType getType() {
        return type;
    }

    @Override
    public String attack(Character hero) {
        double damage = -1 * (getSkills().get(SkillType.Damage)) * 0.05;
        int pre = hero.getHp();
        hero.updateHp(damage);
        return hero.getName() + " loses " + (pre - hero.getHp()) + " hp";
    }

    @Override
    public boolean ifDodge() {
        double chance = getSkills().get(SkillType.Dodge) * 0.001;
        return chance >= Math.random();
    }

    @Override
    public String toString() {
        return getName() +
                "-> type=" + type +
                ", level=" + getLevel() +
                ", hp=" + getHp();
    }

    public ArrayList<String> toStringList() {
        ArrayList<String> personInfo = new ArrayList<>();
        personInfo.add("Name: " + getName());
        personInfo.add("Type: " + getType());
        personInfo.add("Level: " + getLevel());
        for (SkillType type : getSkills().keySet()) {
            int val = (int) Math.round(getSkills().get(type));
            personInfo.add(type.name() + ": " + val);
        }
        for (SkillType type : getSkills().keySet()) {
            if (getSkills().get(type) == null) {
                personInfo.add(type.name() + ": ");
            } else {
                personInfo.add(type.name() + ": " + getSkills().get(type));
            }
        }
        return personInfo;
    }

}
