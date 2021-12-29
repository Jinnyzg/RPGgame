import java.util.ArrayList;

/**
 * This class define the fight mode of this game, with methods to manage fight action
 */

public class FightMode implements IFightMode{
    private HeroTeam heroes;
    private MonsterTeam monsters;
    // use logger to store the fight information
    private ArrayList<String> logger;

    public FightMode(HeroTeam heroes, MonsterTeam monsters) {
        this.heroes = heroes;
        this.monsters = monsters;
        this.logger = new ArrayList<>();
    }

    // take one action at a time
    public void takeAction(Hero hero, Monster m, InteractionManager interactionManager) {
        ActionType action = interactionManager.getAction(hero,heroes,monsters);
        // hero takes an action
        logger.add(hero.getName() + " chooses to " + action);
        //cast spell
        if (action.equals(ActionType.CastSpell)) {
            Spell spell = (Spell) interactionManager.getProp(hero.getBackpack(), ItemType.Spell);
            if (spell == null) {
                logger.add("failed to use spell");
                return;
            }
            // check if monster can dodge the attack
            if (!m.ifDodge()) {
                logger.add("hero cast spell->" + hero.castSpell(spell, m));
            } else {
                this.logger.add("Monster dodged the spell.");
            }
            // check if hero can dodge the attack
            if (!hero.ifDodge()) {
                logger.add("monster attack->" + m.attack(hero));
            } else {
                this.logger.add("Hero dodged the attack.");
            }
        } else if (action.equals(ActionType.Attack)) {
            if (!m.ifDodge()) {
                logger.add("hero attack->" + hero.attack(m));
            } else {
                this.logger.add("Monster dodged the attack.");
            }
        } else if (action.equals(ActionType.UsePortion)) {
            Potion potion = (Potion) interactionManager.getProp(hero.getBackpack(), ItemType.Potion);
            // no potion available or player not choose any potion to use
            if (potion == null) {
                logger.add("failed to use potion");
                return;
            }
            hero.usePotion(potion);
            logger.add("hero uses potion " + potion.getName());
        } else {
            Armor armor = (Armor) interactionManager.getProp(hero.getBackpack(), ItemType.Armor);
            if (armor != null) {
                hero.equip(armor);
                logger.add("equipped armor " + armor.getName() + " successfully!");
            }
            Weapon weapon = (Weapon) interactionManager.getProp(hero.getBackpack(), ItemType.Weapon);
            if (weapon != null) {
                hero.equip(weapon);
                logger.add("equipped weapon " + weapon.getName() + " successfully!");
            }
            // no equip available or player not choose any prop to equip
            if (weapon == null & armor == null) {
                logger.add("failed to change equipment.");
                return;
            }
        }
        // monster attack after hero take the action
        if (!hero.ifDodge()) {
            logger.add("monster attack->" + m.attack(hero));
        } else {
            this.logger.add("Hero dodged the attack.");
        }
    }

    public void fightPostProcess() {
        int monsterLevelSum = monsters.levelSum();
        if (heroes.getAliveHeroes().size() > 0) {
            System.out.println("Heroes wins");
            for (Hero hero : heroes.getAliveHeroes()) {
                hero.updateMoney(monsterLevelSum * 100);
                hero.updateExp(5);
                System.out.println(hero.getName() + "get coins and exps!");
            }
        } else {
            System.out.println("Heroes lose");
            for (Hero hero : heroes.getFaintedHeroes()) {
                hero.updateMoney((int) (-1 * hero.getMoney() * 0.5));
                System.out.println(hero.getName() + "loses coins and exps!");
            }
        }
        heroes.init();
    }

    public HeroTeam getHeroes() {
        return heroes;
    }

    public void setHeroes(HeroTeam heroes) {
        this.heroes = heroes;
    }

    public MonsterTeam getMonsters() {
        return monsters;
    }

    public void setMonsters(MonsterTeam monsters) {
        this.monsters = monsters;
    }

    // manage one round of the fight
    public void Fight(InteractionManager interactionManager) {
        while (heroes.getAliveHeroes().size() > 0 && monsters.getAliveMonster().size() > 0) {
            Hero curHero = heroes.getCurFightHero();
            Monster curMonster = monsters.getCurFightMonster();
            while (!curHero.ifFaint() && !curMonster.ifFaint()) {
                GameView.FightView(heroes, monsters, logger);
                logger = new ArrayList<>();
                takeAction(curHero, curMonster, interactionManager);
                // regain 10% mana and hp for all alive heroes
                heroes.recover();
                // rotate the hero and monster
                heroes.getAliveHeroes().add(getHeroes().getCurFightHero());
                heroes.getAliveHeroes().remove(0);
                curHero = heroes.getCurFightHero();
                monsters.getAliveMonster().add(monsters.getCurFightMonster());
                monsters.getAliveMonster().remove(0);
                curMonster = monsters.getCurFightMonster();
            }
            if (curMonster.ifFaint()) {
                logger.add(curMonster.getName() + "is defeated.");
                monsters.updateAlive();
            }
            if (curHero.ifFaint()) {
                logger.add(curHero.getName() + "is faint.");
                heroes.faintHero();
            }
        }
        fightPostProcess();
    }

}
