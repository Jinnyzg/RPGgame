import java.util.ArrayList;
import java.util.HashMap;

/**
 *  A subclass of WorldComponent,
 *  representing a place where heroes can buy and sell their items,
 *  and it has its own inventory
 */

public class Market extends WorldComponent {
    private HashMap<ItemType, ArrayList<Item>> items;

    public Market(HashMap<ItemType, ArrayList<Item>> items) {
        super(ComponentType.Market);
        setItems(items);
    }

    // initialize an empty inventory if necessary
    public void initItems() {
        for (ItemType type : ItemType.values()) {
            items.put(type, new ArrayList<>());
        }
    }

    public HashMap<ItemType, ArrayList<Item>> getItems() {
        return items;
    }

    public void setItems(HashMap<ItemType, ArrayList<Item>> items) {
        this.items = items;
    }

}
