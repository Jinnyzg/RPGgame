/**
 * A generic interface defines the items that can be equipped by characters(can equip),
 * and the generic type T extends Character defines which type of character can equip it.
 * @param <T>: which character can equip the item
 */
public interface Equipable<T extends Character> {
    void equip(T h);
}
