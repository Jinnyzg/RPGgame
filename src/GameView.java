import java.util.ArrayList;
import java.util.HashMap;

/**
 * this classes create different specific views of this game for different modes and different types of output
 */

class GameView {
    // to achieve colorful console output
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void StartView() {
        LayoutManager layoutManager = new LayoutManager();
        String[] strings = new String[]{"        WELCOME      ",
                "Legends: Monsters and Heroes",
                "This is a magical game full of spells, heroes and monsters.",
                "You will have a hero team with 1 to 3 heroes in three types:",
                "  * Warriors: favored on strength and agility",
                "  * Sorcerers: favored on the dexterity and the agility",
                "  * Paladins are favored on strength and dexterity",
                "-> INPUT HOW MANY HEROES DO YOU WANT: "};
        layoutManager.addComponent(5, 15, ViewComponent.createInfoBlock(strings));
        layoutManager.display(ANSI_GREEN);
    }

    public static void MapView(Map map, LayoutManager layoutManager) {
        layoutManager.addViewTitle(ViewComponent.viewTitle("{MAP VIEW}"));
        layoutManager.addComponent(4, 4, map.toStringList());
        layoutManager.addComponent(2, 45, ViewComponent.hotKeyBlock());
        layoutManager.display(ANSI_YELLOW);
    }

    public static void InfoView(HeroTeam heroes, LayoutManager layoutManager) {
        layoutManager.addViewTitle(ViewComponent.viewTitle("{INFORMATION VIEW}"));
        int startx = 4;
        int starty = 2;
        for (Hero hero : heroes.getAliveHeroes()) {
            ArrayList<String> heroInfo = hero.toStringList();
            layoutManager.addComponent(startx, starty, ViewComponent.createListView(heroInfo));
            starty = starty + 35;
        }
        layoutManager.addComponent(19, 2, ViewComponent.createStringView("press b to turn back..."));
        layoutManager.addComponent(20, 2, ViewComponent.createStringView("press p to exit game..."));
        layoutManager.display(ANSI_CYAN);
    }

    public static void MarketView(HeroTeam heroes) {
        System.out.println(ANSI_BLUE + "--------------IN MARKET---------------" +ANSI_BLUE);
        System.out.println(ANSI_BLUE + heroes + ANSI_BLUE);
    }

    public static void FightView(HeroTeam heroes,MonsterTeam monster,ArrayList<String> logger){
        LayoutManager layoutManager = new LayoutManager();
        String[] curfighters = new String[3];
        layoutManager.addViewTitle(ViewComponent.viewTitle("{FIGHT VIEW}"));
        curfighters[0] = "Current Fight";
        curfighters[1] = "Hero:" + heroes.getCurFightHero();
        curfighters[2] = "Monster:" + monster.getCurFightMonster();
        layoutManager.addComponent(2,2,ViewComponent.createInfoBlock(curfighters));
        layoutManager.addComponent(7,2,ViewComponent.loggerBoard(logger,6));
        layoutManager.addComponent(17,2,ViewComponent.createStringView("Current Alive Hero number:" + heroes.getAliveHeroes().size()));
        layoutManager.addComponent(18,2,ViewComponent.createStringView("Current Alive Monster number:" + monster.getAliveMonster().size()));
        layoutManager.addComponent(19,2,ViewComponent.createStringView(ActionType.toStringInfo()));
        layoutManager.display(ANSI_PURPLE);
    }

}