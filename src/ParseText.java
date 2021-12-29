import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * :a class that is used to process and parse all the configuration files
 */

public class ParseText {
    private static String rootDir = System.getProperty("user.dir") + "/ConfigFile/";

    public static ArrayList<Integer> toNumber(ArrayList<String> info) {
        ArrayList<Integer> numlist = new ArrayList<>();
        for (String i : info) {
            numlist.add(Integer.parseInt(i));
        }
        return numlist;
    }

    public static HashMap<HeroType, ArrayList<ArrayList<String>>> getHeroInfo() {
        HashMap<HeroType, ArrayList<ArrayList<String>>> heroInfo = new HashMap<>();
        String[] fileNames = new String[]{"Warriors.txt", "Paladins.txt", "Sorcerers.txt"};
        for (int i = 0; i < HeroType.values().length; i++) {
            String fileName = rootDir + fileNames[i];
            // put the info in the hashmap with its type as a key
            heroInfo.put(HeroType.valueOf(fileNames[i].replaceFirst(".txt", "")), splitTxt(fileName, 7));
        }
        return heroInfo;
    }

    public static HashMap<MonsterType, ArrayList<ArrayList<String>>> getMonsterInfo() {
        HashMap<MonsterType, ArrayList<ArrayList<String>>> monsterInfo = new HashMap<>();
        String[] fileNames = new String[]{"Dragons.txt", "Exoskeletons.txt", "Spirits.txt"};
        for (int i = 0; i < HeroType.values().length; i++) {
            String fileName = rootDir + fileNames[i];
            monsterInfo.put(MonsterType.valueOf(fileNames[i].replaceFirst(".txt", "")), splitTxt(fileName, 5));
        }
        return monsterInfo;
    }

    public static HashMap<SpellType, ArrayList<ArrayList<String>>> getSpellInfo() {
        HashMap<SpellType, ArrayList<ArrayList<String>>> spellInfo = new HashMap<>();
        for (SpellType t : SpellType.values()) {
            // get the file name form the spell type defined in SpellType
            String fileName = rootDir + t.name() + ".txt";
            spellInfo.put(t, splitTxt(fileName, 5));
        }
        return spellInfo;
    }

    public static ArrayList<ArrayList<String>> getWeaponInfo() {
        String fileName = rootDir + "Weaponry.txt";
        return splitTxt(fileName, 5);
    }

    public static ArrayList<ArrayList<String>> getArmorInfo() {
        String fileName = rootDir + "Armory.txt";
        return splitTxt(fileName, 4);
    }

    public static ArrayList<ArrayList<String>> getPotionInfo() {
        String fileName = rootDir + "Potions.txt";
        return splitTxt(fileName, 5);
    }

    public static ArrayList<SkillType> getPotionAffectType(String info) {
        ArrayList<SkillType> affectTypes = new ArrayList<>();
        String[] splits = info.split("[/]+");
        for (String s : splits) {
            affectTypes.add(SkillType.valueOf(s));
        }
        return affectTypes;
    }

    private static ArrayList<ArrayList<String>> splitTxt(String fileName, int colNum) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            ArrayList<ArrayList<String>> allInfo = new ArrayList<>();
            for (String l : lines) {
                String[] split = l.split("\\s+");
                if (split.length < colNum) {
                    continue;
                }
                ArrayList<String> parsedInfo = new ArrayList<>(Arrays.asList(split));
                allInfo.add(parsedInfo);
            }
            return allInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
