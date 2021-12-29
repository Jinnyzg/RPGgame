/**
 * This class define the trade mode of this game, with methods to manage trade action
 */
public class TradeMode implements ITradeMode{
    private Market market;
    private HeroTeam heroTeam;
    private InteractionManager interactionManager;

    public TradeMode(Market market, HeroTeam heroTeam, InteractionManager interactionManager) {
        this.market = market;
        this.heroTeam = heroTeam;
        this.interactionManager = interactionManager;
    }

    public void trade() {
        GameView.MarketView(heroTeam);
        while (true) {
            Hero hero = interactionManager.getTrader(heroTeam);
            if (hero == null) {
                System.out.println("------------exit the Market----------");
                return;
            }
            int i = interactionManager.getTradeAction();
            // if player enter -1, turn back
            if (i == -1) {
                continue;
            }
            System.out.println(ItemType.toStringInfo());
            ItemType type = interactionManager.getItemType();
            // if player choose nothing, turn bock
            if (type == null) {
                continue;
            }
            // sell:1
            if (i == 1) {
                //System.out.println(hero.getBackpack().get(type));
                Item item = interactionManager.getProp(hero.getBackpack(), type);
                if (item == null) {
                    continue;
                }
                item.sell(hero);
                System.out.println(hero.getName() + " sells " + item.getName() + " successfully!");
            } else {
                //System.out.println(market.getItems().get(type));
                Item item = interactionManager.getProp(market.getItems(), type);
                if (item == null) {
                    continue;
                }
                item.buy(hero);
            }
        }
    }

}
