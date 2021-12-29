import java.util.ArrayList;
import java.util.HashMap;

/**
 * a class that can create all needed entities of the world and implements FactoryCreator interface.
 * This Factory tends to create a randomized but balanced world
 */

public class WorldFactory implements FactoryCreator {
    private static String rootDir = System.getProperty("user.dir") + "/src/ConfigFile/";
    private final HashMap<HeroType, ArrayList<ArrayList<String>>> heroInfo;
    private final HashMap<MonsterType, ArrayList<ArrayList<String>>> monsterInfo;
    private final HashMap<SpellType, ArrayList<ArrayList<String>>> spellInfo;
    private final ArrayList<ArrayList<String>> weaponInfo;
    private final ArrayList<ArrayList<String>> armorInfo;
    private final ArrayList<ArrayList<String>> potionInfo;
    private final int spellNum;
    private final int armNum;
    private final int weaponNum;
    private final int potionNum;
    private final int mapSize;

    public WorldFactory() {
        // get config information from parseText
        heroInfo = ParseText.getHeroInfo();
        monsterInfo = ParseText.getMonsterInfo();
        spellInfo = ParseText.getSpellInfo();
        weaponInfo = ParseText.getWeaponInfo();
        armorInfo = ParseText.getArmorInfo();
        potionInfo = ParseText.getPotionInfo();
        spellNum = 6;
        armNum = 3;
        weaponNum = 3;
        potionNum = 3;
        mapSize = 8;
    }

    @Override
    public Market createMarket() {
        HashMap<ItemType, ArrayList<Item>> items = createMarketItems();
        return new Market(items);
    }

