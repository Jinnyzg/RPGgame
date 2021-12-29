/**
 *  an interface that defines the behavior of an item that can be used for once(can use)
 *  and the generic type T extends Character defines which type of character can use it.
 * @param <T>: which character can equip the item
 */
public interface Usable<T extends Character> {
    public void use(T c);
}
