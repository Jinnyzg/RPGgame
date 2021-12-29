import java.util.ArrayList;
import java.util.HashMap;

/**
 * a class that extends Character that defines the states and behaviors of heroes.
 * It also implements Attackable and Displayable.
 */

public class Hero extends Character implements Attackable, Displayable {
    // a static attribute that defines all skills that will be increased after win
    private static final SkillType[] HeroSkills = new SkillType[]{SkillType.Strength, SkillType.Agility, SkillType.Dexterity};
    private final HeroType type;
    private int money;
    private int freeHands;
    private int exp;
    // store equipped props(Armor and Weapon)
    private HashMap<ItemType, Item> equipments;
    // store owned items they bought from markets
    private HashMap<ItemType, ArrayList<Item>> backpack;

    public Hero(String name, int exp, HeroType type, int money, HashMap<SkillType, Double> skills) {
        super(ComponentType.Hero, name, 100, 1, skills);
        this.type = type;
        this.money = money;
        initPackets();
        initEquips();
        freeHands = 2;
        this.exp = exp;
    }

    public static SkillType[] getHeroSkills() {
        return HeroSkills;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void initEquips() {
        this.equipments = new HashMap<>();
        this.equipments.put(ItemType.Weapon, null);
        this.equipments.put(ItemType.Armor, null);
    }

    public int getFreeHands() {
        return freeHands;
    }

    public void setFreeHands(int freeHands) {
        this.freeHands = freeHands;
    }


    // initialize the backpack with a hashmap
    public void initPackets() {
        backpack = new HashMap<>();
        for (ItemType type : ItemType.values()) {
            backpack.put(type, new ArrayList<>());
        }
    }

    public HeroType getType() {
        return type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public HashMap<ItemType, ArrayList<Item>> getBackpack() {
        return backpack;
    }

    public void setBackpack(HashMap<ItemType, ArrayList<Item>> backpack) {
        this.backpack = backpack;
    }

    public HashMap<ItemType, Item> getEquipments() {
        return equipments;
    }

    public void setEquipments(HashMap<ItemType, Item> equipments) {
        this.equipments = equipments;
    }

    // implements attack
    @Override
    public String attack(Character opponent) {
        int pre = opponent.getHp();
        opponent.updateHp(-1 * getSkills().get(SkillType.Strength));
        Weapon w = (Weapon) getEquipments().get(ItemType.Weapon);
        // when equipped weapon use weapon to attack
        if (w != null) {
            w.attack(opponent);
        }
        return opponent.getName() + " loses " + (pre - opponent.getHp()) + " hp";
    }

    // cast spell
    public String castSpell(Spell spell, Monster opponent) {
        String res = spell.cast(this, opponent);
        return res;
    }

    public void usePotion(Potion potion) {
        potion.use(this);
        getBackpack().get(ItemType.Potion).remove(potion);
    }

    // equip every equipable prop
    public void equip(Equipable<Hero> prop) {
        prop.equip(this);
    }

    @Override
    public boolean ifDodge() {
        double chance = getSkills().get(SkillType.Agility) * 0.001;
        return chance >= Math.random();
    }

    public void updateMoney(int addNum) {
        setMoney(getMoney() + addNum);
    }

    public void updateExp(int addNum) {
        setExp(getExp() + addNum);
        if (getExp() >= getLevel() * 10) {
            levelUp();
        }
    }

    private void levelUp() {
        setLevel(getLevel() + 1);
        System.out.println("Hero " + getName() + " levels up to" + getLevel());
        updateSkill(SkillType.Mana, 1.1);
        setHp(getLevel() * 100);
        for (SkillType skill : HeroSkills) {
            updateSkill(skill, 1.05);
        }
        // their favored skills can be increased more 5%
        for (SkillType skill : type.getFavoredType()) {
            updateSkill(skill, 1.05);
        }
    }

    @Override
    public String toString() {
        return getName() +
                "-> type=" + type +
                ", exp=" + exp +
                ", hp=" + getHp();
    }

    // implements toStringList
    public ArrayList<String> toStringList() {
        ArrayList<String> personInfo = new ArrayList<>();
        personInfo.add("Name: " + getName());
        personInfo.add("Type: " + getType());
        personInfo.add("Level: " + getLevel());
        personInfo.add("Money: " + getMoney());
        personInfo.add("Exp: " + getExp());
        for (SkillType type : getSkills().keySet()) {
            int val = (int) Math.round(getSkills().get(type));
            personInfo.add(type.name() + ": " + val);
        }
        for (ItemType type : getEquipments().keySet()) {
            if (getEquipments().get(type) == null) {
                personInfo.add(type.name() + ": ");
            } else {
                personInfo.add(type.name() + ": " + getEquipments().get(type).getName());
            }
        }
        return personInfo;
    }
}
