import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

/**
 * this class manages all the input actions of users to get proper and valid input
 */

public class InteractionManager {
    private static final String hotkey = "wasdiq";
    private final Scanner scanner;

    public InteractionManager() {
        this.scanner = new Scanner(System.in);
    }

    public ActionType getAction(Hero hero, HeroTeam heroes, MonsterTeam monsters) {
        int key = 0;
        while (true) {
            System.out.println("Enter Action(use 1,2,3,4,5,6):");
            if (scanner.hasNextInt()) {
                key = scanner.nextInt();
                if (key > 0 && key <= 4) {
                    break;
                }
                if (key == 5) {
                    System.out.println("\u001B[31m" + heroes + "\u001B[31m" + "\u001B[0m");
                } else if (key == 6) {
                    System.out.println("\u001B[31m" + monsters + "\u001B[31m" + "\u001B[0m");
                } else {
                    System.out.println("not a valid integer");
                }
            } else {
                System.out.println("not an integer");
                scanner.next();
            }
        }
        return ActionType.getAction(key);
    }

    public Item getProp(HashMap<ItemType, ArrayList<Item>> inventory, ItemType type) {
        ArrayList<Item> items = inventory.get(type);
        System.out.println(items);
        int total = items.size();
        if (total == 0) {
            System.out.println("no available " + type);
            return null;
        }
        int key = 0;
        while (true) {
            System.out.println("select one:");
            if (scanner.hasNextInt()) {
                key = scanner.nextInt();
                if (key == -1) {
                    return null;
                }
                if (key > 0 && key <= total) {
                    break;
                }
                System.out.println("not a valid integer");
            } else {
                System.out.println("not an integer");
                scanner.next();
            }
        }
        return items.get(key - 1);
    }

    public void equip(Hero hero) {
        System.out.println("equip an Armor(enter -1 to skip):");
        Armor a = (Armor) getProp(hero.getBackpack(), ItemType.Armor);
        if (a != null) {
            a.equip(hero);
        }
        System.out.println("equip an Weapon(enter -1 to skip):");
        Weapon w = (Weapon) getProp(hero.getBackpack(), ItemType.Weapon);
        if (w != null) {
            w.equip(hero);
        }
    }

    public Hero getTrader(HeroTeam heroes) {
        ArrayList<Hero> allHeroes = heroes.getAliveHeroes();
        int key;
        while (true) {
            System.out.println("choose a hero:");
            System.out.println("(enter -1 to exit the market)");
            if (scanner.hasNextInt()) {
                key = scanner.nextInt();
                if (key == -1) {
                    return null;
                }
                if (key > 0 && key <= allHeroes.size()) {
                    break;
                }
                System.out.println("not a valid integer");
            } else {
                System.out.println("not an integer");
                scanner.next();
            }
        }
        return allHeroes.get(key - 1);
    }

    public int getTradeAction() {
        int key;
        while (true) {
            System.out.println("enter 1 if wanna sell, enter 2 if wanna buy:");
            System.out.println("(enter -1 to return back)");
            if (scanner.hasNextInt()) {
                key = scanner.nextInt();
                if (key == -1) {
                    return -1;
                }
                if (key > 0 && key <= 2) {
                    break;
                }
                System.out.println("not a valid integer");
            } else {
                System.out.println("not an integer");
                scanner.next();
            }
        }
        return key;
    }

    public ItemType getItemType() {
        int key;
        while (true) {
            System.out.println("Choose the type of item:");
            System.out.println("(enter -1 to return back)");
            if (scanner.hasNextInt()) {
                key = scanner.nextInt();
                if (key == -1) {
                    return null;
                }
                if (key > 0 && key <= 4) {
                    break;
                }
                System.out.println("not a valid integer");
            } else {
                System.out.println("not an integer");
                scanner.next();
            }
        }
        return ItemType.getItem(key);
    }

    public String getKey() {
        String key;
        while (true) {
            System.out.println("Input:");
            if (scanner.hasNext()) {
                key = scanner.next().toLowerCase(Locale.ROOT);
                if (key.length() > 1) {
                    System.out.println("too many input at a time");
                    continue;
                }
                if (hotkey.contains(key)) {
                    return key;
                }
                System.out.println("not a valid input");
            } else {
                System.out.println("not an valid input");
                scanner.next();
            }
        }
    }

    public String ifExitCurView() {
        String key;
        while (true) {
            System.out.println("Input:");
            if (scanner.hasNext()) {
                key = scanner.next().toLowerCase(Locale.ROOT);
                if (key.length() > 1) {
                    System.out.println("too many input at a time");
                    continue;
                }
                if (key.equals("b")) {
                    return key;
                } else if (key.equals("q")) {
                    return key;
                }
                System.out.println("not a valid input");
            } else {
                System.out.println("not an valid input");
                scanner.next();
            }
        }
    }

    public int ChooseNum() {
        int key = 0;
        System.out.println("input(1,2,3):");
        while (true) {
            if (scanner.hasNextInt()) {
                key = scanner.nextInt();
                if (key > 0 && key <= 3) {
                    break;
                }
                System.out.println("not a valid integer");
            } else {
                System.out.println("not an integer");
                scanner.next();
            }
        }
        return key;
    }

    public int getAnswer() {
        String key;
        while (true) {
            System.out.println("Y/y for Yes & N/n for No:");
            if (scanner.hasNext()) {
                key = scanner.next().toLowerCase(Locale.ROOT);
                if (key.length() > 1) {
                    System.out.println("too many input at a time");
                    continue;
                }
                if (key.equals("y")) {
                    return 1;
                } else if (key.equals("n")) {
                    return 0;
                }
                System.out.println("not a valid input");
            } else {
                System.out.println("not an valid input");
                scanner.next();
            }
        }
    }
}
