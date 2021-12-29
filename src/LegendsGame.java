import java.util.ArrayList;

public class LegendsGame implements Game {
    private static FactoryCreator factory;
    private static MoveMode moveMode;
    private static TradeMode tradeMode;
    private static ArrayList<String> logger;
    private static final String moveKey = "asdw";

    public static void initWorld(int heroNum, FactoryCreator worldFactory){
        factory = worldFactory;
        HeroTeam heroes = worldFactory.createHeroes(heroNum);
        Map map = worldFactory.createMap();
        moveMode = new MoveMode(map,heroes);
        logger = initLogger();
    }

    private static ArrayList<String> initLogger() {
        logger = new ArrayList<>();
        logger.add("choose a direction     ");
        return logger;
    }

    public void play(){
        InteractionManager interactionManager = new InteractionManager();
        FactoryCreator worldFactory = new WorldFactory();
        GameView.StartView();
        int heroNum = interactionManager.ChooseNum();
        initWorld(heroNum, worldFactory);
        LayoutManager mapViewLayout = new LayoutManager();
        String key;
        while(true){
            if (moveMode.ifMarket() != null){
                ArrayList<String> curInfo = new ArrayList<>();
                curInfo.add("Market found! In?(y/n)");
                mapViewLayout.addComponent(12, 45, ViewComponent.loggerBoard(curInfo, 1));
                GameView.MapView(moveMode.getMap(), mapViewLayout);
                if(interactionManager.getAnswer() == 1){
                    Market market = moveMode.ifMarket();
                    tradeMode = new TradeMode(market,moveMode.getHeroes(),interactionManager);
                    tradeMode.trade();
                }
            } else if(moveMode.needFight()){
                MonsterTeam monsterTeam = factory.createMonsters(heroNum);
                FightMode fightMode = new FightMode(moveMode.getHeroes(), monsterTeam);
                fightMode.Fight(interactionManager);
            }
            mapViewLayout.addComponent(12, 45, ViewComponent.loggerBoard(logger, 1));
            GameView.MapView(moveMode.getMap(), mapViewLayout);
            key = interactionManager.getKey();
            if(moveKey.contains(key)){
                logger = moveMode.move(key);
            } else if (key.equals("i")) {
                LayoutManager InfoLayout = new LayoutManager();
                GameView.InfoView(moveMode.getHeroes(), InfoLayout);
                if (interactionManager.ifExitCurView().equals("q")) {
                    System.out.println("existing game");
                    return;
                }
            }else{
                System.out.println("existing game");
                return;
            }
        }
    }

}
