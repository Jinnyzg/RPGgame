import java.util.ArrayList;

/**
 * This class defines the basic move mode of this game, with methods to manage move action
 */
public class MoveMode implements IMoveMode{
    private Map map;
    private HeroTeam heroes;
    private int[] curPos;
    private ArrayList<String> logger;

    public MoveMode(Map map, HeroTeam heroes) {
        setMap(map);
        setHeroes(heroes);
        setCurPos(new int[]{0, 0});
        getMap().add(getCurPos()[0], getCurPos()[1], getHeroes());
        logger = initLogger();
    }

    public ArrayList<String> move(String key) {
        switch (key) {
            case "w" -> {
                Tile curtile = map.getTile(curPos[0], curPos[1]);
                if (curPos[1] > 0) {
                    Tile tile = map.getTile(curPos[0], curPos[1] - 1);
                    if (tile.addComponents(heroes, logger)) {
                        curtile.removeComponents(ComponentType.HeroTeam);
                        curPos[1] -= 1;
                        logger.add("move successfully!");
                    }
                } else {
                    logger.add("reached the boundary...");
                }
            }
            case "s" -> {
                Tile curtile = map.getTile(curPos[0], curPos[1]);
                if (curPos[1] < map.getH() - 1) {
                    Tile tile = map.getTile(curPos[0], curPos[1] + 1);
                    if (tile.addComponents(heroes, logger)) {
                        curtile.removeComponents(ComponentType.HeroTeam);
                        curPos[1] += 1;
                        logger.add("move successfully!   ");
                    }
                } else {
                    logger.add("reached the boundary..");
                }
            }
            case "a" -> {
                Tile curtile = map.getTile(curPos[0], curPos[1]);
                if (curPos[0] > 0) {
                    Tile tile = map.getTile(curPos[0] - 1, curPos[1]);
                    if (tile.addComponents(heroes, logger)) {
                        curtile.removeComponents(ComponentType.HeroTeam);
                        curPos[0] -= 1;
                        logger.add("move successfully!   ");
                    }
                } else {
                    logger.add("reached the boundary..");
                }
            }
            case "d" -> {
                Tile curtile = map.getTile(curPos[0], curPos[1]);
                if (curPos[0] < map.getW() - 1) {
                    Tile tile = map.getTile(curPos[0] + 1, curPos[1]);
                    if (tile.addComponents(heroes, logger)) {
                        curtile.removeComponents(ComponentType.HeroTeam);
                        curPos[0] += 1;
                        logger.add("move successfully!   ");
                    }
                } else {
                    logger.add("reached the boundary...");
                }
            }
        }
        return logger;
    }

    public void Move(InteractionManager interactionManager) {
        LayoutManager mapViewLayout = new LayoutManager();
        while (true) {
            mapViewLayout.addComponent(12, 45, ViewComponent.loggerBoard(logger, 1));
            GameView.MapView(map, mapViewLayout);
            //logger = initLogger();
            String key = interactionManager.getKey();
            if (key.equals("w")) {
                Tile curtile = map.getTile(curPos[0], curPos[1]);
                if (curPos[1] > 0) {
                    Tile tile = map.getTile(curPos[0], curPos[1] - 1);
                    if (tile.addComponents(heroes, logger)) {
                        curtile.removeComponents(ComponentType.HeroTeam);
                        curPos[1] -= 1;
                        logger.add("move successfully!");
                    }
                } else {
                    logger.add("reached the boundary...");
                }
                //logger.add("try another direction...");
            } else if (key.equals("s")) {
                Tile curtile = map.getTile(curPos[0], curPos[1]);
                if (curPos[1] < map.getH() - 1) {
                    Tile tile = map.getTile(curPos[0], curPos[1] + 1);
                    if (tile.addComponents(heroes, logger)) {
                        curtile.removeComponents(ComponentType.HeroTeam);
                        curPos[1] += 1;
                        logger.add("move successfully!   ");
                    }
                } else {
                    logger.add("reached the boundary..");
                }
                //logger.add("try another direction...");
            } else if (key.equals("a")) {
                Tile curtile = map.getTile(curPos[0], curPos[1]);
                if (curPos[0] > 0) {
                    Tile tile = map.getTile(curPos[0] - 1, curPos[1]);
                    if (tile.addComponents(heroes, logger)) {
                        curtile.removeComponents(ComponentType.HeroTeam);
                        curPos[0] -= 1;
                        logger.add("move successfully!   ");
                    }
                } else {
                    logger.add("reached the boundary..");
                }
                //logger.add("try another direction...");
            } else if (key.equals("d")) {
                Tile curtile = map.getTile(curPos[0], curPos[1]);
                if (curPos[0] < map.getW() - 1) {
                    Tile tile = map.getTile(curPos[0] + 1, curPos[1]);
                    if (tile.addComponents(heroes, logger)) {
                        curtile.removeComponents(ComponentType.HeroTeam);
                        curPos[0] += 1;
                        logger.add("move successfully!   ");
                    }
                } else {
                    logger.add("reached the boundary...");
                }
                //logger.add("try another direction...");
            } else if (key.equals("i")) {
                LayoutManager InfoLayout = new LayoutManager();
                GameView.InfoView(heroes, InfoLayout);
                if (interactionManager.ifExitCurView().equals("q")) {
                    System.out.println("existing game");
                    return;
                }
            } else {
                System.out.println("existing game");
                return;
            }
        }
    }

    public ArrayList<String> initLogger() {
        logger = new ArrayList<>();
        logger.add("choose a direction     ");
        return logger;
    }

    public int[] getCurPos() {
        return curPos;
    }

    public void setCurPos(int[] curPos) {
        this.curPos = curPos;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public HeroTeam getHeroes() {
        return heroes;
    }

    public void setHeroes(HeroTeam heroes) {
        this.heroes = heroes;
    }

    public ArrayList<String> getLogger() {
        return logger;
    }

    public void setLogger(ArrayList<String> logger) {
        this.logger = logger;
    }

    public Market ifMarket() {
        Tile curtile = map.getTile(curPos[0], curPos[1]);
        return curtile.getMarket();
    }


    public boolean needFight() {
        return Math.random() >= 0.5 && curPos[0] != 0 && curPos[1] != 0;
    }
}
