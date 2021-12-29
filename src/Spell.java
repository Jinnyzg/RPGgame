/**
 * A class that defines a spell, and it implements the Castable Interface
 */
public class Spell extends Item implements Castable{
    private SpellType type;
    private int damage;
    private int manaCost;

    public Spell(String name, int price, int requiredLevel, SpellType type, int damage, int manaCost) {
        super(ItemType.Spell,name, price, requiredLevel);
        this.type = type;
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public SpellType getType() {
        return type;
    }

    public void setType(SpellType type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    @Override
    public String cast(Hero hero, Monster opponent) {
        double damage = (1+ hero.getSkills().get(SkillType.Dexterity)/10000)*getDamage();
        // check if the hero has enough Mana to cast a spell
        if(hero.getSkills().get(SkillType.Mana) < getManaCost()){
            return hero.getName() + " has no enough mana to cast the spell";
        }
        int pre = opponent.getHp();
        //update the mana
        hero.getSkills().put(SkillType.Mana,hero.getSkills().get(SkillType.Mana)-manaCost);
        // update hurt to the opponent
        opponent.updateHp(-1*damage);
        opponent.updateSkill(type.getHurtType(),1-0.1);
        return opponent.getName() + "loses" + (pre - opponent.getHp()) + "hp and " + "10% " + type.getHurtType();
    }
}
