/**
 * An interface that defines a factory's behavior of the RPGgame
 */
public interface FactoryCreator {
    Market createMarket();
    Map createMap();
    HeroTeam createHeroes(int num);
    MonsterTeam createMonsters(int num);
}
