import java.util.ArrayList;
import java.util.HashSet;

/**
 * A cell in the 2d grid map, which can contains multiple worldComponents in it
 */
public class Tile {
    private final HashSet<ComponentType> componentsType;
    private final ArrayList<WorldComponent> components;

    public Tile() {
        componentsType = new HashSet<>();
        components = new ArrayList<>();
    }

    public boolean addComponents(WorldComponent x, ArrayList<String> logger) {
        ComponentType curType = x.getComponentType();
        if (componentsType.contains(ComponentType.Stone)) {
           logger.add("A stone! cannot reach..");
            return false;
        } else if (componentsType.contains(ComponentType.Market) && curType.equals(ComponentType.Monster)) {
            return false;
        } else if (componentsType.contains(ComponentType.Hero) && curType.equals(ComponentType.Monster)) {
            logger.add("Sorry, this place is already occupied by another hero");
            return false;
        }
        components.add(x);
        componentsType.add(x.getComponentType());
        return true;
    }
    public boolean addComponents(WorldComponent x) {
        ComponentType curType = x.getComponentType();
        if (componentsType.contains(ComponentType.Stone) || componentsType.contains(ComponentType.Market) && curType.equals(ComponentType.Monster)) {
            return false;
        } else if (componentsType.contains(ComponentType.Hero) && curType.equals(ComponentType.Monster)) {
            return false;
        }
        components.add(x);
        componentsType.add(x.getComponentType());
        return true;
    }


    // remove a component with a specified ComponentType
    public WorldComponent removeComponents(ComponentType x) {
        if (componentsType.contains(x)) {
            componentsType.remove(x);
            for (WorldComponent c : components) {
                if (c.getComponentType().equals(x)) {
                    WorldComponent res = c;
                    components.remove(c);
                    return res;
                }
            }
        }
        System.out.println("WARNING: No specified component exist to remove!");
        return null;
    }


    public Market getMarket(){
        for(WorldComponent c : components){
            if(c.getComponentType().equals(ComponentType.Market)){
                return (Market) c;
            }
        }
        return null;
    }

    // used to check whether a hero encounter a market
    public boolean ifMarket() {
        return (componentsType.contains(ComponentType.Hero) && componentsType.contains(ComponentType.Market));
    }

    @Override
    public String toString() {
        if (componentsType.contains(ComponentType.HeroTeam)) {
            return ComponentType.HeroTeam.toString();
        } else if (componentsType.contains(ComponentType.Stone)) {
            return ComponentType.Stone.toString();
        } else if (componentsType.contains(ComponentType.Market)) {
            return ComponentType.Market.toString();
        } else {
            return " ";
        }
    }
}
