/**
 * A class that defines the skill of characters
 */
public class Skill {
    private SkillType type;
    private int value;

    public Skill(SkillType type, int value) {
        this.type = type;
        this.value = value;
    }

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