    @Override
    public Map createMap() {
        Map map = new Map(mapSize, mapSize);
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                // skip the start tile of heroes
                if (i == 0 && j == 0) {
                    continue;
                }
                double rand = Math.random();
                if (rand <= 0.3) {
                    // not create stones near heroes' starting point
                    if ((i == 0 && j == 1) || (i == 1 && j == 1)) {
                        continue;
                    }
                    Stone stone = createStone();
                    map.add(i, j, stone);
                } else if (rand <= 0.5) {
                    Market market = createMarket();
                    map.add(i, j, market);
                } else {
                    Road road = createRoad();
                    map.add(i, j, road);
                }
            }
        }
        return map;
    }

    @Override
    public HeroTeam createHeroes(int num) {
        ArrayList<Hero> heroes = new ArrayList<>();
        while (num > 0) {
            for (HeroType type : HeroType.values()) {
                ArrayList<ArrayList<String>> heroesConfig = heroInfo.get(type);
                int randomInx = (int) (Math.random() * (heroesConfig.size()));
                ArrayList<String> heroConfig = heroesConfig.get(randomInx);
                heroes.add(createHero(type, heroConfig));
                num -= 1;
            }
        }
        return new HeroTeam(heroes);
    }

    public Hero createHero(HeroType type, ArrayList<String> heroConfig) {
        ArrayList<String> temp = new ArrayList<>(heroConfig);
        String name = temp.remove(0);
        ArrayList<Integer> values = ParseText.toNumber(temp);
        HashMap<SkillType, Double> skills = new HashMap<>();
        skills.put(SkillType.Mana, Double.valueOf(values.remove(0)));
        for (SkillType skilltype : Hero.getHeroSkills()) {
            skills.put(skilltype, Double.valueOf(values.remove(0)));
        }
        int money = values.remove(0);
        int exp = values.remove(0);
        return new Hero(name, exp, type, money, skills);
    }

    public Monster createMonster(MonsterType type, ArrayList<String> monsterConfig) {
        ArrayList<String> temp = new ArrayList<>(monsterConfig);
        String name = temp.remove(0);
        ArrayList<Integer> values = ParseText.toNumber(temp);
        int level = values.remove(0);
        HashMap<SkillType, Double> skills = new HashMap<>();
        for (SkillType skilltype : Monster.getMonsterSkills()) {
            skills.put(skilltype, Double.valueOf(values.remove(0)));
        }
        return new Monster(name, type, level, skills);
    }

    @Override
    public MonsterTeam createMonsters(int num) {
        ArrayList<Monster> monsters = new ArrayList<>();
        while (num > 0) {
            for (MonsterType type : MonsterType.values()) {
                ArrayList<ArrayList<String>> monstersConfig = monsterInfo.get(type);
                int randomInx = (int) (Math.random() * (monstersConfig.size()));
                ArrayList<String> monsterConfig = monstersConfig.get(randomInx);
                monsters.add(createMonster(type, monsterConfig));
                num -= 1;
            }
        }
        return new MonsterTeam(monsters);
    }

    public Spell createSpell(SpellType type, ArrayList<String> spellConfig) {
        ArrayList<String> temp = new ArrayList<>(spellConfig);
        String name = temp.remove(0);
        ArrayList<Integer> values = ParseText.toNumber(temp);
        int price = values.remove(0);
        int requiredLevel = values.remove(0);
        int damage = values.remove(0);
        int manaCost = values.remove(0);
        return new Spell(name, price, requiredLevel, type, damage, manaCost);
    }

    public Weapon createWeapon(ArrayList<String> weaponConfig) {
        ArrayList<String> temp = new ArrayList<>(weaponConfig);
        String name = temp.remove(0);
        ArrayList<Integer> values = ParseText.toNumber(temp);
        int price = values.remove(0);
        int requiredLevel = values.remove(0);
        int damage = values.remove(0);
        int requiredHands = values.remove(0);
        return new Weapon(name, price, requiredLevel, requiredHands, damage);
    }

    public Armor createArmor(ArrayList<String> armorConfig) {
        ArrayList<String> temp = new ArrayList<>(armorConfig);
        String name = temp.remove(0);
        ArrayList<Integer> values = ParseText.toNumber(temp);
        int price = values.remove(0);
        int requiredLevel = values.remove(0);
        double defense = (double) values.remove(0);
        return new Armor(name, price, requiredLevel, defense);
    }

    public Potion createPotion(ArrayList<String> potionConfig) {
        ArrayList<String> temp = new ArrayList<>(potionConfig);
        String name = temp.remove(0);
        ArrayList<SkillType> affectType = ParseText.getPotionAffectType(temp.remove(temp.size() - 1));
        ArrayList<Integer> values = ParseText.toNumber(temp);
        int price = values.remove(0);
        int requiredLevel = values.remove(0);
        int increment = values.remove(0);
        return new Potion(name, price, requiredLevel, increment, affectType);
    }

    // create market inventory following the requirements of item counts defined in this class
    public HashMap<ItemType, ArrayList<Item>> createMarketItems() {
        HashMap<ItemType, ArrayList<Item>> items = new HashMap<>();
        ArrayList<Item> weapons = new ArrayList<>();
        for (int i = 0; i < weaponNum; i++) {
            int randomInx = (int) (Math.random() * (weaponInfo.size()));
            ArrayList<String> weaponConfig = weaponInfo.get(randomInx);
            weapons.add(createWeapon(weaponConfig));
        }
        items.put(ItemType.Weapon, weapons);
        ArrayList<Item> potions = new ArrayList<>();
        for (int i = 0; i < potionNum; i++) {
            int randomInx = (int) (Math.random() * (potionInfo.size()));
            ArrayList<String> potionConfig = potionInfo.get(randomInx);
            potions.add(createPotion(potionConfig));
        }
        items.put(ItemType.Potion, potions);
        ArrayList<Item> armors = new ArrayList<>();
        for (int i = 0; i < armNum; i++) {
            int randomInx = (int) (Math.random() * (armorInfo.size()));
            ArrayList<String> armorConfig = armorInfo.get(randomInx);
            armors.add(createArmor(armorConfig));
        }
        items.put(ItemType.Armor, armors);
        int i = 0;
        ArrayList<Item> spells = new ArrayList<>();
        while (i < spellNum) {
            for (SpellType type : SpellType.values()) {
                ArrayList<ArrayList<String>> config = spellInfo.get(type);
                int randomInx = (int) (Math.random() * (config.size()));
                ArrayList<String> spellConfig = config.get(randomInx);
                spells.add(createSpell(type, spellConfig));
                i += 1;
            }
        }
        items.put(ItemType.Spell, spells);
        return items;
    }

    public Stone createStone() {
        return new Stone();
    }

    public Road createRoad() {
        return new Road();
    }

}
