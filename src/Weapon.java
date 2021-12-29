/**
 * A class extends Item that defines weapon used in the game,
 * and it implements the Attackable and Equipable<Hero> Interface(only hero can equip it)
 */
public class Weapon extends Item implements Equipable<Hero>,Attackable {
    private int requiredHeads;
    private int damage;

    public Weapon(String name, int price, int requiredLevel, int requiredHeads, int damage) {
        super(ItemType.Weapon,name, price, requiredLevel);
        this.requiredHeads = requiredHeads;
        this.damage = damage;
    }

    public int getRequiredHeads() {
        return requiredHeads;
    }

    public void setRequiredHeads(int requiredHeads) {
        this.requiredHeads = requiredHeads;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void equip(Hero h) {
        h.getEquipments().put(ItemType.Weapon,this);
    }

    @Override
    public String attack(Character opponent) {
        opponent.updateHp(damage);
        // the information of the attack has been integrated in the hero attack method,
        // so no need to return the attack logger here
        return "";
    }
}
