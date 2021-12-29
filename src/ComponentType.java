/**
 * A class defines all objects that exist on the world map
 **/
public enum ComponentType {
    Market,
    Hero,
    HeroTeam,
    Monster,
    MonsterTeam,
    Road,
    Stone;

    @Override
    public String toString() {
        return switch (this) {
            case Market -> "M";
            case Hero,HeroTeam -> "H";
            case Road, Monster, MonsterTeam -> " ";
            case Stone -> "&";
        };
    }
}
