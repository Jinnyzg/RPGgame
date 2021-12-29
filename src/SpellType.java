/**
 * An enum class that defines all spell types and also store the skill it can hurt
 */
public enum SpellType {
    FireSpells(SkillType.Damage),
    IceSpells(SkillType.Defense),
    LightningSpells(SkillType.Dodge);
    private final SkillType hurtType;

    SpellType(SkillType type) {
        this.hurtType = type;
    }

    public SkillType getHurtType() {
        return hurtType;
    }
}
