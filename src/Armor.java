/**
 * A class extends Item that defines armor used in the game,
 * and it implements the Attackable and Equipable<Hero> Interface(only hero can equip it)
 */
public class Armor extends Item implements Equipable<Hero> {
    private double defense;

    public Armor(String name, int price, int requiredLevel, double defense) {
        super(ItemType.Armor,name, price, requiredLevel);
        setDefense(defense);
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    // implements equip behavior defined in the Equipable interface
    @Override
    public void equip(Hero h) {
        h.getEquipments().put(ItemType.Armor,this);
        h.setSkill(SkillType.Defense, getDefense());
    }
}
