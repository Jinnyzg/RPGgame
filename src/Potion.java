import java.util.ArrayList;

/**
 * A class extends Item that defines armor used in the game,
 * and it implements the Usable<Hero> Interface(only hero can use it)
 */
public class Potion extends Item implements Usable<Hero> {
    ArrayList<SkillType> affectType;
    private double increment;

    public Potion(String name, int price, int requiredLevel,double increment, ArrayList<SkillType> affectType) {
        super(ItemType.Potion,name, price, requiredLevel);
        this.increment = increment;
        this.affectType = affectType;
    }


    @Override
    public void use(Hero c) {
        // get a random index of affectType
        int index = (int)(Math.random() * affectType.size());
        SkillType pickedType = affectType.get(index);
        if (pickedType.equals(SkillType.Health)){
            c.updateHp(increment);
        }else{
            c.updateSkill(pickedType,increment);
        }
    }
}
