import java.util.HashMap;

/**
 * A enum class that defines all item types,
 * and each type stores a key(Integer), then we can use the key to get the ItemType easily
 *
 */
public enum ItemType {
    Weapon(1),
    Armor(2),
    Potion(3),
    Spell(4);
    private int key;
    private static HashMap<Integer, ItemType> keyToItem;

    ItemType(int val) {
        this.key = val;
    }

    public int getKey() {
        return key;
    }

    public static HashMap<Integer, ItemType> getKeyToItem(){
        if (keyToItem == null) {
            initMapping();
        }
        return keyToItem;
    }

    public static ItemType getItem(Integer key) {
        if (keyToItem == null) {
            initMapping();
        }
        return keyToItem.get(key);
    }

    private static void initMapping() {
        keyToItem = new HashMap<>();
        for (ItemType s : values()) {
            keyToItem.put(s.key, s);
        }
    }

    public static String toStringInfo() {
        String res = "ItemTypes:";
        for(ItemType i: ItemType.values()){
            res += " *" + i + "->" + i.key ;
        }
        return res;
    }
}
