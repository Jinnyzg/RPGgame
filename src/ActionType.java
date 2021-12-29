import java.util.HashMap;

/**
 * This enum class defines all action the player can choose in the fight mode
 * and each type stores a key(Integer), then we can use the key to get the actionType easily
 */

public enum ActionType {
    CastSpell(1),
    ChangeEq(2),
    Attack(3),
    UsePortion(4),
    SeeHeroInfo(5),
    SeeMonsterInfo(6);
    private int key;
    private static HashMap<Integer, ActionType> keyToAction;

    ActionType(int val) {
        this.key = val;
    }

    public int getKey() {
        return key;
    }

    public static HashMap<Integer, ActionType> getkeyToAction() {
        if (keyToAction == null) {
            initMapping();
        }
        return keyToAction;
    }

    public static ActionType getAction(Integer key) {
        if (keyToAction == null) {
            initMapping();
        }
        return keyToAction.get(key);
    }

    private static void initMapping() {
        keyToAction = new HashMap<>();
        for (ActionType s : values()) {
            keyToAction.put(s.key, s);
        }
    }

    public static String toStringInfo() {
        String res = "ActionTypes:";
        for (ActionType i : ActionType.values()) {
            res += " *" + i + "->" + i.key;
        }
        return res;
    }
}
