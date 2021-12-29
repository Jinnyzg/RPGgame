import java.util.HashMap;

/**
 * An abstract class that defines all characters basic state and method in the RPGgame.
 * Also, it extends the WorldComponent since all characters can be placed on the map.
 */
public abstract class Character extends WorldComponent {
    private String name;
    private int hp;
    private int level;
    // each character has skills and values
    private HashMap<SkillType, Double> skills;


    public Character(ComponentType componentType, String name, HashMap<SkillType, Double> skills) {
        super(componentType);
        this.name = name;
        this.skills = skills;
        this.level = 1;
        this.hp = 100 * level;
    }

    public Character(ComponentType componentType, String name, int hp, int level, HashMap<SkillType, Double> skills) {
        super(componentType);
        this.name = name;
        this.hp = hp;
        this.level = level;
        this.skills = skills;
    }

    public Character(String name, int hp, int level, ComponentType type) {
        super(type);
        this.name = name;
        this.hp = hp;
        this.level = level;
        this.skills = new HashMap<>();
    }

    public HashMap<SkillType, Double> getSkills() {
        return skills;
    }

    public void setSkills(HashMap<SkillType, Double> skills) {
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public void updateHp(double dif) {
        this.hp += dif;
        // if a character has defense skill, add some hp based on its value
        if (getSkills().get(SkillType.Defense) != null) {
            this.hp += getSkills().get(SkillType.Defense) * 0.05;
        }
    }

    // update skill values
    public void updateSkill(SkillType skillType, double dif) {
        if (getSkills().containsKey(skillType)) {
            double newValue = getSkills().get(skillType) * dif;
            getSkills().put(skillType, newValue);
        } else {
            getSkills().put(skillType, dif);
        }
    }

    // an abstract method return if the character dodged the any hurt
    public abstract boolean ifDodge();

    public void setSkill(SkillType type, double val) {
        getSkills().put(type, val);
    }

    // return if the character is faint
    public boolean ifFaint() {
        if (getHp() <= 0) {
            setHp(0);
            return true;
        } else {
            return false;
        }
    }
}
