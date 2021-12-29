/**
 * An enum class that defines all hero types
 * and also store the favored skills of every hero type.
 */
public enum HeroType {
    Warriors(new SkillType[]{SkillType.Agility, SkillType.Strength}),
    Sorcerers(new SkillType[]{SkillType.Dexterity, SkillType.Agility}),
    Paladins(new SkillType[]{SkillType.Dexterity, SkillType.Strength});
    private SkillType[] favoredType;

    HeroType(SkillType[] favoredType) {
        this.favoredType = favoredType;
    }

    public SkillType[] getFavoredType() {
        return favoredType;
    }
}
