/**
 * A class defines all objects that exist on the world map
 **/
public class WorldComponent {
    private ComponentType componentType;
    private String symbol;

    public WorldComponent(ComponentType componentType) {
        this.componentType = componentType;
        this.symbol = componentType.toString();
    }

    @Override
    public String toString() {
        return symbol;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public String getSymbol() {
        return symbol;
    }
}
